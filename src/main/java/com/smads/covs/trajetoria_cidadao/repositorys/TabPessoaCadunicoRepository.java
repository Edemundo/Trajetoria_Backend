package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.TabPessoaCadunico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabPessoaCadunicoRepository extends JpaRepository<TabPessoaCadunico, String> {

  TabPessoaCadunico findByNumNisPessoaAtual(String cdNis);
}
