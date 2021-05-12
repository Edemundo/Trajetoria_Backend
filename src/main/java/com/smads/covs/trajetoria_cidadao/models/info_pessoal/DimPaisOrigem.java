package com.smads.covs.trajetoria_cidadao.models.info_pessoal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DimPaisOrigem {

  @Id
  private Integer ciPaisOrigem;
  private String nmPais;

  public DimPaisOrigem() {
  }

  public DimPaisOrigem(Integer ciPaisOrigem) {
    this.ciPaisOrigem = ciPaisOrigem;
  }

  public DimPaisOrigem(Integer ciPaisOrigem, String nmPais) {
    this.ciPaisOrigem = ciPaisOrigem;
    this.nmPais = nmPais;
  }

  public Integer getCiPaisOrigem() {
    return ciPaisOrigem;
  }

  public void setCiPaisOrigem(Integer ciPaisOrigem) {
    this.ciPaisOrigem = ciPaisOrigem;
  }

  public String getNmPais() {
    return nmPais;
  }

  public void setNmPais(String nmPais) {
    this.nmPais = nmPais;
  }

  @Override
  public String toString() {
    return "DimPaisOrigem{" +
      "ciPaisOrigem=" + ciPaisOrigem +
      ", nmPais='" + nmPais + '\'' +
      '}';
  }
}
