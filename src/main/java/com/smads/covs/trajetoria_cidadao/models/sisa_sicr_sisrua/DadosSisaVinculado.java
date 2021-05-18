package com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua;

import java.math.BigInteger;
import java.util.Date;

public class DadosSisaVinculado {

    private String nmDistrito;
    private String nmSubprefeitura;
    private String nmServico;
    private String dcTipoServico;
    private String dcOrigemProcura;
    private String dcMotivoProcura;
    private String dcMotivoDesligamento;
    private String dtVinculacao;
    private String dtDesligamento;
    private Long tempoPermanencia;

    public DadosSisaVinculado() {
    }

    public String getNmDistrito() {
      return nmDistrito;
    }

    public void setNmDistrito(String nmDistrito) {
      this.nmDistrito = nmDistrito;
    }

    public String getNmSubprefeitura() {
      return nmSubprefeitura;
    }

    public void setNmSubprefeitura(String nmSubprefeitura) {
      this.nmSubprefeitura = nmSubprefeitura;
    }

    public String getNmServico() {
      return nmServico;
    }

    public void setNmServico(String nmServico) {
      this.nmServico = nmServico;
    }

    public String getDcTipoServico() {
      return dcTipoServico;
    }

    public void setDcTipoServico(String dcTipoServico) {
      this.dcTipoServico = dcTipoServico;
    }

    public String getDcOrigemProcura() {
      return dcOrigemProcura;
    }

    public void setDcOrigemProcura(String dcOrigemProcura) {
      this.dcOrigemProcura = dcOrigemProcura;
    }

    public String getDcMotivoProcura() {
      return dcMotivoProcura;
    }

    public void setDcMotivoProcura(String dcMotivoProcura) {
      this.dcMotivoProcura = dcMotivoProcura;
    }

    public String getDcMotivoDesligamento() {
      return dcMotivoDesligamento;
    }

    public void setDcMotivoDesligamento(String dcMotivoDesligamento) {
      this.dcMotivoDesligamento = dcMotivoDesligamento;
    }

    public String getDtVinculacao() {
      return dtVinculacao;
    }

    public void setDtVinculacao(String dtVinculacao) {
      this.dtVinculacao = dtVinculacao;
    }

    public String getDtDesligamento() {
      return dtDesligamento;
    }

    public void setDtDesligamento(String dtDesligamento) {
      this.dtDesligamento = dtDesligamento;
    }

    public Long getTempoPermanencia() {
      return tempoPermanencia;
    }

    public void setTempoPermanencia(Long tempoPermanencia) {
      this.tempoPermanencia = tempoPermanencia;
    }

    @Override
    public String toString() {
      return "DadosSisaVinculado{" +
        "nmDistrito='" + nmDistrito + '\'' +
        ", nmSubprefeitura='" + nmSubprefeitura + '\'' +
        ", nmServico='" + nmServico + '\'' +
        ", dcTipoServico='" + dcTipoServico + '\'' +
        ", dcOrigemProcura='" + dcOrigemProcura + '\'' +
        ", dcMotivoProcura='" + dcMotivoProcura + '\'' +
        ", dcMotivoDesligamento='" + dcMotivoDesligamento + '\'' +
        ", dtVinculacao='" + dtVinculacao + '\'' +
        ", dtDesligamento='" + dtDesligamento + '\'' +
        ", tempoPermanencia=" + tempoPermanencia +
        '}';
    }
}
