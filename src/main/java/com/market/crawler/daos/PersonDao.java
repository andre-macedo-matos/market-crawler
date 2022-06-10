package com.market.crawler.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.market.crawler.models.Person;

public interface PersonDao {
	
	int insertPerson(UUID id, Person person);
	
	default int insertPerson(Person person) {
		return insertPerson(UUID.randomUUID(), person);
	}
	
	List<Person> selectAllPeople();
	
	Optional<Person> selectPersonById(UUID id);
	
	int delete(UUID id);
	
	int update(UUID id, Person person);

}
