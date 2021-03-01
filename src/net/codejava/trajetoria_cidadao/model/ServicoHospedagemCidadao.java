package net.codejava.trajetoria_cidadao.model;

import java.util.Date;

public class ServicoHospedagemCidadao {
	
	private Integer cd_servico_hospedagem;
	private Integer cd_motivo_procura;
	private Integer cd_origem_procura;
	private Integer ci_cidadao;
	private Date dt_desligamento;
	
	public ServicoHospedagemCidadao(Integer cd_servico_hospedagem, Integer cd_motivo_procura, Integer cd_origem_procura,
			Integer ci_cidadao, Date dt_desligamento) {
		this.cd_servico_hospedagem = cd_servico_hospedagem;
		this.cd_motivo_procura = cd_motivo_procura;
		this.cd_origem_procura = cd_origem_procura;
		this.ci_cidadao = ci_cidadao;
		this.dt_desligamento = dt_desligamento;
	}
	
	public Integer getCd_servico_hospedagem() {
		return cd_servico_hospedagem;
	}
	public void setCd_servico_hospedagem(Integer cd_servico_hospedagem) {
		this.cd_servico_hospedagem = cd_servico_hospedagem;
	}
	public Integer getCd_motivo_procura() {
		return cd_motivo_procura;
	}
	public void setCd_motivo_procura(Integer cd_motivo_procura) {
		this.cd_motivo_procura = cd_motivo_procura;
	}
	public Integer getCd_origem_procura() {
		return cd_origem_procura;
	}
	public void setCd_origem_procura(Integer cd_origem_procura) {
		this.cd_origem_procura = cd_origem_procura;
	}
	public Integer getCi_cidadao() {
		return ci_cidadao;
	}
	public void setCi_cidadao(Integer ci_cidadao) {
		this.ci_cidadao = ci_cidadao;
	}
	public Date getDt_desligamento() {
		return dt_desligamento;
	}
	public void setDt_desligamento(Date dt_desligamento) {
		this.dt_desligamento = dt_desligamento;
	}
}
