package com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DimTipoServico {

  @Id
  private Integer cdTipoServico;
  private String dcTipoSimplificado;
  private String dcTipoServico;

  public DimTipoServico() {
  }

  public Integer getCdTipoServico() {
    return cdTipoServico;
  }

  public void setCdTipoServico(Integer cdTipoServico) {
    this.cdTipoServico = cdTipoServico;
  }

  public String getDcTipoSimplificado() {
    return dcTipoSimplificado;
  }

  public void setDcTipoSimplificado(String dcTipoSimplificado) {
    this.dcTipoSimplificado = dcTipoSimplificado;
  }

  public String getDcTipoServico() {
    return dcTipoServico;
  }

  public void setDcTipoServico(String dcTipoServico) {
    this.dcTipoServico = dcTipoServico;
  }

  @Override
  public String toString() {
    return "DimTipoServico{" +
      "cdTipoServico=" + cdTipoServico +
      ", dcTipoSimplificado='" + dcTipoSimplificado + '\'' +
      ", dcTipoServico='" + dcTipoServico + '\'' +
      '}';
  }
}
