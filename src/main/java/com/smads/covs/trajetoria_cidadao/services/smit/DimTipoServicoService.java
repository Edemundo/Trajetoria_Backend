package com.smads.covs.trajetoria_cidadao.services.smit;

import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DimTipoServico;
import com.smads.covs.trajetoria_cidadao.repositorys.DimTipoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DimTipoServicoService {

  private final DimTipoServicoRepository tipoServicoRepository;

  @Autowired
  public DimTipoServicoService(DimTipoServicoRepository tipoServicoRepository) {
    this.tipoServicoRepository = tipoServicoRepository;
  }

  public DimTipoServico findDimTipoServico (String dcTipoServico){
    return tipoServicoRepository.findByDcTipoServico(dcTipoServico);
  }
}
