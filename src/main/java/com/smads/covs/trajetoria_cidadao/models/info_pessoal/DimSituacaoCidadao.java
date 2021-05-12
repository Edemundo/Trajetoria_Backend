package com.smads.covs.trajetoria_cidadao.models.info_pessoal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DimSituacaoCidadao {

  @Id
  private Integer ciSitCidadao;
  private String dcSitCidadao;

  public DimSituacaoCidadao() {
  }

  public DimSituacaoCidadao(Integer ciSitCidadao) {
    this.ciSitCidadao = ciSitCidadao;
  }

  public DimSituacaoCidadao(Integer ciSitCidadao, String dcSitCidadao) {
    this.ciSitCidadao = ciSitCidadao;
    this.dcSitCidadao = dcSitCidadao;
  }

  public Integer getCiSitCidadao() {
    return ciSitCidadao;
  }

  public void setCiSitCidadao(Integer ciSitCidadao) {
    this.ciSitCidadao = ciSitCidadao;
  }

  public String getDcSitCidadao() {
    return dcSitCidadao;
  }

  public void setDcSitCidadao(String dcSitCidadao) {
    this.dcSitCidadao = dcSitCidadao;
  }

  @Override
  public String toString() {
    return "DimSituacaoCidadao{" +
      "ciSitCidadao=" + ciSitCidadao +
      ", dcSitCidadao='" + dcSitCidadao + '\'' +
      '}';
  }
}
