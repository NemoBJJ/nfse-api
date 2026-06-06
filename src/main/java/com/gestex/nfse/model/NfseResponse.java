package com.gestex.nfse.model;

public class NfseResponse {
    private String status;
    private String numeroRps;
    private String serieRps;
    private String numero;
    private String chaveNfse;
    private String codigoVerificacao;
    private String urlXml;
    private String urlPdf;
    private String mensagemErro;
    private String referencia;

    // Getters e Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumeroRps() {
        return numeroRps;
    }

    public void setNumeroRps(String numeroRps) {
        this.numeroRps = numeroRps;
    }

    public String getSerieRps() {
        return serieRps;
    }

    public void setSerieRps(String serieRps) {
        this.serieRps = serieRps;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getChaveNfse() {
        return chaveNfse;
    }

    public void setChaveNfse(String chaveNfse) {
        this.chaveNfse = chaveNfse;
    }

    public String getCodigoVerificacao() {
        return codigoVerificacao;
    }

    public void setCodigoVerificacao(String codigoVerificacao) {
        this.codigoVerificacao = codigoVerificacao;
    }

    public String getUrlXml() {
        return urlXml;
    }

    public void setUrlXml(String urlXml) {
        this.urlXml = urlXml;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}