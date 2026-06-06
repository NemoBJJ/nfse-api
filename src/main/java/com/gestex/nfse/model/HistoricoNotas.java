package com.gestex.nfse.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "historico_notas")
public class HistoricoNotas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "empresa_id", nullable = false)
    private Long empresaId;
    
    @Column(nullable = false, length = 100)
    private String referencia;
    
    @Column(name = "numero_rps", length = 20)
    private String numeroRps;
    
    @Column(name = "chave_nfse", length = 100)
    private String chaveNfse;
    
    @Column(length = 30)
    private String status;
    
    @Column(length = 20)
    private String numero;
    
    @Column(name = "codigo_verificacao", length = 100)
    private String codigoVerificacao;
    
    @Column(name = "data_emissao")
    private LocalDateTime dataEmissao;
    
    @Column(name = "valor_servico", precision = 10, scale = 2)
    private BigDecimal valorServico;
    
    @Column(name = "tomador_cnpj", length = 18)
    private String tomadorCnpj;
    
    @Column(name = "tomador_nome", length = 200)
    private String tomadorNome;
    
    @Column(name = "url_xml", length = 500)
    private String urlXml;
    
    @Column(name = "url_pdf", length = 500)
    private String urlPdf;
    
    @Column(name = "response_json", columnDefinition = "JSON")
    private String responseJson;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getNumeroRps() { return numeroRps; }
    public void setNumeroRps(String numeroRps) { this.numeroRps = numeroRps; }
    public String getChaveNfse() { return chaveNfse; }
    public void setChaveNfse(String chaveNfse) { this.chaveNfse = chaveNfse; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getCodigoVerificacao() { return codigoVerificacao; }
    public void setCodigoVerificacao(String codigoVerificacao) { this.codigoVerificacao = codigoVerificacao; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDateTime dataEmissao) { this.dataEmissao = dataEmissao; }
    public BigDecimal getValorServico() { return valorServico; }
    public void setValorServico(BigDecimal valorServico) { this.valorServico = valorServico; }
    public String getTomadorCnpj() { return tomadorCnpj; }
    public void setTomadorCnpj(String tomadorCnpj) { this.tomadorCnpj = tomadorCnpj; }
    public String getTomadorNome() { return tomadorNome; }
    public void setTomadorNome(String tomadorNome) { this.tomadorNome = tomadorNome; }
    public String getUrlXml() { return urlXml; }
    public void setUrlXml(String urlXml) { this.urlXml = urlXml; }
    public String getUrlPdf() { return urlPdf; }
    public void setUrlPdf(String urlPdf) { this.urlPdf = urlPdf; }
    public String getResponseJson() { return responseJson; }
    public void setResponseJson(String responseJson) { this.responseJson = responseJson; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
