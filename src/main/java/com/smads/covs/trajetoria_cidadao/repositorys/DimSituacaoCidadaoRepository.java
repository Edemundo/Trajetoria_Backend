package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimSituacaoCidadao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimSituacaoCidadaoRepository extends JpaRepository<DimSituacaoCidadao, Integer> {

  DimSituacaoCidadao findByCiSitCidadao(Integer ciSitCidadao);
}
