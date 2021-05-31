package com.smads.covs.trajetoria_cidadao.controllers;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.*;
import com.smads.covs.trajetoria_cidadao.services.info_pessoal.*;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

@Controller
public class PessoalSaudeFinancasEducacaoDataController {

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private final ObjectMapper mapper = new ObjectMapper();
    private final SimpleDateFormat formatadorDatas = new SimpleDateFormat("dd-MM-yyyy");

    private final DimRacaService dimRacaService;
    private final DimTipoSexoService dimTipoSexoService;
    private final DimPaisOrigemService dimPaisOrigemService;
    private final DimSituacaoCidadaoService dimSituacaoCidadaoService;
    private final TabPessoaCadunicoService tabPessoaCadunicoService;
    private final TabFamiliaCadunicoService tabFamiliaCadunicoService;

    public PessoalSaudeFinancasEducacaoDataController(DimRacaService dimRacaService, DimTipoSexoService dimTipoSexoService,
                                                      DimPaisOrigemService dimPaisOrigemService, DimSituacaoCidadaoService dimSituacaoCidadaoService,
                                                      TabPessoaCadunicoService tabPessoaCadunicoService, TabFamiliaCadunicoService tabFamiliaCadunicoService) {
        this.dimRacaService = dimRacaService;
        this.dimTipoSexoService = dimTipoSexoService;
        this.dimPaisOrigemService = dimPaisOrigemService;
        this.dimSituacaoCidadaoService = dimSituacaoCidadaoService;
        this.tabPessoaCadunicoService = tabPessoaCadunicoService;
        this.tabFamiliaCadunicoService = tabFamiliaCadunicoService;
    }

    public Map<String, Object> PSFEDataController(DimCidadao dimCidadao) throws JsonProcessingException, JSONException {

        Map<String, Object> strPersonalData = RacaSexoPaisSituacao(dimCidadao);
        Map<String, Object> strPSFEData = new HashMap<>(strPersonalData);

        Map<String, Object> strPesCad = PessoaCadunico(dimCidadao);
        if(strPesCad.isEmpty()){}
        else{
            strPSFEData.putAll(strPesCad);
            Map<String, Object> strFamCad = FamiliaCadunico(strPesCad);
            if(strFamCad.isEmpty()){}
            else{
                strPSFEData.putAll(strFamCad);
            }
        }

        // Remover o que não será utilizado no frontend
        JSONObject jsonObjStrPSFEData = new JSONObject(strPSFEData);

        // Dados Escolares
        jsonObjStrPSFEData.remove("indFrequentaEscolaMemb");
        jsonObjStrPSFEData.remove("codAnoSerieFrequentaMemb");
        jsonObjStrPSFEData.remove("codCursoFrequentaMemb");
        jsonObjStrPSFEData.remove("codAnoSerieFrequentouMemb");
        jsonObjStrPSFEData.remove("codCursoFrequentouPessoaMemb");
        jsonObjStrPSFEData.remove("nomTipLogradouroFam");
        // Dados Pessoais
        jsonObjStrPSFEData.remove("ciTipoSexo");
        jsonObjStrPSFEData.remove("ciRaca");
        jsonObjStrPSFEData.remove("ciRacaObservada");
        jsonObjStrPSFEData.remove("ciSitCidadao");
        jsonObjStrPSFEData.remove("ciPaisOrigem");
        jsonObjStrPSFEData.remove("indTrabalhoInfantilFam");
        jsonObjStrPSFEData.remove("codPrincipalTrabMemb");
        jsonObjStrPSFEData.remove("codParentescoRfPessoa");
        jsonObjStrPSFEData.remove("numNisPessoaAtual");
        // Endereço
        jsonObjStrPSFEData.remove("nomLogradouroFam");
        jsonObjStrPSFEData.remove("nomLocalidadeFam");
        jsonObjStrPSFEData.remove("nomTituloLogradouroFam");
        jsonObjStrPSFEData.remove("numLogradouroFam");
        strPSFEData = mapper.readValue(jsonObjStrPSFEData.toString(), HashMap.class);

        return strPSFEData;
    }

    public Map<String, Object> RacaSexoPaisSituacao(DimCidadao dimCidadao) throws JsonProcessingException, JSONException {

        String strDimCidadao = objectWriter.writeValueAsString(dimCidadao);
        Map<String, Object> mapStrDimCidadao = mapper.readValue(strDimCidadao, Map.class);
        Map<String, Object> strPersonalData = new HashMap<String, Object>(mapStrDimCidadao);

        DimRaca racaCidadao = findRacaCidadao(dimCidadao.getCiRacaObservada());
        if(racaCidadao != null){
            String strRacaCidadao = objectWriter.writeValueAsString(racaCidadao);
            Map<String, Object> mapStrRacaCidadao = mapper.readValue(strRacaCidadao, Map.class);
            strPersonalData.putAll(mapStrRacaCidadao);
        }

        DimTipoSexo sexoCidadao = findTipoSexo(dimCidadao.getCiTipoSexo());
        if(sexoCidadao != null){
            String strSexoCidadao = objectWriter.writeValueAsString(sexoCidadao);
            Map<String, Object> mapStrSexoCidadao = mapper.readValue(strSexoCidadao, Map.class);
            strPersonalData.putAll(mapStrSexoCidadao);
        }

        DimPaisOrigem paisOrigem = findPaisOrigem(dimCidadao.getCiPaisOrigem());
        if(paisOrigem != null){
            String strPaisOrigem = objectWriter.writeValueAsString(paisOrigem);
            Map<String, Object> mapStrPaisOrigem = mapper.readValue(strPaisOrigem, Map.class);
            strPersonalData.putAll(mapStrPaisOrigem);
        }

        JSONObject jsonObjCidadaoDetalhado = new JSONObject(strPersonalData);

        // Formatando a data para dd/mm/aaaa
        Long resultDtNasc = jsonObjCidadaoDetalhado.getLong("dtNasc");
        Date dtResultDtNasc = new Date(resultDtNasc);
        String strDtResultDtNasc = formatadorDatas.format(dtResultDtNasc);

        // Calculando a idade
        LocalDate dtHoje = LocalDate.now();
        LocalDate ldDtNasc = dtResultDtNasc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer ageCidadao = Period.between(ldDtNasc, dtHoje).getYears();

        // Modificando a situação do cidadão
        String situacaoCidadao;
        if(dimCidadao.getCiSitCidadao() == 2 || dimCidadao.getCiSitCidadao() == 3){
          situacaoCidadao = "Vivo";
        }
        else{
          situacaoCidadao = "Falecido";
        }

        jsonObjCidadaoDetalhado.put("dcSitCidadao", situacaoCidadao);
        jsonObjCidadaoDetalhado.put("dtNasc", strDtResultDtNasc);
        jsonObjCidadaoDetalhado.put("ageCidadao",ageCidadao);

        strPersonalData = mapper.readValue(jsonObjCidadaoDetalhado.toString(), HashMap.class);
        return strPersonalData;
    }

    public Map<String, Object> PessoaCadunico(DimCidadao dimCidadao) throws JsonProcessingException, JSONException {

        Map<String, Object> strHealthData = new HashMap<String, Object>();

        if(dimCidadao.getCdNis() != null){
            String strCdNis = dimCidadao.getCdNis().toString();
            TabPessoaCadunico tabPessoaCadunico = findPessoaCadunico(strCdNis);
            if(tabPessoaCadunico != null){
                String strTabPessoaCadunico = objectWriter.writeValueAsString(tabPessoaCadunico);
                Map<String, Object> mapStrTabPessoaCadunico = mapper.readValue(strTabPessoaCadunico, Map.class);
                strHealthData.putAll(mapStrTabPessoaCadunico);

                JSONObject jsonObjHealthData = new JSONObject(strHealthData);

                // Calculando trabalho do cidadão
                String strCodTrabMemb = jsonObjHealthData.getString("codPrincipalTrabMemb");
                String descTrabMemb;
                if(strCodTrabMemb != null && strCodTrabMemb != "null") {
                    Integer codTrabMemb = Integer.parseInt(strCodTrabMemb);

                    switch(codTrabMemb) {
                        case 1:
                            descTrabMemb = "TRABALHADOR POR CONTA PROPRIA (BICO, AUTONOMO)";
                            break;

                        case 2:
                            descTrabMemb = "TRABALHADOR TEMPORARIO EM AREA RURAL";
                            break;

                        case 3:
                            descTrabMemb = "EMPREGADO SEM CARTEIRA DE TRABALHO ASSINADA";
                            break;

                        case 4:
                            descTrabMemb = "EMPREGO COM CARTEIRA DE TRABALHO ASSINADA";
                            break;

                        case 5:
                            descTrabMemb = "TRABALHADOR DOMESTICO SEM CARTEIRA DE TRABALHO ASSINADA";
                            break;

                        case 6:
                            descTrabMemb = "TRABALHADOR DOMESTICO COM CARTEIRA DE TRABALHO ASSINADA";
                            break;

                        case 7:
                            descTrabMemb = "TRABALHADOR NAO-REMUNERADO";
                            break;

                        case 8:
                            descTrabMemb = "MILITAR OU SERVIDOR PUBLICO";
                            break;

                        case 9:
                            descTrabMemb = "EMPREGADOR";
                            break;

                        case 10:
                            descTrabMemb = "ESTAGIARIO";
                            break;

                        case 11:
                            descTrabMemb = "DESEMPREGADO / NÃO INFORMADO";
                            break;

                        default:
                            descTrabMemb = "DESEMPREGADO / NÃO INFORMADO";
                    }
                } else {
                    descTrabMemb = "DESEMPREGADO / NÃO INFORMADO";
                }

                jsonObjHealthData.put("descTrabMembro", descTrabMemb);

                //Pessoa recebe ajuda de terceiros
                String strIndAjudaFamiliaMemb = jsonObjHealthData.getString("indAjudaFamiliaMemb");
                Integer intIndAjudaFamiliaMemb = 99;

                if (strIndAjudaFamiliaMemb != "null") {
                    intIndAjudaFamiliaMemb = Integer.parseInt(strIndAjudaFamiliaMemb);
                }

                String descAjudaFam;

                if (intIndAjudaFamiliaMemb == 0 || strIndAjudaFamiliaMemb == "null") {
                    descAjudaFam = "Opção não marcada no formulário";
                }
                else {
                    descAjudaFam = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indAjudaFamiliaMemb", descAjudaFam);

                // Calculando se o cidadão já frequentou a escola
                String strCodFrequentaEscolaMemb = jsonObjHealthData.getString("indFrequentaEscolaMemb");
                String descFrequentaEscolaMemb;
                if(strCodFrequentaEscolaMemb != null && strCodFrequentaEscolaMemb != "null") {
                    Integer codFrequentaEscolaMemb = Integer.parseInt(strCodFrequentaEscolaMemb);

                    switch(codFrequentaEscolaMemb) {
                        case 1:
                            descFrequentaEscolaMemb = "Sim, rede pública";
                            break;

                        case 2:
                            descFrequentaEscolaMemb = "Não, rede particular";
                            break;

                        case 3:
                            descFrequentaEscolaMemb = "Não, já frequentou";
                            break;

                        case 4:
                            descFrequentaEscolaMemb = "Nunca frequentou";
                            break;

                        default:
                            descFrequentaEscolaMemb = "Não Informado";
                    }
                }
                else{
                    descFrequentaEscolaMemb = "Não informado";
                }

                jsonObjHealthData.put("descFrequentaEscolaMemb", descFrequentaEscolaMemb);

                // Calculando o curso que frequenta o membro
                String strCodCursoFrequentaMemb = jsonObjHealthData.getString("codCursoFrequentaMemb");
                String descCursoFrequentaMemb;
                if(strCodCursoFrequentaMemb != null && strCodCursoFrequentaMemb != "null") {
                    Integer codCursoFrequentaMemb = Integer.parseInt(strCodCursoFrequentaMemb);

                    switch(codCursoFrequentaMemb) {
                        case 1:
                            descCursoFrequentaMemb = "Creche";
                            break;

                        case 2:
                            descCursoFrequentaMemb = "Pré-escola (exceto CA)";
                            break;

                        case 3:
                            descCursoFrequentaMemb = "Classe de Alfabetização - CA";
                            break;

                        case 4:
                            descCursoFrequentaMemb = "Ensino Fundamental regular (duração 8 anos)";
                            break;

                        case 5:
                            descCursoFrequentaMemb = "Ensino Fundamental regular (duração 9 anos)";
                            break;

                        case 6:
                            descCursoFrequentaMemb = "Ensino Fundamental especial";
                            break;

                        case 7:
                            descCursoFrequentaMemb = "Ensino Médio regular";
                            break;

                        case 8:
                            descCursoFrequentaMemb = "Ensino Médio especial";
                            break;

                        case 9:
                            descCursoFrequentaMemb = "Ensino Fundamental EJA - séries iniciais (Supletivo - 1ª a 4ª)";
                            break;

                        case 10:
                            descCursoFrequentaMemb = "Ensino Fundamental EJA - séries finais (Supletivo - 5ª a 8ª)";
                            break;

                        case 11:
                            descCursoFrequentaMemb = "Ensino Médio EJA (Supletivo)";
                            break;

                        case 12:
                            descCursoFrequentaMemb = "Alfabetização para adultos";
                            break;

                        case 13:
                            descCursoFrequentaMemb = "Superior, Aperfeiçoamento, Especialização, Mestrado, Doutorado";
                            break;

                        case 14:
                            descCursoFrequentaMemb = "Pré-vestibular";
                            break;

                        default:
                            descCursoFrequentaMemb = "Não frequenta / Não informado";
                    }
                }
                else{
                    descCursoFrequentaMemb = "Não frequenta / Não informado";
                }

                jsonObjHealthData.put("descCursoFrequentaMemb", descCursoFrequentaMemb);

                // Calculando o ano/série que frequenta
                String strCodAnoSerieFrequenta = jsonObjHealthData.getString("codAnoSerieFrequentaMemb");
                String descAnoSerieFrequenta;
                if(strCodAnoSerieFrequenta != null && strCodAnoSerieFrequenta != "null") {
                    Integer codAnoSerieFrequenta = Integer.parseInt(strCodAnoSerieFrequenta);

                    switch(codAnoSerieFrequenta) {
                        case 1:
                            descAnoSerieFrequenta = "Primeiro(a)";
                            break;

                        case 2:
                            descAnoSerieFrequenta = "Segundo(a)";
                            break;

                        case 3:
                            descAnoSerieFrequenta = "Terceiro(a)";
                            break;

                        case 4:
                            descAnoSerieFrequenta = "Quarto(a)";
                            break;

                        case 5:
                            descAnoSerieFrequenta = "Quinto(a)";
                            break;

                        case 6:
                            descAnoSerieFrequenta = "Sexto(a)";
                            break;

                        case 7:
                            descAnoSerieFrequenta = "Sétimo(a)";
                            break;

                        case 8:
                            descAnoSerieFrequenta = "Oitavo(a)";
                            break;

                        case 9:
                            descAnoSerieFrequenta = "Nono(a)";
                            break;

                        case 10:
                            descAnoSerieFrequenta = "Curso não-seriado";
                            break;

                        default:
                            descAnoSerieFrequenta = "Não frequenta / Não informado";
                    }
                }
                else{
                    descAnoSerieFrequenta = "Não frequenta / Não informado";
                }

                jsonObjHealthData.put("descAnoSerieFrequentaMemb", descAnoSerieFrequenta);

                // Calculando o curso que o cidadão já frequentou
                String strCodCursoFrequentou = jsonObjHealthData.getString("codCursoFrequentouPessoaMemb");
                String descCursoFrequentou;
                if(strCodCursoFrequentou != null && strCodCursoFrequentou != "null") {
                    Integer codCursoFrequentou = Integer.parseInt(strCodCursoFrequentou);

                    switch(codCursoFrequentou) {
                        case 1:
                            descCursoFrequentou = "Creche";
                            break;

                        case 2:
                            descCursoFrequentou = "Pré-escola (exceto CA)";
                            break;

                        case 3:
                            descCursoFrequentou = "Classe de Alfabetização - CA";
                            break;

                        case 4:
                            descCursoFrequentou = "Ensino Fundamental 1ª a 4ª séries, Elementar (Primário), Primeira fase do 1º grau";
                            break;

                        case 5:
                            descCursoFrequentou = "Ensino Fundamental 5ª a 8ª séries, Médio 1º ciclo (Ginasial), Segunda fase do 1º grau";
                            break;

                        case 6:
                            descCursoFrequentou = "Ensino Fundamental regular (duração 9 anos)";
                            break;

                        case 7:
                            descCursoFrequentou = "Ensino Fundamental especial";
                            break;

                        case 8:
                            descCursoFrequentou = "Ensino Médio, 2ºgrau, Médio 2º ciclo (Científico, Clássico, Técnico, Normal)";
                            break;

                        case 9:
                            descCursoFrequentou = "Ensino Médio especial";
                            break;

                        case 10:
                            descCursoFrequentou = "Ensino Fundamental EJA - séries iniciais (Supletivo - 1ª a 4ª)";
                            break;

                        case 11:
                            descCursoFrequentou = "Ensino Fundamental EJA - séries finais (Supletivo - 5ª a 8ª)";
                            break;

                        case 12:
                            descCursoFrequentou = "Ensino Médio EJA (Supletivo)";
                            break;

                        case 13:
                            descCursoFrequentou = "Superior, Aperfeiçoamento, Especialização, Mestrado, Doutorado";
                            break;

                        case 14:
                            descCursoFrequentou = "Alfabetização para Adultos (Mobral, etc.)";
                            break;

                        case 15:
                            descCursoFrequentou = "Nenhum";
                            break;

                        default:
                            descCursoFrequentou = "Não informado";
                    }
                }
                else{
                    descCursoFrequentou = "Não informado";
                }

                jsonObjHealthData.put("descCursoFrequentouPessoaMemb", descCursoFrequentou);

                // Calculando o ano/série que frequenta
                String strCodAnoSerieFrequentou = jsonObjHealthData.getString("codAnoSerieFrequentouMemb");
                String descAnoSerieFrequentou;
                if(strCodAnoSerieFrequentou != null && strCodAnoSerieFrequentou != "null") {
                    Integer codAnoSerieFrequentou = Integer.parseInt(strCodAnoSerieFrequentou);

                    switch(codAnoSerieFrequentou) {
                        case 1:
                            descAnoSerieFrequentou = "Primeiro(a)";
                            break;

                        case 2:
                            descAnoSerieFrequentou = "Segundo(a)";
                            break;

                        case 3:
                            descAnoSerieFrequentou = "Terceiro(a)";
                            break;

                        case 4:
                            descAnoSerieFrequentou = "Quarto(a)";
                            break;

                        case 5:
                            descAnoSerieFrequentou = "Quinto(a)";
                            break;

                        case 6:
                            descAnoSerieFrequentou = "Sexto(a)";
                            break;

                        case 7:
                            descAnoSerieFrequentou = "Sétimo(a)";
                            break;

                        case 8:
                            descAnoSerieFrequentou = "Oitavo(a)";
                            break;

                        case 9:
                            descAnoSerieFrequentou = "Nono(a)";
                            break;

                        case 10:
                            descAnoSerieFrequentou = "Curso não-seriado";
                            break;

                        default:
                            descAnoSerieFrequentou = "Não frequentou / Não informado";
                    }
                }
                else{
                    descAnoSerieFrequentou = "Não frequentou / Não informado";
                }

                jsonObjHealthData.put("descAnoSerieFrequentouMemb", descAnoSerieFrequentou);

                //Pessoa sabe ler e escrever
                Integer intCodSabeLerEscreverMemb = null;
                String strCodSabeLerEscreverMemb = jsonObjHealthData.getString("codSabeLerEscreverMemb");
                if(strCodSabeLerEscreverMemb != "null"){
                  intCodSabeLerEscreverMemb = Integer.parseInt(strCodSabeLerEscreverMemb);
                }

                String descSabeLerEscrever;
                if (intCodSabeLerEscreverMemb == 1) {
                    descSabeLerEscrever = "Sim";
                }
                else {
                    descSabeLerEscrever = "Não";
                }

                jsonObjHealthData.put("codSabeLerEscreverMemb", descSabeLerEscrever);

                //Pessoa concluiu o curso frequentado
                Integer intCodConcluiuFrequentouMemb = null;
                String strCodConcluiuFrequentouMemb = jsonObjHealthData.getString("codConcluiuFrequentouMemb");
                if(strCodConcluiuFrequentouMemb != "null"){
                  intCodConcluiuFrequentouMemb = Integer.parseInt(strCodConcluiuFrequentouMemb);
                }
                String descConcluiu;
                if (intCodConcluiuFrequentouMemb == 1) {
                    descConcluiu = "Sim";
                }
                else {
                    descConcluiu = "Não";
                }

                jsonObjHealthData.put("codConcluiuFrequentouMemb", descConcluiu);

                // Responsável Familiar
                Integer intResponsavel = null;
                String strResponsavel = jsonObjHealthData.getString("codParentescoRfPessoa");
                if(strResponsavel != "null"){
                  intResponsavel = Integer.parseInt(strResponsavel);
                }

                String descResponsavel;
                if(intResponsavel == 1){
                    descResponsavel = "Sim";
                }
                else{
                    descResponsavel = "Não";
                }

                jsonObjHealthData.put("descResponsavel", descResponsavel);

                // Pessoa tem deficiência
                Integer intCodDefMemb = null;
                String strCodDefMemb = jsonObjHealthData.getString("codDeficienciaMemb");
                if(strCodDefMemb != "null"){
                  intCodDefMemb =  Integer.parseInt(strCodDefMemb);
                }
                String descCodDefMemb;
                if (intCodDefMemb == 1) {
                    descCodDefMemb = "Sim";
                }
                else {
                    descCodDefMemb = "Não";
                }

                jsonObjHealthData.put("codDeficienciaMemb", descCodDefMemb);

                // Pessoa tem deficiência de baixa visão
                String strIndDefBaixaVisaoMemb = jsonObjHealthData.getString("indDefBaixaVisaoMemb");
                Integer intIndDefBaixaVisaoMemb = 99;
                if (strIndDefBaixaVisaoMemb != "null") {
                    intIndDefBaixaVisaoMemb = Integer.parseInt(strIndDefBaixaVisaoMemb);
                }
                String descIndDefBaixaVisao;

                if (intIndDefBaixaVisaoMemb == 0 || strIndDefBaixaVisaoMemb == "null"){
                    descIndDefBaixaVisao = "Opção não marcada no formulário";
                }
                else {
                    descIndDefBaixaVisao = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefBaixaVisaoMemb", descIndDefBaixaVisao);

                // Pessoa tem Síndrome de Down
                String strIndDefSrindromeDownMemb = jsonObjHealthData.getString("indDefSindromeDownMemb");
                Integer intIndDefSindromeDownMemb = 99;
                if (strIndDefSrindromeDownMemb != "null") {
                    intIndDefSindromeDownMemb = Integer.parseInt(strIndDefSrindromeDownMemb);
                }
                String descIndDefSindromeDown;

                if (intIndDefSindromeDownMemb == 0 || strIndDefSrindromeDownMemb == "null") {
                    descIndDefSindromeDown = "Opção não marcada no formulário";
                }
                else {
                    descIndDefSindromeDown = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefSindromeDownMemb", descIndDefSindromeDown);

                // Pessoa possuí deficiência mental
                String strIndDefMentalMemb = jsonObjHealthData.getString("indDefMentalMemb");
                Integer intIndDefMentalMemb = 99;
                if (strIndDefMentalMemb != "null") {
                    intIndDefMentalMemb = Integer.parseInt(strIndDefMentalMemb);
                }

                String descIndDefMental;

                if (intIndDefMentalMemb == 0 || strIndDefMentalMemb == "null") {
                    descIndDefMental = "Opção não marcada no formulário";
                }
                else {
                    descIndDefMental = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefMentalMemb", descIndDefMental);

                //Pessoa possuí deficiência cegueira
                String strIndDefCegueiraMemb = jsonObjHealthData.getString("indDefCegueiraMemb");
                Integer intIndDefCegueiraMemb = 99;

                if (strIndDefCegueiraMemb != "null") {
                    intIndDefCegueiraMemb = Integer.parseInt(strIndDefCegueiraMemb);
                }

                String descIndDefCegueira;

                if (intIndDefCegueiraMemb == 0 || strIndDefCegueiraMemb == "null") {
                    descIndDefCegueira = "Opção não marcada no formulário";
                }
                else {
                    descIndDefCegueira = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefCegueiraMemb", descIndDefCegueira);

                //Pessoa tem surdez leve
                String strIndDefSurdezLeveMemb = jsonObjHealthData.getString("indDefSurdezLeveMemb");
                Integer intIndDefSurdezLeveMemb = 99;

                if (strIndDefSurdezLeveMemb != "null") {
                    intIndDefSurdezLeveMemb = Integer.parseInt(strIndDefSurdezLeveMemb);
                }

                String descIndDefSurdez;

                if (intIndDefSurdezLeveMemb == 0 || strIndDefSurdezLeveMemb == "null") {
                    descIndDefSurdez = "Opção não marcada no formulário";
                }
                else {
                    descIndDefSurdez = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefSurdezLeveMemb", descIndDefSurdez);

                //Pessoa tem surdez profunda
                String strIndDefSurdezProfundaMemb = jsonObjHealthData.getString("indDefSurdezProfundaMemb");
                Integer intIndDefSurdezProfundaMemb = 99;

                if (strIndDefSurdezProfundaMemb != "null") {
                    intIndDefSurdezProfundaMemb = Integer.parseInt(strIndDefSurdezProfundaMemb);
                }

                String descIndDefSurdezProfunda;

                if (intIndDefSurdezProfundaMemb == 0 || strIndDefSurdezProfundaMemb == "null") {
                    descIndDefSurdezProfunda = "Opção não marcada no formulário";
                }
                else {
                    descIndDefSurdezProfunda = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefSurdezProfundaMemb", descIndDefSurdezProfunda);

                //Pessoa tem transtorno mental
                String strIndDefTranstornoMentalMemb = jsonObjHealthData.getString("indDefTranstornoMentalMemb");
                Integer intIndDefTranstornoMentalMemb = 99;

                if (strIndDefTranstornoMentalMemb != "null") {
                    intIndDefTranstornoMentalMemb = Integer.parseInt(strIndDefTranstornoMentalMemb);
                }

                String descTranstornoMental;

                if (intIndDefTranstornoMentalMemb == 0 || strIndDefTranstornoMentalMemb == "null") {
                    descTranstornoMental = "Opção não marcada no formulário";
                }
                else {
                    descTranstornoMental = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefTranstornoMentalMemb", descTranstornoMental);

                //Pessoa tem deficiência física
                String strIndDefFisicaMemb = jsonObjHealthData.getString("indDefFisicaMemb");
                Integer intIndDefFisicaMemb = 99;

                if (strIndDefFisicaMemb != "null") {
                    intIndDefFisicaMemb = Integer.parseInt(strIndDefFisicaMemb);
                }

                String descDefFisica;

                if (intIndDefFisicaMemb == 0 || strIndDefFisicaMemb == "null") {
                    descDefFisica = "Opção não marcada no formulário";
                }
                else {
                    descDefFisica = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indDefFisicaMemb", descDefFisica);

                //Pessoa recebe ajuda de terceiros
                String strIndAjudaNaoMemb = jsonObjHealthData.getString("indAjudaNaoMemb");
                Integer intIndAjudaNaoMemb = 99;

                if (strIndAjudaNaoMemb != "null") {
                    intIndAjudaNaoMemb = Integer.parseInt(strIndAjudaNaoMemb);
                }

                String descAjudaDeTerceiro;

                if (intIndAjudaNaoMemb == 0 || strIndAjudaNaoMemb.equals("null")) {
                    descAjudaDeTerceiro = "Opção não marcada no formulário";
                }
                else {
                    descAjudaDeTerceiro = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indAjudaNaoMemb", descAjudaDeTerceiro);

                //Pessoa recebe ajuda especializada
                String strIndAjudaEspecializadoMemb = jsonObjHealthData.getString("indAjudaEspecializadoMemb");
                String descIndAjudaEspecializadoMemb;

                if(strIndAjudaEspecializadoMemb == null || strIndAjudaEspecializadoMemb.equals("null") || strIndAjudaEspecializadoMemb == "0"){
                  descIndAjudaEspecializadoMemb = "Opção não marcada no formulário";
                }
                else{
                  descIndAjudaEspecializadoMemb = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indAjudaEspecializadoMemb",descIndAjudaEspecializadoMemb);

                //Pessoa recebe ajuda de vizinhos
                String strIndAjudaVizinhoMemb = jsonObjHealthData.getString("indAjudaVizinhoMemb");
                String descAjudaVizinhoMemb;

                if(strIndAjudaVizinhoMemb == null || strIndAjudaVizinhoMemb.equals("null") || strIndAjudaVizinhoMemb == "0"){
                  descAjudaVizinhoMemb = "Opção não marcada no formulário";
                }
                else{
                  descAjudaVizinhoMemb = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indAjudaVizinhoMemb", descAjudaVizinhoMemb);

                //Pessoa recebe ajuda de instituição
                String strIndAjudaInstituicaoMemb = jsonObjHealthData.getString("indAjudaInstituicaoMemb");
                String descIndAjudaInstituicaoMemb;

                if(strIndAjudaInstituicaoMemb == null || strIndAjudaInstituicaoMemb == "null" || strIndAjudaInstituicaoMemb.equals("0")){
                  descIndAjudaInstituicaoMemb = "Opção não marcada no formulário";
                }
                else{
                  descIndAjudaInstituicaoMemb = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indAjudaInstituicaoMemb", descIndAjudaInstituicaoMemb);

                //Pessoa recebe algum outro tipo de ajuda
                String strIndAjudaOutraMemb = jsonObjHealthData.getString("indAjudaOutraMemb");
                String descIndAjudaOutraMemb;

                if(strIndAjudaOutraMemb == null || strIndAjudaOutraMemb == "null" || strIndAjudaOutraMemb.equals("0")){
                  descIndAjudaOutraMemb = "Opção não marcada no formulário";
                }
                else{
                  descIndAjudaOutraMemb = "Opção marcada no formulário";
                }

                jsonObjHealthData.put("indAjudaOutraMemb",descIndAjudaOutraMemb);

                //Pessoa trabalhou nos ultimos 12 meses
                String strCodTrabalhoDozeMesesMemb = jsonObjHealthData.getString("codTrabalhoDozeMesesMemb");
                Integer intCodTrabalhoDozeMesesMemb = 99;

                if(strCodTrabalhoDozeMesesMemb != "null" && strCodTrabalhoDozeMesesMemb != null){
                  intCodTrabalhoDozeMesesMemb = Integer.parseInt(strCodTrabalhoDozeMesesMemb);
                }

                String descCodTrabalhoDozeMesesMemb;

                if(intCodTrabalhoDozeMesesMemb == 2 || intCodTrabalhoDozeMesesMemb == null){
                  descCodTrabalhoDozeMesesMemb = "Não";
                }
                else{
                  descCodTrabalhoDozeMesesMemb = "Sim";
                }

                jsonObjHealthData.put("codTrabalhoDozeMesesMemb", descCodTrabalhoDozeMesesMemb);

                //Pessoa Realiza Trabalho Infantil
                String strIndTrabalhoInfantilPessoa = jsonObjHealthData.getString("indTrabalhoInfantilPessoa");
                String descIndTrabalhoInfantilPessoa;

                if(strIndTrabalhoInfantilPessoa == null || strIndTrabalhoInfantilPessoa == "null" || strIndTrabalhoInfantilPessoa == "0"){
                  descIndTrabalhoInfantilPessoa = null;
                }
                else{
                  descIndTrabalhoInfantilPessoa = "Sim";
                }

                strHealthData = mapper.readValue(jsonObjHealthData.toString(), HashMap.class);
            }
        }

        return strHealthData;
    }

    public Map<String, Object> FamiliaCadunico(Map<String, Object> strHealthData)
            throws JSONException, JsonProcessingException {

        Map<String, Object> strFinantialData = new HashMap<String, Object>();

        JSONObject jsonFinantialData = new JSONObject(strHealthData);
        String codFamiliarFam = jsonFinantialData.getString("codFamiliarFam");
        if(codFamiliarFam != null){
            TabFamiliaCadunico tabFamiliaCadunico = findFamiliaCadunico(codFamiliarFam);
            if(tabFamiliaCadunico != null){
                String strTabFamiliaCadunico = objectWriter.writeValueAsString(tabFamiliaCadunico);
                Map<String, Object> mapStrTabFamiliaCadunico = mapper.readValue(strTabFamiliaCadunico, Map.class);
                strFinantialData.putAll(mapStrTabFamiliaCadunico);

                jsonFinantialData = new JSONObject(strFinantialData);
                // Concatenando o endereço do cidadão
                Integer numEndereco = null;
                if(jsonFinantialData.getString("numLogradouroFam").trim() != null &&
                  jsonFinantialData.getString("numLogradouroFam").trim() != "null"){
                  numEndereco = Integer.parseInt(jsonFinantialData.getString("numLogradouroFam").trim());
                }
                else{
                  numEndereco = 0;
                }

                String endTipLog;
                if(jsonFinantialData.getString("nomTipLogradouroFam").trim() != null){
                  endTipLog = jsonFinantialData.getString("nomTipLogradouroFam").trim();
                }
                else{
                  endTipLog = "";
                }

                String endNomLog;
                if(jsonFinantialData.getString("nomTituloLogradouroFam").trim() != null){
                  endNomLog = jsonFinantialData.getString("nomTituloLogradouroFam").trim();
                }
                else{
                  endNomLog = "";
                }

                String endLog;
                if(jsonFinantialData.getString("nomLogradouroFam").trim() != null){
                  endLog = jsonFinantialData.getString("nomLogradouroFam").trim();
                }
                else{
                  endLog = "";
                }

                String endNomLoc;
                if(jsonFinantialData.getString("nomLocalidadeFam").trim() != null){
                  endNomLoc = jsonFinantialData.getString("nomLocalidadeFam").trim();
                }
                else{
                  endNomLoc = "";
                }

                if(endTipLog == "null"){
                    endTipLog = "";
                }
                if(endNomLog == "null"){
                    endNomLog = "";
                }
                if(endLog == "null"){
                    endLog = "";
                }
                if(endNomLoc == "null"){
                    endNomLoc = "";
                }

                if(endLog != ""){
                  String strFullAddress = endTipLog + " "
                    + endNomLog + " "
                    + endLog  + ", nº"
                    + numEndereco + ", "
                    + endNomLoc + ", "
                    + "SÃO PAULO - SP - BRASIL";
                  jsonFinantialData.put("descEndereco", strFullAddress);
                }
                else{
                  jsonFinantialData.put("descEndereco", "");
                }

                // Mudando o formato da renda familiar para float
                String strVlrRendaFamiliar = jsonFinantialData.getString("vlrRendaMediaFam");
                StringBuilder stringBuilder = new StringBuilder(strVlrRendaFamiliar);
                stringBuilder.insert(strVlrRendaFamiliar.length()-2,".");
                String dotStrVlrRendaFamiliar = stringBuilder.toString();
                Float vlrRendaFamiliar = Float.parseFloat(dotStrVlrRendaFamiliar);
                String strRendaMedia = "R$"+ vlrRendaFamiliar +"0";
                jsonFinantialData.put("vlrRendaMediaFam", strRendaMedia);

                strFinantialData = mapper.readValue(jsonFinantialData.toString(), HashMap.class);
            }
        }

        return strFinantialData;
    }

    // Utilização do método findRacaCidadao
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // Método que chama: PersonalData
    private DimRaca findRacaCidadao(Integer racaCidadao){
        DimRaca Response;

        Response = dimRacaService.findByCiRaca(racaCidadao);
        return Response;
    }

    // Utilização do método findTipoSexo
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // Método que chama: PersonalData
    private DimTipoSexo findTipoSexo(Integer tipoSexoCidadao){
        DimTipoSexo Response;

        Response = dimTipoSexoService.findByCiTipoSexo(tipoSexoCidadao);
        return Response;
    }

    // Utilização do método findPaisOrigem
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // Método que chama: PersonalData
    private DimPaisOrigem findPaisOrigem(Integer paisOrigemCidadao){
        DimPaisOrigem Response;

        Response = dimPaisOrigemService.findCiPaisOrigem(paisOrigemCidadao);
        return Response;
    }

    // Utilização do método findSituacaoCidadao
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // Método que chama: HealthData
    private DimSituacaoCidadao findSituacaoCidadao(Integer ciSitCidadao) {
        DimSituacaoCidadao Response;

        Response = dimSituacaoCidadaoService.findByCiSitCidadao(ciSitCidadao);
        return Response;
    }

    // Utilização do método findSituacaoCidadao
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // Método que utilizam dele: HealthData, FinantialData, EducationData
    private TabPessoaCadunico findPessoaCadunico(String cdNis){
        TabPessoaCadunico Response;

        Response = tabPessoaCadunicoService.findByNumNisPessoaAtual(cdNis);
        return Response;
    }

    // Utilização do método findFamiliaCadunico
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // Método que utilizam dele: FinantialData, PersonalData
    private TabFamiliaCadunico findFamiliaCadunico(String cdFamiliarFam){
        TabFamiliaCadunico Response;

        Response = tabFamiliaCadunicoService.findByTabFamiliaCadunico(cdFamiliarFam);
        return Response;
    }
}
