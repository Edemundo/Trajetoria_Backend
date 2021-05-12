package com.smads.covs.trajetoria_cidadao.services.info_pessoal;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimSituacaoCidadao;
import com.smads.covs.trajetoria_cidadao.repositorys.DimSituacaoCidadaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DimSituacaoCidadaoService {

  private final DimSituacaoCidadaoRepository dimSituacaoCidadaoRepository;

  @Autowired
  public DimSituacaoCidadaoService(DimSituacaoCidadaoRepository dimSituacaoCidadaoRepository) {
    this.dimSituacaoCidadaoRepository = dimSituacaoCidadaoRepository;
  }

  public DimSituacaoCidadao findByCiSitCidadao(Integer ciSitCidadao){
    return dimSituacaoCidadaoRepository.findByCiSitCidadao(ciSitCidadao);
  }
}
