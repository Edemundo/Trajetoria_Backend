package net.codejava.trajetoria_cidadao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import net.codejava.trajetoria_cidadao.model.ServicoHospedagemCidadao;

public class ServicoHospedagemCidadaoDAOImpl implements ServicoHospedagemCidadaoDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ServicoHospedagemCidadaoDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public ServicoHospedagemCidadao get(Integer ci_cidadao) {
		String sql = "SELECT * FROM fat_servico_hospedagem_cidadao WHERE =" + ci_cidadao + "AND dt_desligamento is NULL";
		
		ResultSetExtractor<ServicoHospedagemCidadao> extractor = new ResultSetExtractor<ServicoHospedagemCidadao>() {
	
			@Override
			public ServicoHospedagemCidadao extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Integer cd_servico_hospedagem = rs.getInt("cd_servico_hospedagem");
					Integer cd_motivo_procura = rs.getInt("cd_motivo_procura");
					Integer cd_origem_procura = rs.getInt("cd_origem_procura");
					Integer ci_cidadao = rs.getInt("ci_cidadao");
					Date dt_desligamento = rs.getDate("dt_desligamento");
					
					return new ServicoHospedagemCidadao(cd_servico_hospedagem, cd_motivo_procura, cd_origem_procura, ci_cidadao, dt_desligamento); 
				}
				return null;
			}			
		};
		
		return jdbcTemplate.query(sql, extractor);
	}
}
