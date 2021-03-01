package net.codejava.trajetoria_cidadao.service;

import org.springframework.stereotype.Service;

import net.codejava.trajetoria_cidadao.model.DimCidadao;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DimCidadaoService {

	public Product get(Integer id) {
		return repo.findById(id).get();
	}
}
