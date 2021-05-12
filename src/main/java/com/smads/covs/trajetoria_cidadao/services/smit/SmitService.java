package com.smads.covs.trajetoria_cidadao.services.smit;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimCidadao;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaVinculado;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaPernoite;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SmitService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final List<DadosSisaPernoite> lstDadosSisaPernoite;
    private final List<DadosSisaVinculado> lstDadosSisaVinculado;

  public SmitService(List<DadosSisaPernoite> lstDadosSisaPernoite, List<DadosSisaVinculado> lstDadosSisaVinculado) {
    this.lstDadosSisaPernoite = lstDadosSisaPernoite;
    this.lstDadosSisaVinculado = lstDadosSisaVinculado;
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
            Integer lenghtListVinc = arrResultRequest.length();

            for(int i = 0; i< lenghtListVinc; i++){

              DadosSisaVinculado dadosSisaVinculado = new DadosSisaVinculado();
              JSONObject service = arrResultRequest.getJSONObject(i);

              dadosSisaVinculado.setNmDistrito(service.getString("nmDistrito"));
              dadosSisaVinculado.setNmSubprefeitura(service.getString("nmSubprefeitura"));
              dadosSisaVinculado.setNmServico(service.getString("nmServico"));
              dadosSisaVinculado.setDcTipoServico(service.getString("nmTipoServico"));
              dadosSisaVinculado.setDcMotivoDesligamento(service.getString("nmMotivoDesligamento"));
              dadosSisaVinculado.setDcMotivoProcura(service.getString("nmMotivoProcura"));
              dadosSisaVinculado.setDcOrigemProcura(service.getString("nmOrigemProcura"));
              dadosSisaVinculado.setDtVinculacao(service.getString("dtVinculacao"));
              dadosSisaVinculado.setDtDesligamento(service.getString("dtDesligamento"));

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
          Integer lenghtListPern = arrResultRequest.length();

          for(int i = 0; i < lenghtListPern; i++){

            DadosSisaPernoite dadosSisaPernoite = new DadosSisaPernoite();
            JSONObject service = arrResultRequest.getJSONObject(i);

            dadosSisaPernoite.setDtHospedagem(service.getString("dtHospedagem"));
            dadosSisaPernoite.setNmDistrito(service.getString("nmDistrito"));
            dadosSisaPernoite.setNmServico(service.getString("nmServico"));
            dadosSisaPernoite.setNmSubprefeitura(service.getString("nmSubprefeitura"));
            dadosSisaPernoite.setNmTipoServico(service.getString("nmTipoServico"));

            lstDadosSisaPernoite.add(dadosSisaPernoite);
          }

        }
      } catch (ClientProtocolException | JSONException e) {
        e.printStackTrace();
      }

      return lstDadosSisaPernoite;
    }

//    public List<DadosSiscr> SmitAPISiscrCaller(BigInteger ciCidadao){
//
//        // Chamar API
//
//        return null;
//    }
}
