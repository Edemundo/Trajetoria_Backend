package com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua;

public class DadosSiscr {

  private String nmCentroAssistencial;
  private String nmSubprefeitura;
  private String dtAtualizacao;
  private String dtPreAtendimento;
  private String dcTipoProcura;
  private String dcMotivoProcura;
  private String dcEncaminhamento;
  private String dcTipoProvidencia;

  public DadosSiscr() {
  }

  public String getNmCentroAssistencial() {
    return nmCentroAssistencial;
  }

  public void setNmCentroAssistencial(String nmCentroAssistencial) {
    this.nmCentroAssistencial = nmCentroAssistencial;
  }

  public String getNmSubprefeitura() {
    return nmSubprefeitura;
  }

  public void setNmSubprefeitura(String nmSubprefeitura) {
    this.nmSubprefeitura = nmSubprefeitura;
  }

  public String getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(String dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

  public String getDtPreAtendimento() {
    return dtPreAtendimento;
  }

  public void setDtPreAtendimento(String dtPreAtendimento) {
    this.dtPreAtendimento = dtPreAtendimento;
  }

  public String getDcTipoProcura() {
    return dcTipoProcura;
  }

  public void setDcTipoProcura(String dcTipoProcura) {
    this.dcTipoProcura = dcTipoProcura;
  }

  public String getDcMotivoProcura() {
    return dcMotivoProcura;
  }

  public void setDcMotivoProcura(String dcMotivoProcura) {
    this.dcMotivoProcura = dcMotivoProcura;
  }

  public String getDcEncaminhamento() {
    return dcEncaminhamento;
  }

  public void setDcEncaminhamento(String dcEncaminhamento) {
    this.dcEncaminhamento = dcEncaminhamento;
  }

  public String getDcTipoProvidencia() {
    return dcTipoProvidencia;
  }

  public void setDcTipoProvidencia(String dcTipoProvidencia) {
    this.dcTipoProvidencia = dcTipoProvidencia;
  }

  @Override
  public String toString() {
    return "DadosSiscr{" +
      "nmCentroAssistencial='" + nmCentroAssistencial + '\'' +
      ", nmSubprefeitura='" + nmSubprefeitura + '\'' +
      ", dtAtualizacao='" + dtAtualizacao + '\'' +
      ", dtPreAtendimento='" + dtPreAtendimento + '\'' +
      ", dcTipoProcura='" + dcTipoProcura + '\'' +
      ", dcMotivoProcura='" + dcMotivoProcura + '\'' +
      ", dcEncaminhamento='" + dcEncaminhamento + '\'' +
      ", dcTipoProvidencia='" + dcTipoProvidencia + '\'' +
      '}';
  }
}
