package com.market.crawler.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.crawler.daos.PersonDao;
import com.market.crawler.models.Person;

@Service
public class PersonService {
	
	private final PersonDao dao;

	@Autowired
	public PersonService(@Qualifier("postgres") PersonDao dao) {
		this.dao = dao;
	}
	
	public int addPerson(Person person) {
		return dao.insertPerson(person);
	}
	
	public List<Person> getAllPeople() {
		return dao.selectAllPeople();
	}
	
	public Optional<Person> getPersonById(UUID id) {
		return dao.selectPersonById(id);
	}
	
	public int deletePerson(UUID id) {
		return dao.delete(id);
	}
	
	public int updatePerson(UUID id, Person person) {
		return dao.update(id, person);
	}
	

}
