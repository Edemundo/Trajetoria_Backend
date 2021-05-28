package com.smads.covs.trajetoria_cidadao.controllers;

  import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimCidadao;
  import com.smads.covs.trajetoria_cidadao.services.info_pessoal.DimCidadaoService;
  import org.json.JSONException;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  import java.math.BigInteger;
  import java.text.DateFormat;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.Date;
  import java.util.List;

@RestController
@RequestMapping("/cidadao")
public class ApiRestController {

  private final DimCidadaoService dimCidadaoService;

  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  public ApiRestController(DimCidadaoService dimCidadaoService) {
    this.dimCidadaoService = dimCidadaoService;
  }
  @CrossOrigin
  @GetMapping("/find/{nrCpf}/{cdNis}/{nmCidadao}/{nmMae}/{dtNasc}")
  public ResponseEntity<List<DimCidadao>> getDimCidadaos(@PathVariable("nrCpf") String nrCpf, @PathVariable("cdNis") String cdNis,
                                                         @PathVariable("nmCidadao") String nmCidadao, @PathVariable("nmMae") String nmMae,
                                                         @PathVariable("dtNasc") String dtNasc) throws ParseException {

    Date dtNascimento;
    BigInteger intNrCpf;
    BigInteger intCdNis;
    int i;

    if(dtNasc.equals("0")){
      dtNascimento = null;
    } else {
      dtNascimento = dateFormat.parse(dtNasc);
    }

    if(nrCpf.equals("0")){
      intNrCpf = null;
    } else {
      intNrCpf = new BigInteger(nrCpf);
    }

    if(cdNis.equals("0")){
      intCdNis = null;
    } else {
      intCdNis = new BigInteger(cdNis);
    }

    List<DimCidadao> dimCidadaos =  dimCidadaoService.findNrCpfAndCdNisAndNmCidadaoAndNmMaeAndDtNasc
      (intNrCpf, intCdNis, nmCidadao, nmMae, dtNascimento);

    for(i = 0;i < dimCidadaos.size(); i++){
      if(dimCidadaos.get(i).getCdNis() == null && intCdNis != null){
        dimCidadaos.get(i).setCdNis(intCdNis);
      }
    }

    if(dimCidadaos == null){
      return new ResponseEntity<>(dimCidadaos, HttpStatus.NOT_FOUND);
    }
    else {
      return new ResponseEntity<>(dimCidadaos, HttpStatus.OK);
    }
  }

}
