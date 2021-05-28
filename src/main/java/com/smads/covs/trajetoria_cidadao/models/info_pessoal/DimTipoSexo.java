package com.smads.covs.trajetoria_cidadao.models.info_pessoal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DimTipoSexo {

  @Id
  private Integer ciTipoSexo;
  private String dcTipoSexo;

  public DimTipoSexo() {
  }

  public Integer getCiTipoSexo() {
    return ciTipoSexo;
  }

  public void setCiTipoSexo(Integer ciTipoSexo) {
    this.ciTipoSexo = ciTipoSexo;
  }

  public String getDcTipoSexo() {
    return dcTipoSexo;
  }

  public void setDcTipoSexo(String dcTipoSexo) {
    this.dcTipoSexo = dcTipoSexo;
  }

  @Override
  public String toString() {
    return "DimTipoSexo{" +
      "ciTipoSexo=" + ciTipoSexo +
      ", dcTipoSexo='" + dcTipoSexo + '\'' +
      '}';
  }
}
