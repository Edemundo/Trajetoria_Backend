package com.smads.covs.trajetoria_cidadao.models.cidadao_detalhado;

import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaVinculado;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaPernoite;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSiscr;

import java.math.BigInteger;
import java.util.List;

public class CidadaoDetalhado {

    // DW - Tabela DimCidadao e tabs de referência
    private BigInteger ciCidadao;
    private BigInteger nrCpf;
    private BigInteger cdNis;
    private Integer ageCidadao;
    private String nmCidadao;
    private String nmMae;
    private String dcRaca;
    private String dcTipoSexo;
    private String dcSitCidadao;
    private String dtNasc;
    private String nmPais;

    // DW - Tabela Pessoa_Cadunico
    private String descResponsavel;
    private String indDefBaixaVisaoMemb;
    private String indDefSindromeDownMemb;
    private String indDefSurdezProfundaMemb;
    private String codDeficienciaMemb;
    private String indDefTranstornoMentalMemb;
    private String indDefMentalMemb;
    private String indDefSurdezLeveMemb;
    private String indDefFisicaMemb;
    private String indDefCegueiraMemb;
    private String descAnoSerieFrequentaMemb;
    private String descCursoFrequentaMemb;
    private String descCursoFrequentouPessoaMemb;
    private String codConcluiuFrequentouMemb;
    private String descFrequentaEscolaMemb;
    private String descAnoSerieFrequentouMemb;
    private String codSabeLerEscreverMemb;
    private String descTrabMembro;
    private String indAjudaNaoMemb;
    private String indAjudaFamiliaMemb;
    private String codTrabalhoDozeMesesMemb;
    private String indAjudaEspecializadoMemb;
    private String indAjudaVizinhoMemb;
    private String indAjudaInstituicaoMemb;
    private String indAjudaOutraMemb;

    // DW - Tabela Familia_Cadunico
    private String codFamiliarFam;
    private String vlrRendaMediaFam;
    private String descEndereco;

    // Dados SMIT
    private List<DadosSisaVinculado> lstSisa;
    private List<DadosSisaPernoite> lstSisaPernoite;
    private List<DadosSiscr> lstSiscr;

    public CidadaoDetalhado() {
    }

    public List<DadosSisaVinculado> getLstSisa() {
        return lstSisa;
    }

    public void setLstSisa(List<DadosSisaVinculado> lstSisa) {
        this.lstSisa = lstSisa;
    }

    public List<DadosSisaPernoite> getLstSisaPernoite() {
        return lstSisaPernoite;
    }

    public void setLstSisaPernoite(List<DadosSisaPernoite> lstSisaPernoite) {
        this.lstSisaPernoite = lstSisaPernoite;
    }

    public List<DadosSiscr> getLstSiscr() {
        return lstSiscr;
    }

    public void setLstSiscr(List<DadosSiscr> lstSiscr) {
        this.lstSiscr = lstSiscr;
    }

    public BigInteger getCiCidadao() {
        return ciCidadao;
    }

    public void setCiCidadao(BigInteger ciCidadao) {
        this.ciCidadao = ciCidadao;
    }

    public BigInteger getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(BigInteger nrCpf) {
        this.nrCpf = nrCpf;
    }

    public BigInteger getCdNis() {
        return cdNis;
    }

    public void setCdNis(BigInteger cdNis) {
        this.cdNis = cdNis;
    }

    public Integer getAgeCidadao() {
        return ageCidadao;
    }

    public void setAgeCidadao(Integer ageCidadao) {
        this.ageCidadao = ageCidadao;
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

    public String getDcRaca() {
        return dcRaca;
    }

    public void setDcRaca(String dcRaca) {
        this.dcRaca = dcRaca;
    }

    public String getDcTipoSexo() {
        return dcTipoSexo;
    }

    public void setDcTipoSexo(String dcTipoSexo) {
        this.dcTipoSexo = dcTipoSexo;
    }

    public String getDcSitCidadao() {
        return dcSitCidadao;
    }

    public void setDcSitCidadao(String dcSitCidadao) {
        this.dcSitCidadao = dcSitCidadao;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getDescResponsavel() {
        return descResponsavel;
    }

    public void setDescResponsavel(String descResponsavel) {
        this.descResponsavel = descResponsavel;
    }

    public String getIndDefBaixaVisaoMemb() {
        return indDefBaixaVisaoMemb;
    }

    public void setIndDefBaixaVisaoMemb(String indDefBaixaVisaoMemb) {
        this.indDefBaixaVisaoMemb = indDefBaixaVisaoMemb;
    }

    public String getIndDefSindromeDownMemb() {
        return indDefSindromeDownMemb;
    }

    public void setIndDefSindromeDownMemb(String indDefSindromeDownMemb) {
        this.indDefSindromeDownMemb = indDefSindromeDownMemb;
    }

    public String getIndAjudaFamiliaMemb() {
      return indAjudaFamiliaMemb;
    }

    public void setIndAjudaFamiliaMemb(String indAjudaFamiliaMemb) {
      this.indAjudaFamiliaMemb = indAjudaFamiliaMemb;
    }

    public String getIndDefSurdezProfundaMemb() {
        return indDefSurdezProfundaMemb;
    }

    public void setIndDefSurdezProfundaMemb(String indDefSurdezProfundaMemb) {
        this.indDefSurdezProfundaMemb = indDefSurdezProfundaMemb;
    }

    public String getCodDeficienciaMemb() {
        return codDeficienciaMemb;
    }

    public void setCodDeficienciaMemb(String codDeficienciaMemb) {
        this.codDeficienciaMemb = codDeficienciaMemb;
    }

    public String getIndDefTranstornoMentalMemb() {
        return indDefTranstornoMentalMemb;
    }

    public void setIndDefTranstornoMentalMemb(String indDefTranstornoMentalMemb) {
        this.indDefTranstornoMentalMemb = indDefTranstornoMentalMemb;
    }

    public String getIndDefMentalMemb() {
        return indDefMentalMemb;
    }

    public void setIndDefMentalMemb(String indDefMentalMemb) {
        this.indDefMentalMemb = indDefMentalMemb;
    }

    public String getIndDefSurdezLeveMemb() {
        return indDefSurdezLeveMemb;
    }

    public void setIndDefSurdezLeveMemb(String indDefSurdezLeveMemb) {
        this.indDefSurdezLeveMemb = indDefSurdezLeveMemb;
    }

    public String getNmPais() {
      return nmPais;
    }

    public void setNmPais(String nmPais) {
      this.nmPais = nmPais;
    }

    public String getIndDefFisicaMemb() {
        return indDefFisicaMemb;
    }

    public void setIndDefFisicaMemb(String indDefFisicaMemb) {
        this.indDefFisicaMemb = indDefFisicaMemb;
    }

    public String getIndDefCegueiraMemb() {
        return indDefCegueiraMemb;
    }

    public void setIndDefCegueiraMemb(String indDefCegueiraMemb) {
        this.indDefCegueiraMemb = indDefCegueiraMemb;
    }

    public String getDescAnoSerieFrequentaMemb() {
        return descAnoSerieFrequentaMemb;
    }

    public void setDescAnoSerieFrequentaMemb(String descAnoSerieFrequentaMemb) {
        this.descAnoSerieFrequentaMemb = descAnoSerieFrequentaMemb;
    }

    public String getDescCursoFrequentaMemb() {
        return descCursoFrequentaMemb;
    }

    public void setDescCursoFrequentaMemb(String descCursoFrequentaMemb) {
        this.descCursoFrequentaMemb = descCursoFrequentaMemb;
    }

    public String getDescCursoFrequentouPessoaMemb() {
        return descCursoFrequentouPessoaMemb;
    }

    public void setDescCursoFrequentouPessoaMemb(String descCursoFrequentouPessoaMemb) {
        this.descCursoFrequentouPessoaMemb = descCursoFrequentouPessoaMemb;
    }

    public String getCodConcluiuFrequentouMemb() {
        return codConcluiuFrequentouMemb;
    }

    public void setCodConcluiuFrequentouMemb(String codConcluiuFrequentouMemb) {
        this.codConcluiuFrequentouMemb = codConcluiuFrequentouMemb;
    }

    public String getDescFrequentaEscolaMemb() {
        return descFrequentaEscolaMemb;
    }

    public void setDescFrequentaEscolaMemb(String descFrequentaEscolaMemb) {
        this.descFrequentaEscolaMemb = descFrequentaEscolaMemb;
    }

    public String getDescAnoSerieFrequentouMemb() {
        return descAnoSerieFrequentouMemb;
    }

    public void setDescAnoSerieFrequentouMemb(String descAnoSerieFrequentouMemb) {
        this.descAnoSerieFrequentouMemb = descAnoSerieFrequentouMemb;
    }

    public String getCodSabeLerEscreverMemb() {
        return codSabeLerEscreverMemb;
    }

    public void setCodSabeLerEscreverMemb(String codSabeLerEscreverMemb) {
        this.codSabeLerEscreverMemb = codSabeLerEscreverMemb;
    }

    public String getDescTrabMembro() {
        return descTrabMembro;
    }

    public void setDescTrabMembro(String descTrabMembro) {
        this.descTrabMembro = descTrabMembro;
    }

    public String getIndAjudaNaoMemb() {
        return indAjudaNaoMemb;
    }

    public void setIndAjudaNaoMemb(String indAjudaNaoMemb) {
        this.indAjudaNaoMemb = indAjudaNaoMemb;
    }

    public String getCodFamiliarFam() {
        return codFamiliarFam;
    }

    public void setCodFamiliarFam(String codFamiliarFam) {
        this.codFamiliarFam = codFamiliarFam;
    }

    public String getVlrRendaMediaFam() {
        return vlrRendaMediaFam;
    }

    public void setVlrRendaMediaFam(String vlrRendaMediaFam) {
        this.vlrRendaMediaFam = vlrRendaMediaFam;
    }

    public String getDescEndereco() {
        return descEndereco;
    }

    public void setDescEndereco(String descEndereco) {
        this.descEndereco = descEndereco;
    }

    public String getCodTrabalhoDozeMesesMemb() {
      return codTrabalhoDozeMesesMemb;
    }

    public void setCodTrabalhoDozeMesesMemb(String codTrabalhoDozeMesesMemb) {
      this.codTrabalhoDozeMesesMemb = codTrabalhoDozeMesesMemb;
    }

    public String getIndAjudaEspecializadoMemb() {
      return indAjudaEspecializadoMemb;
    }

    public void setIndAjudaEspecializadoMemb(String indAjudaEspecializadoMemb) {
      this.indAjudaEspecializadoMemb = indAjudaEspecializadoMemb;
    }

    public String getIndAjudaVizinhoMemb() {
      return indAjudaVizinhoMemb;
    }

    public void setIndAjudaVizinhoMemb(String indAjudaVizinhoMemb) {
      this.indAjudaVizinhoMemb = indAjudaVizinhoMemb;
    }

    public String getIndAjudaInstituicaoMemb() {
      return indAjudaInstituicaoMemb;
    }

    public void setIndAjudaInstituicaoMemb(String indAjudaInstituicaoMemb) {
      this.indAjudaInstituicaoMemb = indAjudaInstituicaoMemb;
    }

    public String getIndAjudaOutraMemb() {
      return indAjudaOutraMemb;
    }

    public void setIndAjudaOutraMemb(String indAjudaOutraMemb) {
      this.indAjudaOutraMemb = indAjudaOutraMemb;
    }

  @Override
  public String toString() {
    return "CidadaoDetalhado{" +
      "ciCidadao=" + ciCidadao +
      ", nrCpf=" + nrCpf +
      ", cdNis=" + cdNis +
      ", ageCidadao=" + ageCidadao +
      ", nmCidadao='" + nmCidadao + '\'' +
      ", nmMae='" + nmMae + '\'' +
      ", dcRaca='" + dcRaca + '\'' +
      ", dcTipoSexo='" + dcTipoSexo + '\'' +
      ", dcSitCidadao='" + dcSitCidadao + '\'' +
      ", dtNasc='" + dtNasc + '\'' +
      ", nmPais='" + nmPais + '\'' +
      ", descResponsavel='" + descResponsavel + '\'' +
      ", indDefBaixaVisaoMemb='" + indDefBaixaVisaoMemb + '\'' +
      ", indDefSindromeDownMemb='" + indDefSindromeDownMemb + '\'' +
      ", indDefSurdezProfundaMemb='" + indDefSurdezProfundaMemb + '\'' +
      ", codDeficienciaMemb='" + codDeficienciaMemb + '\'' +
      ", indDefTranstornoMentalMemb='" + indDefTranstornoMentalMemb + '\'' +
      ", indDefMentalMemb='" + indDefMentalMemb + '\'' +
      ", indDefSurdezLeveMemb='" + indDefSurdezLeveMemb + '\'' +
      ", indDefFisicaMemb='" + indDefFisicaMemb + '\'' +
      ", indDefCegueiraMemb='" + indDefCegueiraMemb + '\'' +
      ", descAnoSerieFrequentaMemb='" + descAnoSerieFrequentaMemb + '\'' +
      ", descCursoFrequentaMemb='" + descCursoFrequentaMemb + '\'' +
      ", descCursoFrequentouPessoaMemb='" + descCursoFrequentouPessoaMemb + '\'' +
      ", codConcluiuFrequentouMemb='" + codConcluiuFrequentouMemb + '\'' +
      ", descFrequentaEscolaMemb='" + descFrequentaEscolaMemb + '\'' +
      ", descAnoSerieFrequentouMemb='" + descAnoSerieFrequentouMemb + '\'' +
      ", codSabeLerEscreverMemb='" + codSabeLerEscreverMemb + '\'' +
      ", descTrabMembro='" + descTrabMembro + '\'' +
      ", indAjudaNaoMemb='" + indAjudaNaoMemb + '\'' +
      ", indAjudaFamiliaMemb='" + indAjudaFamiliaMemb + '\'' +
      ", codTrabalhoDozeMesesMemb='" + codTrabalhoDozeMesesMemb + '\'' +
      ", indAjudaEspecializadoMemb='" + indAjudaEspecializadoMemb + '\'' +
      ", indAjudaVizinhoMemb='" + indAjudaVizinhoMemb + '\'' +
      ", indAjudaInstituicaoMemb='" + indAjudaInstituicaoMemb + '\'' +
      ", indAjudaOutraMemb='" + indAjudaOutraMemb + '\'' +
      ", codFamiliarFam='" + codFamiliarFam + '\'' +
      ", vlrRendaMediaFam='" + vlrRendaMediaFam + '\'' +
      ", descEndereco='" + descEndereco + '\'' +
      ", lstSisa=" + lstSisa +
      ", lstSisaPernoite=" + lstSisaPernoite +
      ", lstSiscr=" + lstSiscr +
      '}';
  }
}
