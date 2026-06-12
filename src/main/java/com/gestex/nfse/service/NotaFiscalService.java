package com.gestex.nfse.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestex.nfse.model.*;
import com.gestex.nfse.repository.ConfigFiscalRepository;
import com.gestex.nfse.repository.HistoricoNotasRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotaFiscalService {

    private static final String FOCUS_BASE_URL = "https://homologacao.focusnfe.com.br";

    private final ConfigFiscalRepository configRepository;
    private final HistoricoNotasRepository historicoRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotaFiscalService(
            ConfigFiscalRepository configRepository,
            HistoricoNotasRepository historicoRepository
    ) {
        this.configRepository = configRepository;
        this.historicoRepository = historicoRepository;
    }

    public NfseResponse emitirNota(Long empresaId, NfseRequest request) throws Exception {
        ConfigFiscal config = configRepository.findByEmpresaId(empresaId)
                .orElseThrow(() -> new RuntimeException("Configuração fiscal não encontrada para empresa " + empresaId));

        String token = config.getTokenHomologacao();
        String url = FOCUS_BASE_URL + "/v2/nfsen";
        String referencia = "NFSE_" + System.currentTimeMillis();

        Map<String, Object> body = montarJson(request, config);

        HttpHeaders headers = criarHeaders(token);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url + "?ref=" + referencia,
                HttpMethod.POST,
                entity,
                String.class
        );

        JsonNode jsonResponse = objectMapper.readTree(response.getBody());

        salvarHistorico(config, referencia, request, jsonResponse);

        NfseResponse nfseResponse = montarRespostaNormalizada(jsonResponse);
        nfseResponse.setReferencia(referencia);

        return nfseResponse;
    }

    public NfseResponse consultarNota(String referencia) throws Exception {
        ConfigFiscal config = configRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma configuração fiscal encontrada"));

        String token = config.getTokenHomologacao();
        String url = FOCUS_BASE_URL + "/v2/nfsen/" + referencia;

        HttpHeaders headers = criarHeaders(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        JsonNode jsonResponse = objectMapper.readTree(response.getBody());

        NfseResponse nfseResponse = montarRespostaNormalizada(jsonResponse);
        nfseResponse.setReferencia(referencia);

        return nfseResponse;
    }

    private HttpHeaders criarHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String auth = Base64.getEncoder().encodeToString((token + ":").getBytes());
        headers.set("Authorization", "Basic " + auth);

        return headers;
    }

    private NfseResponse montarRespostaNormalizada(JsonNode jsonResponse) {
        NfseResponse nfseResponse = new NfseResponse();

        nfseResponse.setStatus(pegarTexto(jsonResponse, "status"));
        nfseResponse.setNumeroRps(pegarTexto(jsonResponse, "numero_rps"));
        nfseResponse.setSerieRps(pegarTexto(jsonResponse, "serie_rps"));
        nfseResponse.setNumero(pegarTexto(jsonResponse, "numero"));
        nfseResponse.setCodigoVerificacao(pegarTexto(jsonResponse, "codigo_verificacao"));

        String caminhoXml = pegarTexto(jsonResponse, "caminho_xml_nota_fiscal");
        String urlDanfse = pegarTexto(jsonResponse, "url_danfse");

        nfseResponse.setUrlXml(normalizarUrl(caminhoXml));
        nfseResponse.setUrlPdf(normalizarUrl(urlDanfse));

        if (jsonResponse.has("erros")) {
            nfseResponse.setMensagemErro(jsonResponse.get("erros").toString());
        } else if (jsonResponse.has("mensagem")) {
            nfseResponse.setMensagemErro(jsonResponse.get("mensagem").asText());
        }

        return nfseResponse;
    }

    private String pegarTexto(JsonNode json, String campo) {
        if (json != null && json.has(campo) && !json.get(campo).isNull()) {
            return json.get(campo).asText();
        }
        return null;
    }

    private String normalizarUrl(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }

        if (valor.startsWith("http://") || valor.startsWith("https://")) {
            return valor;
        }

        return FOCUS_BASE_URL + valor;
    }

    private Map<String, Object> montarJson(NfseRequest request, ConfigFiscal config) {
        Map<String, Object> json = new HashMap<>();

        String dataAtual = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'-03:00'"));

        String dataCompetencia = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        json.put("data_emissao", dataAtual);
        json.put("data_competencia", dataCompetencia);
        json.put("codigo_municipio_emissora", Integer.parseInt(config.getCodigoMunicipioIbge()));
        json.put("cnpj_prestador", config.getCnpj());

        if (config.getUsarCorrecaoNatal() != null && config.getUsarCorrecaoNatal()) {
            json.put("codigo_opcao_simples_nacional", 3);
            json.put("regime_tributario_simples_nacional", 1);
        } else {
            json.put("codigo_opcao_simples_nacional", Integer.parseInt(config.getRegimeTributario()));
        }

        json.put("regime_especial_tributacao", 0);

        json.put("cnpj_tomador", request.getCnpjTomador());
        json.put("razao_social_tomador", request.getRazaoSocialTomador());
        json.put("codigo_municipio_tomador", Integer.parseInt(config.getCodigoMunicipioIbge()));
        json.put("cep_tomador", request.getCepTomador());
        json.put("logradouro_tomador", request.getLogradouroTomador());
        json.put("numero_tomador", request.getNumeroTomador());
        json.put("bairro_tomador", request.getBairroTomador());
        json.put("telefone_tomador", request.getTelefoneTomador());
        json.put("email_tomador", request.getEmailTomador());

        json.put("codigo_municipio_prestacao", Integer.parseInt(config.getCodigoMunicipioIbge()));
        json.put("codigo_tributacao_nacional_iss", "130501");
        json.put("codigo_nbs", "121012200");
        json.put("descricao_servico", request.getDescricaoServico());
        json.put("valor_servico", request.getValorServico());
        json.put("valor_iss", request.getValorServico().multiply(new java.math.BigDecimal("0.02")));

        json.put("tributacao_iss", 1);
        json.put("tipo_retencao_iss", 1);
        json.put("situacao_tributaria_pis_cofins", "00");

        json.put("percentual_total_tributos_federais", "10.38");
        json.put("percentual_total_tributos_estaduais", "0.00");
        json.put("percentual_total_tributos_municipais", "2.50");
        json.put("indicador_total_tributacao", null);

        return json;
    }

    private void salvarHistorico(
            ConfigFiscal config,
            String referencia,
            NfseRequest request,
            JsonNode response
    ) {
        HistoricoNotas historico = new HistoricoNotas();

        historico.setEmpresaId(config.getEmpresaId());
        historico.setReferencia(referencia);
        historico.setStatus(pegarTexto(response, "status"));
        historico.setNumeroRps(pegarTexto(response, "numero_rps"));
        historico.setNumero(pegarTexto(response, "numero"));
        historico.setCodigoVerificacao(pegarTexto(response, "codigo_verificacao"));

        String caminhoXml = pegarTexto(response, "caminho_xml_nota_fiscal");
        String urlDanfse = pegarTexto(response, "url_danfse");

        historico.setUrlXml(normalizarUrl(caminhoXml));
        historico.setUrlPdf(normalizarUrl(urlDanfse));

        historico.setValorServico(request.getValorServico());
        historico.setTomadorCnpj(request.getCnpjTomador());
        historico.setTomadorNome(request.getRazaoSocialTomador());
        historico.setDataEmissao(LocalDateTime.now());
        historico.setCreatedAt(LocalDateTime.now());
        historico.setResponseJson(response.toString());

        historicoRepository.save(historico);
    }
}