package net.codejava.trajetoria_cidadao.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.codejava.trajetoria_cidadao.model.DimSubprefeituras;

class ContactDAOTest {

	private DriverManagerDataSource dataSource;
	private DimSubprefeiturasDAO dao;
	
	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		
		dao = new DimSubprefeiturasDAOImpl(dataSource);
	}
	
//	@Test
//	void testSave() {		
//		Contact contact = new Contact("Alisson Rodrigues", "alissonr@gmail.com", "São Paulo, SP - Brasil", "5511950893745");
//		int result = dao.save(contact);
//		
//		assertTrue(result > 0);
//		
//	}

//	@Test
//	void testUpdate() {
//		Contact contact = new Contact(1, "Victor Souza", "victordesgm@gmail.com", "Suzano, SP - Brasil", "5511950891895");
//		int result = dao.update(contact);
//		
//		assertTrue(result > 0);
//	}

//	@Test
//	void testGet() {
//		Integer id = 1;
//		Contact contact = dao.get(id);
//		
//		if(contact != null) {
//			System.out.println(contact);
//		}
//		assertNotNull(contact);
//	}

//	@Test
//	void testDelete() {
//		Integer id = 3;
//		int result = dao.delete(id);
//		
//		assertTrue(result > 0);
//	}

	@Test
	void testList() {
		List<DimSubprefeituras> listContacts = dao.list();
		
		for(DimSubprefeituras aContact : listContacts) {
			System.out.println(aContact);
		}
		
		assertTrue(!listContacts.isEmpty());
	}

}
