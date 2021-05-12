package com.smads.covs.trajetoria_cidadao.services.info_pessoal;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.TabPessoaCadunico;
import com.smads.covs.trajetoria_cidadao.repositorys.TabPessoaCadunicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TabPessoaCadunicoService {

  private final TabPessoaCadunicoRepository tabPessoaCadunicoRepository;

  @Autowired
  public TabPessoaCadunicoService(TabPessoaCadunicoRepository tabPessoaCadunicoRepository) {
    this.tabPessoaCadunicoRepository = tabPessoaCadunicoRepository;
  }

  public TabPessoaCadunico findByNumNisPessoaAtual(String cdNis){
    return tabPessoaCadunicoRepository.findByNumNisPessoaAtual(cdNis);
  }
}
