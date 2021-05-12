package com.smads.covs.trajetoria_cidadao.controllers;

import com.smads.covs.trajetoria_cidadao.models.cidadao_detalhado.CidadaoDetalhado;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaVinculado;
import com.smads.covs.trajetoria_cidadao.models.sisa_sicr_sisrua.DadosSisaPernoite;
import com.smads.covs.trajetoria_cidadao.services.smit.SmitService;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class SmitAPICallerController {

    private CidadaoDetalhado cidadaoDetalhado = new CidadaoDetalhado();
    private final SmitService smitService;

    public SmitAPICallerController(SmitService smitService) {
        this.smitService = smitService;
    }

    public CidadaoDetalhado getServicosData(BigInteger ciCidadao) throws URISyntaxException, IOException {

        List<DadosSisaVinculado> resultSmitSisa = smitService.SmitAPISisaCaller(ciCidadao);
        List<DadosSisaPernoite> resultSmitSisaPernoite = smitService.SmitAPISisaPernoiteCaller(ciCidadao);
        //List<DadosSiscr> resultSmitSiscr = smitService.SmitAPISiscrCaller(ciCidadao);

        cidadaoDetalhado.setLstSisa(resultSmitSisa);
        cidadaoDetalhado.setLstSisaPernoite(resultSmitSisaPernoite);
        // cidadaoDetalhado.setLstSiscr(resultSmitSiscr);

        return cidadaoDetalhado;
    }
}
