package com.example.demo.web.rest;

import com.example.demo.services.PersonService;
import com.example.demo.web.models.Person;
import io.micrometer.core.annotation.Timed;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/person")
public class PersonResource {

	private static final Random random = new Random();

	private final PersonService personService;

	@Autowired
	PersonResource(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public List<Person> getAllPerson() {
		return personService.getAllPersons();
	}

	@Timed(histogram = true, percentiles = {0.5, 0.95})
	@GetMapping(value = "/{personId}")
	public ResponseEntity<Person> getPerson(@PathVariable("personId") int personId) throws InterruptedException {
		Thread.sleep(random.nextInt(500));
		return personService.getPersonById(personId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Person> addNewPerson(@RequestBody Person person) {
		return personService.saveUpdatePerson(person).map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
	}

	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		return personService.saveUpdatePerson(person).map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
	}

	@DeleteMapping(value = "/{personId}")
	public ResponseEntity<String> deletePerson(@PathVariable("personId") int personId) {
        if (personService.removePerson(personId)) {
            return ResponseEntity.ok("Person with id : " + personId + " removed");
        } else {
            return new ResponseEntity<>("Error deleting enitty ", HttpStatus.EXPECTATION_FAILED);
        }
	}
}
