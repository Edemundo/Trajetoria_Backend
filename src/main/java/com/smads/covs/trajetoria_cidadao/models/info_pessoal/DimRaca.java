package com.smads.covs.trajetoria_cidadao.models.info_pessoal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DimRaca {

  @Id
  private Integer ciRaca;
  private String dcRaca;

  public DimRaca() {
  }

  public Integer getCiRaca() {
    return ciRaca;
  }

  public void setCiRaca(Integer ciRaca) {
    this.ciRaca = ciRaca;
  }

  public String getDcRaca() {
    return dcRaca;
  }

  public void setDcRaca(String dcRaca) {
    this.dcRaca = dcRaca;
  }

  @Override
  public String toString() {
    return "DimRaca{" +
      "ciRaca=" + ciRaca +
      ", dcRaca='" + dcRaca + '\'' +
      '}';
  }
}
