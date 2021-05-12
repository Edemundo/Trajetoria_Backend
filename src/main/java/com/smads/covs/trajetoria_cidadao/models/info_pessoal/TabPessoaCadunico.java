package com.smads.covs.trajetoria_cidadao.models.info_pessoal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TabPessoaCadunico {

  // Atributos usados em dados pessoais
  @Id
  private String numNisPessoaAtual;
  private String codFamiliarFam;
  private String codParentescoRfPessoa;

  // Atributos usados em saúde
  private String codDeficienciaMemb;
  private String indDefCegueiraMemb;
  private String indDefBaixaVisaoMemb;
  private String indDefSurdezProfundaMemb;
  private String indDefSurdezLeveMemb;
  private String indDefFisicaMemb;
  private String indDefMentalMemb;
  private String indDefSindromeDownMemb;
  private String indDefTranstornoMentalMemb;

  // Atributos usados em educação
  private String codSabeLerEscreverMemb;
  private String indFrequentaEscolaMemb;
  private String codCursoFrequentaMemb;
  private String codAnoSerieFrequentaMemb;
  private String codConcluiuFrequentouMemb;
  private String codCursoFrequentouPessoaMemb;
  private String codAnoSerieFrequentouMemb;

  // Atributos usados em financeiro
  private String indAjudaFamiliaMemb;
  private String indAjudaNaoMemb;
  private String codPrincipalTrabMemb;
  private String codTrabalho12MesesMemb;
  private String indAjudaEspecializadoMemb;
  private String indAjudaVizinhoMemb;
  private String indAjudaInstituicaoMemb;
  private String indAjudaOutraMemb;

  public TabPessoaCadunico() {
  }

  public TabPessoaCadunico(String numNisPessoaAtual) {
    this.numNisPessoaAtual = numNisPessoaAtual;
  }

  public TabPessoaCadunico(String codFamiliarFam, String codParentescoRfPessoa,
                           String codDeficienciaMemb, String indDefCegueiraMemb,
                           String indDefBaixaVisaoMemb, String indDefSurdezProfundaMemb,
                           String indDefSurdezLeveMemb, String indDefFisicaMemb, String indDefMentalMemb,
                           String indDefSindromeDownMemb, String indDefTranstornoMentalMemb, String codSabeLerEscreverMemb,
                           String indFrequentaEscolaMemb, String codCursoFrequentaMemb, String codAnoSerieFrequentaMemb,
                           String codConcluiuFrequentouMemb, String codCursoFrequentouPessoaMemb, String codAnoSerieFrequentouMemb,
                           String indAjudaFamiliaMemb, String indAjudaNaoMemb, String codPrincipalTrabMemb) {
    this.codFamiliarFam = codFamiliarFam;
    this.codParentescoRfPessoa = codParentescoRfPessoa;
    this.codDeficienciaMemb = codDeficienciaMemb;
    this.indDefCegueiraMemb = indDefCegueiraMemb;
    this.indDefBaixaVisaoMemb = indDefBaixaVisaoMemb;
    this.indDefSurdezProfundaMemb = indDefSurdezProfundaMemb;
    this.indDefSurdezLeveMemb = indDefSurdezLeveMemb;
    this.indDefFisicaMemb = indDefFisicaMemb;
    this.indDefMentalMemb = indDefMentalMemb;
    this.indDefSindromeDownMemb = indDefSindromeDownMemb;
    this.indDefTranstornoMentalMemb = indDefTranstornoMentalMemb;
    this.codSabeLerEscreverMemb = codSabeLerEscreverMemb;
    this.indFrequentaEscolaMemb = indFrequentaEscolaMemb;
    this.codCursoFrequentaMemb = codCursoFrequentaMemb;
    this.codAnoSerieFrequentaMemb = codAnoSerieFrequentaMemb;
    this.codConcluiuFrequentouMemb = codConcluiuFrequentouMemb;
    this.codCursoFrequentouPessoaMemb = codCursoFrequentouPessoaMemb;
    this.codAnoSerieFrequentouMemb = codAnoSerieFrequentouMemb;
    this.indAjudaFamiliaMemb = indAjudaFamiliaMemb;
    this.indAjudaNaoMemb = indAjudaNaoMemb;
    this.codPrincipalTrabMemb = codPrincipalTrabMemb;
  }

  public TabPessoaCadunico(String numNisPessoaAtual, String codFamiliarFam, String codParentescoRfPessoa,
                           String codDeficienciaMemb, String indDefCegueiraMemb,
                           String indDefBaixaVisaoMemb, String indDefSurdezProfundaMemb,
                           String indDefSurdezLeveMemb, String indDefFisicaMemb, String indDefMentalMemb,
                           String indDefSindromeDownMemb, String indDefTranstornoMentalMemb, String codSabeLerEscreverMemb,
                           String indFrequentaEscolaMemb, String codCursoFrequentaMemb, String codAnoSerieFrequentaMemb,
                           String codConcluiuFrequentouMemb, String codCursoFrequentouPessoaMemb, String codAnoSerieFrequentouMemb,
                           String indAjudaFamiliaMemb, String indAjudaNaoMemb, String codPrincipalTrabMemb) {
    this.numNisPessoaAtual = numNisPessoaAtual;
    this.codFamiliarFam = codFamiliarFam;
    this.codParentescoRfPessoa = codParentescoRfPessoa;
    this.codDeficienciaMemb = codDeficienciaMemb;
    this.indDefCegueiraMemb = indDefCegueiraMemb;
    this.indDefBaixaVisaoMemb = indDefBaixaVisaoMemb;
    this.indDefSurdezProfundaMemb = indDefSurdezProfundaMemb;
    this.indDefSurdezLeveMemb = indDefSurdezLeveMemb;
    this.indDefFisicaMemb = indDefFisicaMemb;
    this.indDefMentalMemb = indDefMentalMemb;
    this.indDefSindromeDownMemb = indDefSindromeDownMemb;
    this.indDefTranstornoMentalMemb = indDefTranstornoMentalMemb;
    this.codSabeLerEscreverMemb = codSabeLerEscreverMemb;
    this.indFrequentaEscolaMemb = indFrequentaEscolaMemb;
    this.codCursoFrequentaMemb = codCursoFrequentaMemb;
    this.codAnoSerieFrequentaMemb = codAnoSerieFrequentaMemb;
    this.codConcluiuFrequentouMemb = codConcluiuFrequentouMemb;
    this.codCursoFrequentouPessoaMemb = codCursoFrequentouPessoaMemb;
    this.codAnoSerieFrequentouMemb = codAnoSerieFrequentouMemb;
    this.indAjudaFamiliaMemb = indAjudaFamiliaMemb;
    this.indAjudaNaoMemb = indAjudaNaoMemb;
    this.codPrincipalTrabMemb = codPrincipalTrabMemb;
  }

  public String getNumNisPessoaAtual() {
    return numNisPessoaAtual;
  }

  public void setNumNisPessoaAtual(String numNisPessoaAtual) {
    this.numNisPessoaAtual = numNisPessoaAtual;
  }

  public String getCodFamiliarFam() {
    return codFamiliarFam;
  }

  public void setCodFamiliarFam(String codFamiliarFam) {
    this.codFamiliarFam = codFamiliarFam;
  }

  public String getCodParentescoRfPessoa() {
    return codParentescoRfPessoa;
  }

  public void setCodParentescoRfPessoa(String codParentescoRfPessoa) {
    this.codParentescoRfPessoa = codParentescoRfPessoa;
  }

  public String getCodDeficienciaMemb() {
    return codDeficienciaMemb;
  }

  public void setCodDeficienciaMemb(String codDeficienciaMemb) {
    this.codDeficienciaMemb = codDeficienciaMemb;
  }

  public String getIndDefCegueiraMemb() {
    return indDefCegueiraMemb;
  }

  public void setIndDefCegueiraMemb(String indDefCegueiraMemb) {
    this.indDefCegueiraMemb = indDefCegueiraMemb;
  }

  public String getIndDefBaixaVisaoMemb() {
    return indDefBaixaVisaoMemb;
  }

  public void setIndDefBaixaVisaoMemb(String indDefBaixaVisaoMemb) {
    this.indDefBaixaVisaoMemb = indDefBaixaVisaoMemb;
  }

  public String getIndDefSurdezProfundaMemb() {
    return indDefSurdezProfundaMemb;
  }

  public void setIndDefSurdezProfundaMemb(String indDefSurdezProfundaMemb) {
    this.indDefSurdezProfundaMemb = indDefSurdezProfundaMemb;
  }

  public String getIndDefSurdezLeveMemb() {
    return indDefSurdezLeveMemb;
  }

  public void setIndDefSurdezLeveMemb(String indDefSurdezLeveMemb) {
    this.indDefSurdezLeveMemb = indDefSurdezLeveMemb;
  }

  public String getIndDefFisicaMemb() {
    return indDefFisicaMemb;
  }

  public void setIndDefFisicaMemb(String indDefFisicaMemb) {
    this.indDefFisicaMemb = indDefFisicaMemb;
  }

  public String getIndDefMentalMemb() {
    return indDefMentalMemb;
  }

  public void setIndDefMentalMemb(String indDefMentalMemb) {
    this.indDefMentalMemb = indDefMentalMemb;
  }

  public String getIndDefSindromeDownMemb() {
    return indDefSindromeDownMemb;
  }

  public void setIndDefSindromeDownMemb(String indDefSindromeDownMemb) {
    this.indDefSindromeDownMemb = indDefSindromeDownMemb;
  }

  public String getIndDefTranstornoMentalMemb() {
    return indDefTranstornoMentalMemb;
  }

  public void setIndDefTranstornoMentalMemb(String indDefTranstornoMentalMemb) {
    this.indDefTranstornoMentalMemb = indDefTranstornoMentalMemb;
  }

  public String getCodSabeLerEscreverMemb() {
    return codSabeLerEscreverMemb;
  }

  public void setCodSabeLerEscreverMemb(String codSabeLerEscreverMemb) {
    this.codSabeLerEscreverMemb = codSabeLerEscreverMemb;
  }

  public String getIndFrequentaEscolaMemb() {
    return indFrequentaEscolaMemb;
  }

  public void setIndFrequentaEscolaMemb(String indFrequentaEscolaMemb) {
    this.indFrequentaEscolaMemb = indFrequentaEscolaMemb;
  }

  public String getCodCursoFrequentaMemb() {
    return codCursoFrequentaMemb;
  }

  public void setCodCursoFrequentaMemb(String codCursoFrequentaMemb) {
    this.codCursoFrequentaMemb = codCursoFrequentaMemb;
  }

  public String getCodAnoSerieFrequentaMemb() {
    return codAnoSerieFrequentaMemb;
  }

  public void setCodAnoSerieFrequentaMemb(String codAnoSerieFrequentaMemb) {
    this.codAnoSerieFrequentaMemb = codAnoSerieFrequentaMemb;
  }

  public String getCodConcluiuFrequentouMemb() {
    return codConcluiuFrequentouMemb;
  }

  public void setCodConcluiuFrequentouMemb(String codConcluiuFrequentouMemb) {
    this.codConcluiuFrequentouMemb = codConcluiuFrequentouMemb;
  }

  public String getCodCursoFrequentouPessoaMemb() {
    return codCursoFrequentouPessoaMemb;
  }

  public void setCodCursoFrequentouPessoaMemb(String codCursoFrequentouPessoaMemb) {
    this.codCursoFrequentouPessoaMemb = codCursoFrequentouPessoaMemb;
  }

  public String getCodAnoSerieFrequentouMemb() {
    return codAnoSerieFrequentouMemb;
  }

  public void setCodAnoSerieFrequentouMemb(String codAnoSerieFrequentouMemb) {
    this.codAnoSerieFrequentouMemb = codAnoSerieFrequentouMemb;
  }

  public String getIndAjudaFamiliaMemb() {
    return indAjudaFamiliaMemb;
  }

  public void setIndAjudaFamiliaMemb(String indAjudaFamiliaMemb) {
    this.indAjudaFamiliaMemb = indAjudaFamiliaMemb;
  }

  public String getIndAjudaNaoMemb() {
    return indAjudaNaoMemb;
  }

  public void setIndAjudaNaoMemb(String indAjudaNaoMemb) {
    this.indAjudaNaoMemb = indAjudaNaoMemb;
  }

  public String getCodPrincipalTrabMemb() {
    return codPrincipalTrabMemb;
  }

  public void setCodPrincipalTrabMemb(String codPrincipalTrabMemb) {
    this.codPrincipalTrabMemb = codPrincipalTrabMemb;
  }

  @Override
  public String toString() {
    return "TabPessoaCadunico{" +
      "numNisPessoaAtual='" + numNisPessoaAtual + '\'' +
      ", codFamiliarFam='" + codFamiliarFam + '\'' +
      ", codParentescoRfPessoa='" + codParentescoRfPessoa + '\'' +
      ", codDeficienciaMemb='" + codDeficienciaMemb + '\'' +
      ", indDefCegueiraMemb='" + indDefCegueiraMemb + '\'' +
      ", indDefBaixaVisaoMemb='" + indDefBaixaVisaoMemb + '\'' +
      ", indDefSurdezProfundaMemb='" + indDefSurdezProfundaMemb + '\'' +
      ", indDefSurdezLeveMemb='" + indDefSurdezLeveMemb + '\'' +
      ", indDefFisicaMemb='" + indDefFisicaMemb + '\'' +
      ", indDefMentalMemb='" + indDefMentalMemb + '\'' +
      ", indDefSindromeDownMemb='" + indDefSindromeDownMemb + '\'' +
      ", indDefTranstornoMentalMemb='" + indDefTranstornoMentalMemb + '\'' +
      ", codSabeLerEscreverMemb='" + codSabeLerEscreverMemb + '\'' +
      ", indFrequentaEscolaMemb='" + indFrequentaEscolaMemb + '\'' +
      ", codCursoFrequentaMemb='" + codCursoFrequentaMemb + '\'' +
      ", codAnoSerieFrequentaMemb='" + codAnoSerieFrequentaMemb + '\'' +
      ", codConcluiuFrequentouMemb='" + codConcluiuFrequentouMemb + '\'' +
      ", codCursoFrequentouPessoaMemb='" + codCursoFrequentouPessoaMemb + '\'' +
      ", codAnoSerieFrequentouMemb='" + codAnoSerieFrequentouMemb + '\'' +
      ", indAjudaFamiliaMemb='" + indAjudaFamiliaMemb + '\'' +
      ", indAjudaNaoMemb='" + indAjudaNaoMemb + '\'' +
      ", codPrincipalTrabMemb='" + codPrincipalTrabMemb + '\'' +
      '}';
  }
}
