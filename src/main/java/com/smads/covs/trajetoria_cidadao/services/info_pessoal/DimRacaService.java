package com.smads.covs.trajetoria_cidadao.services.info_pessoal;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimRaca;
import com.smads.covs.trajetoria_cidadao.repositorys.DimRacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DimRacaService {

  private final DimRacaRepository dimRacaRepository;

  @Autowired
  public DimRacaService(DimRacaRepository dimRacaRepository) {
    this.dimRacaRepository = dimRacaRepository;
  }

  public DimRaca findByCiRaca(Integer ciRaca){
    return dimRacaRepository.findByCiRaca(ciRaca);
  }
}
