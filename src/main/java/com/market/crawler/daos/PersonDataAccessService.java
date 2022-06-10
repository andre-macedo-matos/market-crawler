package com.market.crawler.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.market.crawler.models.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insertPerson(UUID id, Person person) {
		String sql = "insert into person(id, name) values(?,?)";
		
		return jdbcTemplate.update(sql, id, person.getName());
	}


	@Override
	public List<Person> selectAllPeople() {
		String sql = "select id, name from person";
		List<Person> people = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
			UUID id = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new Person(id, name);
		});
		
		return people;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		String sql = "select id, name from person where id = ?";
		Person findPerson = jdbcTemplate.queryForObject(sql, 
				(resultSet, rowNum) -> {
					UUID uuid = UUID.fromString(resultSet.getString("id"));
					String name = resultSet.getString("name");
					return new Person(uuid, name);
		}, id);
		
		return Optional.ofNullable(findPerson);
	}

	@Override
	public int delete(UUID id) {
		String sql = "delete from person where id = ?";
		
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int update(UUID id, Person person) {
		String sql = "update person set name = ? where id = ?";
		
		return jdbcTemplate.update(sql, person.getName(), id);
	}

}
