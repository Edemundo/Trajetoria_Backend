package com.smads.covs.trajetoria_cidadao.repositorys;

import com.smads.covs.trajetoria_cidadao.models.info_pessoal.DimCidadao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface DimCidadaoRepository extends JpaRepository<DimCidadao, BigInteger> {

  List<DimCidadao> findAllByNrCpf(BigInteger nrCpf);

  List<DimCidadao> findAllByCdNis(BigInteger cdNis);

  List<DimCidadao> findAllByNmCidadaoAndNmMaeAndDtNasc(String nmCidadao, String nmMae, Date dtNasc);

}
