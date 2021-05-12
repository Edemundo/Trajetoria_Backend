package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.TabFamiliaCadunico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabFamiliaCadunicoRepository extends JpaRepository<TabFamiliaCadunico, String> {

  TabFamiliaCadunico findByCodFamiliarFam(String CodFamiliarFam);
}
