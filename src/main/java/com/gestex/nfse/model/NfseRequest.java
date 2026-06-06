package com.gestex.nfse.model;

import java.math.BigDecimal;

public class NfseRequest {
    private String cnpjTomador;
    private String razaoSocialTomador;
    private String cepTomador;
    private String logradouroTomador;
    private String numeroTomador;
    private String bairroTomador;
    private String telefoneTomador;
    private String emailTomador;
    private String descricaoServico;
    private BigDecimal valorServico;
    
    // Getters e Setters
    public String getCnpjTomador() { return cnpjTomador; }
    public void setCnpjTomador(String cnpjTomador) { this.cnpjTomador = cnpjTomador; }
    public String getRazaoSocialTomador() { return razaoSocialTomador; }
    public void setRazaoSocialTomador(String razaoSocialTomador) { this.razaoSocialTomador = razaoSocialTomador; }
    public String getCepTomador() { return cepTomador; }
    public void setCepTomador(String cepTomador) { this.cepTomador = cepTomador; }
    public String getLogradouroTomador() { return logradouroTomador; }
    public void setLogradouroTomador(String logradouroTomador) { this.logradouroTomador = logradouroTomador; }
    public String getNumeroTomador() { return numeroTomador; }
    public void setNumeroTomador(String numeroTomador) { this.numeroTomador = numeroTomador; }
    public String getBairroTomador() { return bairroTomador; }
    public void setBairroTomador(String bairroTomador) { this.bairroTomador = bairroTomador; }
    public String getTelefoneTomador() { return telefoneTomador; }
    public void setTelefoneTomador(String telefoneTomador) { this.telefoneTomador = telefoneTomador; }
    public String getEmailTomador() { return emailTomador; }
    public void setEmailTomador(String emailTomador) { this.emailTomador = emailTomador; }
    public String getDescricaoServico() { return descricaoServico; }
    public void setDescricaoServico(String descricaoServico) { this.descricaoServico = descricaoServico; }
    public BigDecimal getValorServico() { return valorServico; }
    public void setValorServico(BigDecimal valorServico) { this.valorServico = valorServico; }
}
