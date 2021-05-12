package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimRaca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimRacaRepository extends JpaRepository<DimRaca, Integer> {

  DimRaca findByCiRaca(Integer ciRaca);
}
