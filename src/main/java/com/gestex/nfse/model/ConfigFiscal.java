package com.gestex.nfse.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "config_fiscal")
public class ConfigFiscal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "empresa_id", nullable = false)
    private Long empresaId;
    
    @Column(nullable = false, length = 18)
    private String cnpj;
    
    @Column(name = "razao_social", length = 200)
    private String razaoSocial;
    
    @Column(name = "inscricao_municipal", length = 20)
    private String inscricaoMunicipal;
    
    @Column(name = "codigo_municipio_ibge", length = 7)
    private String codigoMunicipioIbge;
    
    @Column(name = "token_homologacao", length = 255)
    private String tokenHomologacao;
    
    @Column(name = "token_producao", length = 255)
    private String tokenProducao;
    
    @Column(length = 10)
    private String ambiente;
    
    @Column(name = "regime_tributario", length = 1)
    private String regimeTributario;
    
    @Column(name = "usar_correcao_natal")
    private Boolean usarCorrecaoNatal;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getInscricaoMunicipal() { return inscricaoMunicipal; }
    public void setInscricaoMunicipal(String inscricaoMunicipal) { this.inscricaoMunicipal = inscricaoMunicipal; }
    public String getCodigoMunicipioIbge() { return codigoMunicipioIbge; }
    public void setCodigoMunicipioIbge(String codigoMunicipioIbge) { this.codigoMunicipioIbge = codigoMunicipioIbge; }
    public String getTokenHomologacao() { return tokenHomologacao; }
    public void setTokenHomologacao(String tokenHomologacao) { this.tokenHomologacao = tokenHomologacao; }
    public String getTokenProducao() { return tokenProducao; }
    public void setTokenProducao(String tokenProducao) { this.tokenProducao = tokenProducao; }
    public String getAmbiente() { return ambiente; }
    public void setAmbiente(String ambiente) { this.ambiente = ambiente; }
    public String getRegimeTributario() { return regimeTributario; }
    public void setRegimeTributario(String regimeTributario) { this.regimeTributario = regimeTributario; }
    public Boolean getUsarCorrecaoNatal() { return usarCorrecaoNatal; }
    public void setUsarCorrecaoNatal(Boolean usarCorrecaoNatal) { this.usarCorrecaoNatal = usarCorrecaoNatal; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
