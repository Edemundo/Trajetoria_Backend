package com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua;

import java.util.Date;

public class DadosSisaPernoite {

  private String dtHospedagem;
  private String nmSubprefeitura;
  private String nmDistrito;
  private String nmServico;
  private String nmTipoServico;

  public DadosSisaPernoite() {
  }

  public String getDtHospedagem() {
    return dtHospedagem;
  }

  public void setDtHospedagem(String dtHospedagem) {
    this.dtHospedagem = dtHospedagem;
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

  @Override
  public String toString() {
    return "SisaPernoiteData{" +
      "dtHospedagem=" + dtHospedagem +
      ", nmSubprefeitura='" + nmSubprefeitura + '\'' +
      ", nmDistrito='" + nmDistrito + '\'' +
      ", nmServico='" + nmServico + '\'' +
      ", nmTipoServico='" + nmTipoServico + '\'' +
      '}';
  }
}
