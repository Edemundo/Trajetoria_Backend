package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimPaisOrigem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimPaisOrigemRepository extends JpaRepository <DimPaisOrigem, Integer> {

  DimPaisOrigem findByCiPaisOrigem(Integer ciPaisOrigem);
}
