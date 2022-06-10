package com.market.crawler.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.crawler.models.Person;
import com.market.crawler.services.PersonService;

@RequestMapping("/crawler")
@RestController
public class PersonController {

	private final PersonService service;

	public PersonController(PersonService service) {
		this.service = service;
	}
	
	@PostMapping
	public void addPerson(@Valid @Null @RequestBody Person person) {
		service.addPerson(person);
	}
	
	@GetMapping
	public List<Person> getAllPeople() {
		return service.getAllPeople();
	}
	
	@GetMapping(path = "/{id}")
	public Person getPersonById(@PathVariable("id") UUID id) {
		return service.getPersonById(id).orElse(null);
	}
	
	@PutMapping(path = "/{id}")
	public void updatePerson(@PathVariable("id") UUID id,
							 @Valid @NotNull @RequestBody Person person) {
		
		service.updatePerson(id, person);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deletePerson(@PathVariable("id") UUID id) {
		service.deletePerson(id);
	}
	
	
}
