package net.codejava.trajetoria_cidadao.model;

import java.sql.Date;

public class Contact {
	
	private Integer cd_subprefeitura;
	private String nm_subprefeitura;
	private String cd_sigla;
	private Integer ci_ativo_inativo;
	private Date dt_carga;
	private String origem_dado;
	
	public Contact(Integer cd_subprefeitura, String nm_subprefeitura, String cd_sigla, Integer ci_ativo_inativo, Date dt_carga, String origem_dado) {
		
		this(nm_subprefeitura, cd_sigla, ci_ativo_inativo, dt_carga, origem_dado);
		this.setCd_subprefeitura(cd_subprefeitura);
	}
	
	public Contact() {
		
	}
	
	public Contact(String nm_subprefeitura, String cd_sigla, Integer ci_ativo_inativo, Date dt_carga, String origem_dado) {
		this.nm_subprefeitura = nm_subprefeitura;
		this.cd_sigla = cd_sigla;
		this.ci_ativo_inativo = ci_ativo_inativo;
		this.dt_carga = dt_carga;
		this.origem_dado = origem_dado;
	}

	@Override
	public String toString() {
		return "Contact [cd_subprefeitura=" + cd_subprefeitura + ", nm_subprefeitura=" + nm_subprefeitura
				+ ", cd_sigla=" + cd_sigla + ", ci_ativo_inativo=" + ci_ativo_inativo + ", dt_carga=" + dt_carga
				+ ", origem_dado=" + origem_dado + "]";
	}

	public Integer getCd_subprefeitura() {
		return cd_subprefeitura;
	}

	public void setCd_subprefeitura(Integer cd_subprefeitura) {
		this.cd_subprefeitura = cd_subprefeitura;
	}

	public String getNm_subprefeitura() {
		return nm_subprefeitura;
	}

	public void setNm_subprefeitura(String nm_subprefeitura) {
		this.nm_subprefeitura = nm_subprefeitura;
	}

	public String getCd_sigla() {
		return cd_sigla;
	}

	public void setCd_sigla(String cd_sigla) {
		this.cd_sigla = cd_sigla;
	}

	public Integer getCi_ativo_inativo() {
		return ci_ativo_inativo;
	}

	public void setCi_ativo_inativo(Integer ci_ativo_inativo) {
		this.ci_ativo_inativo = ci_ativo_inativo;
	}

	public Date getDt_carga() {
		return dt_carga;
	}

	public void setDt_carga(Date dt_carga) {
		this.dt_carga = dt_carga;
	}

	public String getOrigem_dado() {
		return origem_dado;
	}

	public void setOrigem_dado(String origem_dado) {
		this.origem_dado = origem_dado;
	}
	
	

}
