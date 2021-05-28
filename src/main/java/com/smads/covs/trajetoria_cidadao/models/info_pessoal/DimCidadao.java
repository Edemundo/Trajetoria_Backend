package com.smads.covs.trajetoria_cidadao.models.info_pessoal;

  import javax.persistence.*;
  import java.io.Serializable;
  import java.math.BigInteger;
  import java.util.Date;

@Entity
public class DimCidadao implements Serializable {

  @Id
  private BigInteger ciCidadao;
  private String nmCidadao;
  private String nmMae;
  private Date dtNasc;
  private BigInteger cdNis;
  private BigInteger nrCpf;
  private Integer ciTipoSexo;
  private Integer ciRacaObservada;
  private Integer ciPaisOrigem;
  private Integer ciSitCidadao;

  public DimCidadao() {
  }

  public BigInteger getCiCidadao() {
    return ciCidadao;
  }

  public void setCiCidadao(BigInteger ciCidadao) {
    this.ciCidadao = ciCidadao;
  }

  public String getNmCidadao() {
    return nmCidadao;
  }

  public void setNmCidadao(String nmCidadao) {
    this.nmCidadao = nmCidadao;
  }

  public String getNmMae() {
    return nmMae;
  }

  public void setNmMae(String nmMae) {
    this.nmMae = nmMae;
  }

  public Date getDtNasc() {
    return dtNasc;
  }

  public void setDtNasc(Date dtNasc) {
    this.dtNasc = dtNasc;
  }

  public BigInteger getCdNis() {
    return cdNis;
  }

  public void setCdNis(BigInteger cdNis) {
    this.cdNis = cdNis;
  }

  public BigInteger getNrCpf() {
    return nrCpf;
  }

  public void setNrCpf(BigInteger nrCpf) {
    this.nrCpf = nrCpf;
  }

  public Integer getCiTipoSexo() {
    return ciTipoSexo;
  }

  public void setCiTipoSexo(Integer ciTipoSexo) {
    this.ciTipoSexo = ciTipoSexo;
  }

  public Integer getCiRacaObservada() {
    return ciRacaObservada;
  }

  public void setCiRacaObservada(Integer ciRacaObservada) {
    this.ciRacaObservada = ciRacaObservada;
  }

  public Integer getCiPaisOrigem() {
    return ciPaisOrigem;
  }

  public void setCiPaisOrigem(Integer ciPaisOrigem) {
    this.ciPaisOrigem = ciPaisOrigem;
  }

  public Integer getCiSitCidadao() {
    return ciSitCidadao;
  }

  public void setCiSitCidadao(Integer ciSitCidadao) {
    this.ciSitCidadao = ciSitCidadao;
  }

  @Override
  public String toString() {
    return "DimCidadao{" +
      "ciCidadao=" + ciCidadao +
      ", nmCidadao='" + nmCidadao + '\'' +
      ", nmMae='" + nmMae + '\'' +
      ", dtNasc=" + dtNasc +
      ", cdNis=" + cdNis +
      ", nrCpf=" + nrCpf +
      ", ciTipoSexo=" + ciTipoSexo +
      ", ciRacaObservada=" + ciRacaObservada +
      ", ciPaisOrigem=" + ciPaisOrigem +
      ", ciSitCidadao=" + ciSitCidadao +
      '}';
  }
}

