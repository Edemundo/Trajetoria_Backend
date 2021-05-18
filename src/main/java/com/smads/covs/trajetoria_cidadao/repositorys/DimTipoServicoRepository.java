package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DimTipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimTipoServicoRepository extends JpaRepository<DimTipoServico, Integer> {

  DimTipoServico findByDcTipoServico (String dcTipoServico);
}
