package com.smads.covs.trajetoria_cidadao.services.info_pessoal;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimCidadao;
import com.smads.covs.trajetoria_cidadao.repositorys.DimCidadaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class DimCidadaoService {
  private final DimCidadaoRepository dimCidadaoRepository;
  private List<DimCidadao> Response;

  @Autowired
  public DimCidadaoService(DimCidadaoRepository dimCidadaoRepository) {
    this.dimCidadaoRepository = dimCidadaoRepository;
  }

  public List<DimCidadao> findNrCpfAndCdNisAndNmCidadaoAndNmMaeAndDtNasc
    (BigInteger nrCpf, BigInteger cdNis, String nmCidadao, String nmMae, Date dtNasc) {

    if(nrCpf != null){
      Response = dimCidadaoRepository.findAllByNrCpf(nrCpf);
    } else if(cdNis != null){
      Response = dimCidadaoRepository.findAllByCdNis(cdNis);
    }else if(nmCidadao != null && nmMae != null && dtNasc != null){
      Response = dimCidadaoRepository.findAllByNmCidadaoAndNmMaeAndDtNasc(nmCidadao, nmMae, dtNasc);
    } else {
      /* Retornará codigo 404 quando não encontrar pessoa */
      return Response;
    }
    return Response;
  }

}
