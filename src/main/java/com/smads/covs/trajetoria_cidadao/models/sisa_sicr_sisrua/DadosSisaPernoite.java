package com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua;

import java.util.Date;
import java.util.List;

public class DadosSisaPernoite {

  private String primeiraData;
  private String ultimaData;
  private Integer qtdEstadias;
  private String nmSubprefeitura;
  private String nmDistrito;
  private String nmServico;
  private String nmTipoServico;
  //private List<DadosListaPernoites> lstPernoitadas;

  public DadosSisaPernoite() {
  }

  public String getPrimeiraData() {
    return primeiraData;
  }

  public void setPrimeiraData(String primeiraData) {
    this.primeiraData = primeiraData;
  }

  public String getUltimaData() {
    return ultimaData;
  }

  public void setUltimaData(String ultimaData) {
    this.ultimaData = ultimaData;
  }

  public Integer getQtdEstadias() {
    return qtdEstadias;
  }

  public void setQtdEstadias(Integer qtdEstadias) {
    this.qtdEstadias = qtdEstadias;
  }

  public String getNmSubprefeitura() {
    return nmSubprefeitura;
  }

  public void setNmSubprefeitura(String nmSubprefeitura) {
    this.nmSubprefeitura = nmSubprefeitura;
  }

  public String getNmDistrito() {
    return nmDistrito;
  }

  public void setNmDistrito(String nmDistrito) {
    this.nmDistrito = nmDistrito;
  }

  public String getNmServico() {
    return nmServico;
  }

  public void setNmServico(String nmServico) {
    this.nmServico = nmServico;
  }

  public String getNmTipoServico() {
    return nmTipoServico;
  }

  public void setNmTipoServico(String nmTipoServico) {
    this.nmTipoServico = nmTipoServico;
  }

//  public List<DadosListaPernoites> getLstPernoitadas() {
//    return lstPernoitadas;
//  }
//
//  public void setLstPernoitadas(List<DadosListaPernoites> lstPernoitadas) {
//    this.lstPernoitadas = lstPernoitadas;
//  }

  @Override
  public String toString() {
    return "DadosSisaPernoite{" +
      "primeiraData='" + primeiraData + '\'' +
      ", ultimaData='" + ultimaData + '\'' +
      ", qtdEstadias=" + qtdEstadias +
      ", nmSubprefeitura='" + nmSubprefeitura + '\'' +
      ", nmDistrito='" + nmDistrito + '\'' +
      ", nmServico='" + nmServico + '\'' +
      ", nmTipoServico='" + nmTipoServico + '\'' +
//      ", lstPernoitadas=" + lstPernoitadas +
      '}';
  }
}
