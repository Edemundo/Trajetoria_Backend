package com.smads.covs.trajetoria_cidadao.services.info_pessoal;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimTipoSexo;
import com.smads.covs.trajetoria_cidadao.repositorys.DimTipoSexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DimTipoSexoService {

  private final DimTipoSexoRepository dimTipoSexoRepository;

  @Autowired
  public DimTipoSexoService(DimTipoSexoRepository dimTipoSexoRepository) {
    this.dimTipoSexoRepository = dimTipoSexoRepository;
  }

  public DimTipoSexo findByCiTipoSexo(Integer ciTipoSexo){

    return dimTipoSexoRepository.findByCiTipoSexo(ciTipoSexo);
  }
}
