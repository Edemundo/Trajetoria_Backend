package com.smads.covs.trajetoria_cidadao.services.smit;

import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaVinculado;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaPernoite;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSiscr;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DimTipoServico;

import org.springframework.stereotype.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.IOException;
import java.math.BigInteger;

@Service
public class SmitService {

  private final CloseableHttpClient httpClient = HttpClients.createDefault();
  private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS+ss:ss");
  private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");

  private final DimTipoServicoService dimTipoServicoService;

  private final List<DadosSisaPernoite> lstDadosSisaPernoite;
  private final List<DadosSisaVinculado> lstDadosSisaVinculado;
  private final List<DadosSiscr> lstDadosSiscr;

  public SmitService(DimTipoServicoService dimTipoServicoService,
                     List<DadosSisaPernoite> lstDadosSisaPernoite,
                     List<DadosSisaVinculado> lstDadosSisaVinculado,
                     List<DadosSiscr> lstDadosSiscr) {
    this.dimTipoServicoService = dimTipoServicoService;
    this.lstDadosSisaPernoite = lstDadosSisaPernoite;
    this.lstDadosSisaVinculado = lstDadosSisaVinculado;
    this.lstDadosSiscr = lstDadosSiscr;
  }


  public List<DadosSisaVinculado> SmitAPISisaCaller(BigInteger ciCidadao)
      throws URISyntaxException, IOException {

      lstDadosSisaVinculado.clear();
      HttpGet httpGet = new HttpGet("http://localhost:9090/cidadao/smit/sisav");

      URI uri = new URIBuilder(httpGet.getURI())
              .addParameter("ciCidadao", String.valueOf(ciCidadao))
              .build();

      httpGet.setURI(uri);
      try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

          HttpEntity entity = response.getEntity();

          if (entity != null) {
            // return it as a String
            String strVincResultRequest = EntityUtils.toString(entity);

            JSONArray arrResultRequest =  new JSONArray(strVincResultRequest);
            int lenghtListVinc = arrResultRequest.length();

            for(int i = 0; i< lenghtListVinc; i++){

              DadosSisaVinculado dadosSisaVinculado = new DadosSisaVinculado();
              JSONObject service = arrResultRequest.getJSONObject(i);

              // Tratar tipos dos serviços que estão escritos por extenso
              DimTipoServico dimTipoServico = dimTipoServicoService.findDimTipoServico(service.getString("nmTipoServico"));
              String nmServicoResumido = dimTipoServico.getDcTipoSimplificado();

              dadosSisaVinculado.setNmDistrito(service.getString("nmDistrito"));
              dadosSisaVinculado.setNmSubprefeitura(service.getString("nmSubprefeitura"));
              dadosSisaVinculado.setNmServico(service.getString("nmServico"));
              dadosSisaVinculado.setDcTipoServico(nmServicoResumido);
              dadosSisaVinculado.setDcMotivoDesligamento(service.getString("nmMotivoDesligamento"));
              dadosSisaVinculado.setDcMotivoProcura(service.getString("nmMotivoProcura"));
              dadosSisaVinculado.setDcOrigemProcura(service.getString("nmOrigemProcura"));
              dadosSisaVinculado.setDtVinculacao(service.getString("dtVinculacao"));
              dadosSisaVinculado.setDtDesligamento(service.getString("dtDesligamento"));
              dadosSisaVinculado.setTempoPermanencia(service.getLong("tempoPermanencia"));

              lstDadosSisaVinculado.add(dadosSisaVinculado);
            }

          }
      } catch (ClientProtocolException | JSONException e) {
            e.printStackTrace();
      }

      return lstDadosSisaVinculado;
    }

    public List<DadosSisaPernoite> SmitAPISisaPernoiteCaller(BigInteger ciCidadao)
      throws IOException, URISyntaxException {

      lstDadosSisaPernoite.clear();
      HttpGet httpGet = new HttpGet("http://localhost:9090/cidadao/smit/sisap");

      URI uri = new URIBuilder(httpGet.getURI())
        .addParameter("ciCidadao", String.valueOf(ciCidadao))
        .build();

      httpGet.setURI(uri);
      try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

        HttpEntity entity = response.getEntity();

        if (entity != null) {
          // return it as a String
          String strPernResultRequest = EntityUtils.toString(entity);

          JSONArray arrResultRequest =  new JSONArray(strPernResultRequest);
          int lenghtListPern = arrResultRequest.length();

          for(int i = 0; i < lenghtListPern; i++){

            DadosSisaPernoite dadosSisaPernoite = new DadosSisaPernoite();
            JSONObject service = arrResultRequest.getJSONObject(i);

            String strPrimeiraData = service.getString("primeiraData");
            LocalDate date = LocalDate.parse(strPrimeiraData, inputFormatter);
            strPrimeiraData = outputFormatter.format(date);

            String strUltimaData = service.getString("ultimaData");
            date = LocalDate.parse(strUltimaData, inputFormatter);
            strUltimaData = outputFormatter.format(date);

            // Tratar tipos dos serviços que estão escritos por extenso
            DimTipoServico dimTipoServico = dimTipoServicoService.findDimTipoServico(service.getString("nmTipoServico"));
            String nmServicoResumido = dimTipoServico.getDcTipoSimplificado();

            dadosSisaPernoite.setPrimeiraData(strPrimeiraData);
            dadosSisaPernoite.setUltimaData(strUltimaData);
            dadosSisaPernoite.setQtdEstadias(service.getInt("qtdEstadias"));
            dadosSisaPernoite.setNmDistrito(service.getString("nmDistrito"));
            dadosSisaPernoite.setNmServico(service.getString("nmServico"));
            dadosSisaPernoite.setNmSubprefeitura(service.getString("nmSubprefeitura"));
            dadosSisaPernoite.setNmTipoServico(nmServicoResumido);
            //dadosSisaPernoite.setLstPernoitadas(service.get(""));

            lstDadosSisaPernoite.add(dadosSisaPernoite);
          }

        }
      } catch (ClientProtocolException | JSONException e) {
        e.printStackTrace();
      }

      return lstDadosSisaPernoite;
    }

    public List<DadosSiscr> SmitAPISiscrCaller(BigInteger ciCidadao) throws URISyntaxException, IOException {

      lstDadosSiscr.clear();
      HttpGet httpGet = new HttpGet("http://localhost:9091/it0101/siscr");

      URI uri = new URIBuilder(httpGet.getURI())
        .addParameter("ciCidadao", String.valueOf(ciCidadao))
        .build();

      httpGet.setURI(uri);

      try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

        HttpEntity entity = response.getEntity();

        if (entity != null) {
          String strSiscrResultRequest = EntityUtils.toString(entity);

          JSONArray arrResultRequest =  new JSONArray(strSiscrResultRequest);
          int lenghtListSiscr = arrResultRequest.length();

          for(int i = 0; i < lenghtListSiscr; i++){

            DadosSiscr dadosSiscr = new DadosSiscr();
            JSONObject service = arrResultRequest.getJSONObject(i);

            dadosSiscr.setDcEncaminhamento(service.getString("dcEncaminhamento"));
            dadosSiscr.setDcTipoProcura(service.getString("dcTipoProcura"));
            dadosSiscr.setDcMotivoProcura(service.getString("dcMotivoProcura"));
            dadosSiscr.setDcTipoProvidencia(service.getString("dcTipoProvidencia"));
            dadosSiscr.setDtAtualizacao(service.getString("dtAtualizacao"));
            dadosSiscr.setDtPreAtendimento(service.getString("dtPreAtendimento"));
            dadosSiscr.setNmCentroAssistencial(service.getString("nmCentroAssistencial"));
            dadosSiscr.setNmSubprefeitura(service.getString("nmSubprefeitura"));

            lstDadosSiscr.add(dadosSiscr);
          }

        }

      } catch (ClientProtocolException | JSONException e) {
        e.printStackTrace();
      }

      return lstDadosSiscr;
    }
}
