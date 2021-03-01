package net.codejava.trajetoria_cidadao.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.codejava.trajetoria_cidadao.model.DimSubprefeituras;

public class DimSubprefeiturasDAOImpl implements DimSubprefeiturasDAO {

	private JdbcTemplate jdbcTemplate;
	
	public DimSubprefeiturasDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//	@Override
//	public int save(Contact c) {
//		String sql = "INSERT INTO Contact (name, email, address, phone) VALUES (?, ?, ?, ?)";
//		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getPhone());
//	}

//	@Override
//	public int update(Contact c) {
//		String sql = "UPDATE Contact SET name=?, email=?, address=?, phone=? WHERE contact_id=?";
//		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getPhone(), c.getId());
//	}

//	@Override
////	public Contact get(Integer id) {
////		String sql = "SELECT * FROM Contact WHERE contact_id=" + id;
////		
////		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {
////
////			@Override
////			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
////				if(rs.next()) {
////					String name = rs.getString("name");
////					String email = rs.getString("email");
////					String address = rs.getString("address");
////					String phone = rs.getString("phone");
////					
////					return new Contact(id, name, email, address, phone); 
////				}
////				return null;
////			}			
////		};
////		
////		return jdbcTemplate.query(sql, extractor);
////	}

//	@Override
//	public int delete(Integer id) {
//		String sql = "DELETE FROM Contact WHERE contact_id=" + id;
//		
//		return jdbcTemplate.update(sql);
//	}

	@Override
	public List<DimSubprefeituras> list() {
		String sql = "SELECT * FROM dim_subprefeitura";
		
		RowMapper<DimSubprefeituras> rowMapper = new RowMapper<DimSubprefeituras>() {

			@Override
			public DimSubprefeituras mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer cd_subprefeitura = rs.getInt("cd_subprefeitura");
				String nm_subprefeitura = rs.getString("nm_subprefeitura");
				String cd_sigla = rs.getString("cd_sigla");
				Integer ci_ativo_inativo = rs.getInt("ci_ativo_inativo");
				Date dt_carga = rs.getDate("dt_carga");
				String origem_dado = rs.getString("origem_dado");
				
				return new DimSubprefeituras(cd_subprefeitura, nm_subprefeitura, cd_sigla, ci_ativo_inativo, dt_carga, origem_dado); 
			}
			
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

}
