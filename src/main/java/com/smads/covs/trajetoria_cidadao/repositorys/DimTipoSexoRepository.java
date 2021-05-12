package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimTipoSexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimTipoSexoRepository extends JpaRepository<DimTipoSexo, Integer> {

  DimTipoSexo findByCiTipoSexo(Integer ciTipoSexo);
}
