package com.smads.covs.trajetoria_cidadao.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.smads.covs.trajetoria_cidadao.models.cidadao_detalhado.CidadaoDetalhado;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.Map;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimCidadao;

@Controller
public class TelaDetalhadaMainController {

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private ObjectMapper mapper = new ObjectMapper();

    private final PessoalSaudeFinancasEducacaoDataController pessoalSaudeFinancasEducacaoDataController;
    private final SmitAPICallerController smitAPICallerController;

    public TelaDetalhadaMainController(PessoalSaudeFinancasEducacaoDataController pessoalSaudeFinancasEducacaoDataController,
                                       SmitAPICallerController smitAPICallerController) {
        this.pessoalSaudeFinancasEducacaoDataController = pessoalSaudeFinancasEducacaoDataController;
        this.smitAPICallerController = smitAPICallerController;
    }

    @CrossOrigin
    @PostMapping("/cidadao/detalhes")
    @ResponseBody
    public ResponseEntity<?> detalhesCidadao(@RequestBody DimCidadao dimCidadao) throws IOException,
            JSONException, URISyntaxException {
      Map<String, Object> strPSFEData = pessoalSaudeFinancasEducacaoDataController.PSFEDataController(dimCidadao);
      JSONObject jsonObjStrPSFEData = new JSONObject(strPSFEData);

      CidadaoDetalhado objServicosData = smitAPICallerController.getServicosData(dimCidadao.getCiCidadao());

      String strCiCidadao = jsonObjStrPSFEData.getString("ciCidadao");
      BigInteger biCiCidadao = new BigInteger(strCiCidadao);
      String strNrCpf = jsonObjStrPSFEData.getString("nrCpf");
      BigInteger biNrCpf = new BigInteger(strNrCpf);
      String strCdNis = jsonObjStrPSFEData.getString("cdNis");
      BigInteger biCdNis = new BigInteger(strCdNis);

      objServicosData.setCiCidadao(biCiCidadao);
      objServicosData.setNrCpf(biNrCpf);
      objServicosData.setCdNis(biCdNis);
      objServicosData.setAgeCidadao(jsonObjStrPSFEData.getInt("ageCidadao"));
      objServicosData.setNmCidadao(jsonObjStrPSFEData.getString("nmCidadao"));
      objServicosData.setNmMae(jsonObjStrPSFEData.getString("nmMae"));
      objServicosData.setDcRaca(jsonObjStrPSFEData.getString("dcRaca"));
      objServicosData.setDcTipoSexo(jsonObjStrPSFEData.getString("dcTipoSexo"));
      objServicosData.setDcSitCidadao(jsonObjStrPSFEData.getString("dcSitCidadao"));
      objServicosData.setDtNasc(jsonObjStrPSFEData.getString("dtNasc"));
      objServicosData.setNmPais(jsonObjStrPSFEData.getString("nmPais"));
      objServicosData.setDescResponsavel(jsonObjStrPSFEData.getString("descResponsavel"));
      objServicosData.setIndDefBaixaVisaoMemb(jsonObjStrPSFEData.getString("indDefBaixaVisaoMemb"));
      objServicosData.setIndDefSindromeDownMemb(jsonObjStrPSFEData.getString("indDefSindromeDownMemb"));
      objServicosData.setIndDefSurdezProfundaMemb(jsonObjStrPSFEData.getString("indDefSurdezProfundaMemb"));
      objServicosData.setCodDeficienciaMemb(jsonObjStrPSFEData.getString("codDeficienciaMemb"));
      objServicosData.setIndDefTranstornoMentalMemb(jsonObjStrPSFEData.getString("indDefTranstornoMentalMemb"));
      objServicosData.setIndDefMentalMemb(jsonObjStrPSFEData.getString("indDefMentalMemb"));
      objServicosData.setIndDefSurdezLeveMemb(jsonObjStrPSFEData.getString("indDefSurdezLeveMemb"));
      objServicosData.setIndDefFisicaMemb(jsonObjStrPSFEData.getString("indDefFisicaMemb"));
      objServicosData.setIndDefCegueiraMemb(jsonObjStrPSFEData.getString("indDefCegueiraMemb"));
      objServicosData.setDescAnoSerieFrequentaMemb(jsonObjStrPSFEData.getString("descAnoSerieFrequentaMemb"));
      objServicosData.setDescCursoFrequentaMemb(jsonObjStrPSFEData.getString("descCursoFrequentaMemb"));
      objServicosData.setDescCursoFrequentouPessoaMemb(jsonObjStrPSFEData.getString("descCursoFrequentouPessoaMemb"));
      objServicosData.setCodConcluiuFrequentouMemb(jsonObjStrPSFEData.getString("codConcluiuFrequentouMemb"));
      objServicosData.setDescFrequentaEscolaMemb(jsonObjStrPSFEData.getString("descFrequentaEscolaMemb"));
      objServicosData.setDescAnoSerieFrequentouMemb(jsonObjStrPSFEData.getString("descAnoSerieFrequentouMemb"));
      objServicosData.setCodSabeLerEscreverMemb(jsonObjStrPSFEData.getString("codSabeLerEscreverMemb"));
      objServicosData.setDescTrabMembro(jsonObjStrPSFEData.getString("descTrabMembro"));
      objServicosData.setIndAjudaNaoMemb(jsonObjStrPSFEData.getString("indAjudaNaoMemb"));
      objServicosData.setCodFamiliarFam(jsonObjStrPSFEData.getString("codFamiliarFam"));
      objServicosData.setVlrRendaMediaFam(jsonObjStrPSFEData.getString("vlrRendaMediaFam"));
      objServicosData.setDescEndereco(jsonObjStrPSFEData.getString("descEndereco"));
      objServicosData.setIndAjudaFamiliaMemb(jsonObjStrPSFEData.getString("indAjudaFamiliaMemb"));
      objServicosData.setCodTrabalhoDozeMesesMemb(jsonObjStrPSFEData.getString("codTrabalhoDozeMesesMemb"));
      objServicosData.setIndAjudaEspecializadoMemb(jsonObjStrPSFEData.getString("indAjudaEspecializadoMemb"));
      objServicosData.setIndAjudaVizinhoMemb(jsonObjStrPSFEData.getString("indAjudaVizinhoMemb"));
      objServicosData.setIndAjudaInstituicaoMemb(jsonObjStrPSFEData.getString("indAjudaInstituicaoMemb"));
      objServicosData.setIndAjudaOutraMemb(jsonObjStrPSFEData.getString("indAjudaOutraMemb"));

      return new ResponseEntity<>(objServicosData, HttpStatus.OK);
    }
}
