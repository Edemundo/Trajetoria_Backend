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

        // Remover o que n??o ser?? utilizado no frontend
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
        // Endere??o
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

        // Modificando a situa????o do cidad??o
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

                // Calculando trabalho do cidad??o
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
                            descTrabMemb = "DESEMPREGADO / N??O INFORMADO";
                            break;

                        default:
                            descTrabMemb = "DESEMPREGADO / N??O INFORMADO";
                    }
                } else {
                    descTrabMemb = "DESEMPREGADO / N??O INFORMADO";
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
                    descAjudaFam = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descAjudaFam = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indAjudaFamiliaMemb", descAjudaFam);

                // Calculando se o cidad??o j?? frequentou a escola
                String strCodFrequentaEscolaMemb = jsonObjHealthData.getString("indFrequentaEscolaMemb");
                String descFrequentaEscolaMemb;
                if(strCodFrequentaEscolaMemb != null && strCodFrequentaEscolaMemb != "null") {
                    Integer codFrequentaEscolaMemb = Integer.parseInt(strCodFrequentaEscolaMemb);

                    switch(codFrequentaEscolaMemb) {
                        case 1:
                            descFrequentaEscolaMemb = "Sim, rede p??blica";
                            break;

                        case 2:
                            descFrequentaEscolaMemb = "N??o, rede particular";
                            break;

                        case 3:
                            descFrequentaEscolaMemb = "N??o, j?? frequentou";
                            break;

                        case 4:
                            descFrequentaEscolaMemb = "Nunca frequentou";
                            break;

                        default:
                            descFrequentaEscolaMemb = "N??o Informado";
                    }
                }
                else{
                    descFrequentaEscolaMemb = "N??o informado";
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
                            descCursoFrequentaMemb = "Pr??-escola (exceto CA)";
                            break;

                        case 3:
                            descCursoFrequentaMemb = "Classe de Alfabetiza????o - CA";
                            break;

                        case 4:
                            descCursoFrequentaMemb = "Ensino Fundamental regular (dura????o 8 anos)";
                            break;

                        case 5:
                            descCursoFrequentaMemb = "Ensino Fundamental regular (dura????o 9 anos)";
                            break;

                        case 6:
                            descCursoFrequentaMemb = "Ensino Fundamental especial";
                            break;

                        case 7:
                            descCursoFrequentaMemb = "Ensino M??dio regular";
                            break;

                        case 8:
                            descCursoFrequentaMemb = "Ensino M??dio especial";
                            break;

                        case 9:
                            descCursoFrequentaMemb = "Ensino Fundamental EJA - s??ries iniciais (Supletivo - 1?? a 4??)";
                            break;

                        case 10:
                            descCursoFrequentaMemb = "Ensino Fundamental EJA - s??ries finais (Supletivo - 5?? a 8??)";
                            break;

                        case 11:
                            descCursoFrequentaMemb = "Ensino M??dio EJA (Supletivo)";
                            break;

                        case 12:
                            descCursoFrequentaMemb = "Alfabetiza????o para adultos";
                            break;

                        case 13:
                            descCursoFrequentaMemb = "Superior, Aperfei??oamento, Especializa????o, Mestrado, Doutorado";
                            break;

                        case 14:
                            descCursoFrequentaMemb = "Pr??-vestibular";
                            break;

                        default:
                            descCursoFrequentaMemb = "N??o frequenta / N??o informado";
                    }
                }
                else{
                    descCursoFrequentaMemb = "N??o frequenta / N??o informado";
                }

                jsonObjHealthData.put("descCursoFrequentaMemb", descCursoFrequentaMemb);

                // Calculando o ano/s??rie que frequenta
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
                            descAnoSerieFrequenta = "S??timo(a)";
                            break;

                        case 8:
                            descAnoSerieFrequenta = "Oitavo(a)";
                            break;

                        case 9:
                            descAnoSerieFrequenta = "Nono(a)";
                            break;

                        case 10:
                            descAnoSerieFrequenta = "Curso n??o-seriado";
                            break;

                        default:
                            descAnoSerieFrequenta = "N??o frequenta / N??o informado";
                    }
                }
                else{
                    descAnoSerieFrequenta = "N??o frequenta / N??o informado";
                }

                jsonObjHealthData.put("descAnoSerieFrequentaMemb", descAnoSerieFrequenta);

                // Calculando o curso que o cidad??o j?? frequentou
                String strCodCursoFrequentou = jsonObjHealthData.getString("codCursoFrequentouPessoaMemb");
                String descCursoFrequentou;
                if(strCodCursoFrequentou != null && strCodCursoFrequentou != "null") {
                    Integer codCursoFrequentou = Integer.parseInt(strCodCursoFrequentou);

                    switch(codCursoFrequentou) {
                        case 1:
                            descCursoFrequentou = "Creche";
                            break;

                        case 2:
                            descCursoFrequentou = "Pr??-escola (exceto CA)";
                            break;

                        case 3:
                            descCursoFrequentou = "Classe de Alfabetiza????o - CA";
                            break;

                        case 4:
                            descCursoFrequentou = "Ensino Fundamental 1?? a 4?? s??ries, Elementar (Prim??rio), Primeira fase do 1?? grau";
                            break;

                        case 5:
                            descCursoFrequentou = "Ensino Fundamental 5?? a 8?? s??ries, M??dio 1?? ciclo (Ginasial), Segunda fase do 1?? grau";
                            break;

                        case 6:
                            descCursoFrequentou = "Ensino Fundamental regular (dura????o 9 anos)";
                            break;

                        case 7:
                            descCursoFrequentou = "Ensino Fundamental especial";
                            break;

                        case 8:
                            descCursoFrequentou = "Ensino M??dio, 2??grau, M??dio 2?? ciclo (Cient??fico, Cl??ssico, T??cnico, Normal)";
                            break;

                        case 9:
                            descCursoFrequentou = "Ensino M??dio especial";
                            break;

                        case 10:
                            descCursoFrequentou = "Ensino Fundamental EJA - s??ries iniciais (Supletivo - 1?? a 4??)";
                            break;

                        case 11:
                            descCursoFrequentou = "Ensino Fundamental EJA - s??ries finais (Supletivo - 5?? a 8??)";
                            break;

                        case 12:
                            descCursoFrequentou = "Ensino M??dio EJA (Supletivo)";
                            break;

                        case 13:
                            descCursoFrequentou = "Superior, Aperfei??oamento, Especializa????o, Mestrado, Doutorado";
                            break;

                        case 14:
                            descCursoFrequentou = "Alfabetiza????o para Adultos (Mobral, etc.)";
                            break;

                        case 15:
                            descCursoFrequentou = "Nenhum";
                            break;

                        default:
                            descCursoFrequentou = "N??o informado";
                    }
                }
                else{
                    descCursoFrequentou = "N??o informado";
                }

                jsonObjHealthData.put("descCursoFrequentouPessoaMemb", descCursoFrequentou);

                // Calculando o ano/s??rie que frequenta
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
                            descAnoSerieFrequentou = "S??timo(a)";
                            break;

                        case 8:
                            descAnoSerieFrequentou = "Oitavo(a)";
                            break;

                        case 9:
                            descAnoSerieFrequentou = "Nono(a)";
                            break;

                        case 10:
                            descAnoSerieFrequentou = "Curso n??o-seriado";
                            break;

                        default:
                            descAnoSerieFrequentou = "N??o frequentou / N??o informado";
                    }
                }
                else{
                    descAnoSerieFrequentou = "N??o frequentou / N??o informado";
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
                    descSabeLerEscrever = "N??o";
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
                    descConcluiu = "N??o";
                }

                jsonObjHealthData.put("codConcluiuFrequentouMemb", descConcluiu);

                // Respons??vel Familiar
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
                    descResponsavel = "N??o";
                }

                jsonObjHealthData.put("descResponsavel", descResponsavel);

                // Pessoa tem defici??ncia
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
                    descCodDefMemb = "N??o";
                }

                jsonObjHealthData.put("codDeficienciaMemb", descCodDefMemb);

                // Pessoa tem defici??ncia de baixa vis??o
                String strIndDefBaixaVisaoMemb = jsonObjHealthData.getString("indDefBaixaVisaoMemb");
                Integer intIndDefBaixaVisaoMemb = 99;
                if (strIndDefBaixaVisaoMemb != "null") {
                    intIndDefBaixaVisaoMemb = Integer.parseInt(strIndDefBaixaVisaoMemb);
                }
                String descIndDefBaixaVisao;

                if (intIndDefBaixaVisaoMemb == 0 || strIndDefBaixaVisaoMemb == "null"){
                    descIndDefBaixaVisao = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descIndDefBaixaVisao = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indDefBaixaVisaoMemb", descIndDefBaixaVisao);

                // Pessoa tem S??ndrome de Down
                String strIndDefSrindromeDownMemb = jsonObjHealthData.getString("indDefSindromeDownMemb");
                Integer intIndDefSindromeDownMemb = 99;
                if (strIndDefSrindromeDownMemb != "null") {
                    intIndDefSindromeDownMemb = Integer.parseInt(strIndDefSrindromeDownMemb);
                }
                String descIndDefSindromeDown;

                if (intIndDefSindromeDownMemb == 0 || strIndDefSrindromeDownMemb == "null") {
                    descIndDefSindromeDown = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descIndDefSindromeDown = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indDefSindromeDownMemb", descIndDefSindromeDown);

                // Pessoa possu?? defici??ncia mental
                String strIndDefMentalMemb = jsonObjHealthData.getString("indDefMentalMemb");
                Integer intIndDefMentalMemb = 99;
                if (strIndDefMentalMemb != "null") {
                    intIndDefMentalMemb = Integer.parseInt(strIndDefMentalMemb);
                }

                String descIndDefMental;

                if (intIndDefMentalMemb == 0 || strIndDefMentalMemb == "null") {
                    descIndDefMental = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descIndDefMental = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indDefMentalMemb", descIndDefMental);

                //Pessoa possu?? defici??ncia cegueira
                String strIndDefCegueiraMemb = jsonObjHealthData.getString("indDefCegueiraMemb");
                Integer intIndDefCegueiraMemb = 99;

                if (strIndDefCegueiraMemb != "null") {
                    intIndDefCegueiraMemb = Integer.parseInt(strIndDefCegueiraMemb);
                }

                String descIndDefCegueira;

                if (intIndDefCegueiraMemb == 0 || strIndDefCegueiraMemb == "null") {
                    descIndDefCegueira = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descIndDefCegueira = "Op????o marcada no formul??rio";
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
                    descIndDefSurdez = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descIndDefSurdez = "Op????o marcada no formul??rio";
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
                    descIndDefSurdezProfunda = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descIndDefSurdezProfunda = "Op????o marcada no formul??rio";
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
                    descTranstornoMental = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descTranstornoMental = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indDefTranstornoMentalMemb", descTranstornoMental);

                //Pessoa tem defici??ncia f??sica
                String strIndDefFisicaMemb = jsonObjHealthData.getString("indDefFisicaMemb");
                Integer intIndDefFisicaMemb = 99;

                if (strIndDefFisicaMemb != "null") {
                    intIndDefFisicaMemb = Integer.parseInt(strIndDefFisicaMemb);
                }

                String descDefFisica;

                if (intIndDefFisicaMemb == 0 || strIndDefFisicaMemb == "null") {
                    descDefFisica = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descDefFisica = "Op????o marcada no formul??rio";
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
                    descAjudaDeTerceiro = "Op????o n??o marcada no formul??rio";
                }
                else {
                    descAjudaDeTerceiro = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indAjudaNaoMemb", descAjudaDeTerceiro);

                //Pessoa recebe ajuda especializada
                String strIndAjudaEspecializadoMemb = jsonObjHealthData.getString("indAjudaEspecializadoMemb");
                String descIndAjudaEspecializadoMemb;

                if(strIndAjudaEspecializadoMemb == null || strIndAjudaEspecializadoMemb.equals("null") || strIndAjudaEspecializadoMemb == "0"){
                  descIndAjudaEspecializadoMemb = "Op????o n??o marcada no formul??rio";
                }
                else{
                  descIndAjudaEspecializadoMemb = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indAjudaEspecializadoMemb",descIndAjudaEspecializadoMemb);

                //Pessoa recebe ajuda de vizinhos
                String strIndAjudaVizinhoMemb = jsonObjHealthData.getString("indAjudaVizinhoMemb");
                String descAjudaVizinhoMemb;

                if(strIndAjudaVizinhoMemb == null || strIndAjudaVizinhoMemb.equals("null") || strIndAjudaVizinhoMemb == "0"){
                  descAjudaVizinhoMemb = "Op????o n??o marcada no formul??rio";
                }
                else{
                  descAjudaVizinhoMemb = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indAjudaVizinhoMemb", descAjudaVizinhoMemb);

                //Pessoa recebe ajuda de institui????o
                String strIndAjudaInstituicaoMemb = jsonObjHealthData.getString("indAjudaInstituicaoMemb");
                String descIndAjudaInstituicaoMemb;

                if(strIndAjudaInstituicaoMemb == null || strIndAjudaInstituicaoMemb == "null" || strIndAjudaInstituicaoMemb.equals("0")){
                  descIndAjudaInstituicaoMemb = "Op????o n??o marcada no formul??rio";
                }
                else{
                  descIndAjudaInstituicaoMemb = "Op????o marcada no formul??rio";
                }

                jsonObjHealthData.put("indAjudaInstituicaoMemb", descIndAjudaInstituicaoMemb);

                //Pessoa recebe algum outro tipo de ajuda
                String strIndAjudaOutraMemb = jsonObjHealthData.getString("indAjudaOutraMemb");
                String descIndAjudaOutraMemb;

                if(strIndAjudaOutraMemb == null || strIndAjudaOutraMemb == "null" || strIndAjudaOutraMemb.equals("0")){
                  descIndAjudaOutraMemb = "Op????o n??o marcada no formul??rio";
                }
                else{
                  descIndAjudaOutraMemb = "Op????o marcada no formul??rio";
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
                  descCodTrabalhoDozeMesesMemb = "N??o";
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
                // Concatenando o endere??o do cidad??o
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
                    + endLog  + ", n??"
                    + numEndereco + ", "
                    + endNomLoc + ", "
                    + "S??O PAULO - SP - BRASIL";
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

    // Utiliza????o do m??todo findRacaCidadao
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // M??todo que chama: PersonalData
    private DimRaca findRacaCidadao(Integer racaCidadao){
        DimRaca Response;

        Response = dimRacaService.findByCiRaca(racaCidadao);
        return Response;
    }

    // Utiliza????o do m??todo findTipoSexo
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // M??todo que chama: PersonalData
    private DimTipoSexo findTipoSexo(Integer tipoSexoCidadao){
        DimTipoSexo Response;

        Response = dimTipoSexoService.findByCiTipoSexo(tipoSexoCidadao);
        return Response;
    }

    // Utiliza????o do m??todo findPaisOrigem
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // M??todo que chama: PersonalData
    private DimPaisOrigem findPaisOrigem(Integer paisOrigemCidadao){
        DimPaisOrigem Response;

        Response = dimPaisOrigemService.findCiPaisOrigem(paisOrigemCidadao);
        return Response;
    }

    // Utiliza????o do m??todo findSituacaoCidadao
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // M??todo que chama: HealthData
    private DimSituacaoCidadao findSituacaoCidadao(Integer ciSitCidadao) {
        DimSituacaoCidadao Response;

        Response = dimSituacaoCidadaoService.findByCiSitCidadao(ciSitCidadao);
        return Response;
    }

    // Utiliza????o do m??todo findSituacaoCidadao
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // M??todo que utilizam dele: HealthData, FinantialData, EducationData
    private TabPessoaCadunico findPessoaCadunico(String cdNis){
        TabPessoaCadunico Response;

        Response = tabPessoaCadunicoService.findByNumNisPessoaAtual(cdNis);
        return Response;
    }

    // Utiliza????o do m??todo findFamiliaCadunico
    // Controller(s) que utilizam: PessoalSaudeFinancasEducacaoDataController
    // M??todo que utilizam dele: FinantialData, PersonalData
    private TabFamiliaCadunico findFamiliaCadunico(String cdFamiliarFam){
        TabFamiliaCadunico Response;

        Response = tabFamiliaCadunicoService.findByTabFamiliaCadunico(cdFamiliarFam);
        return Response;
    }
}
