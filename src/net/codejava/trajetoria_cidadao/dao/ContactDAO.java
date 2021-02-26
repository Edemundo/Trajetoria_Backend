package net.codejava.trajetoria_cidadao.dao;

import java.util.List;

import net.codejava.trajetoria_cidadao.model.Contact;

public interface ContactDAO {
//	public int save(Contact contact);
//	
//	public int update(Contact contact);
//	
//	public Contact get(Integer id);
//	
//	public int delete(Integer id);
	
	public List<Contact> list();
}
