package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.web.models.Person;
import org.springframework.stereotype.Service;


/**
 * 
 * @author Satish Sharma
 * 
 * <pre>
 *  Service class for CRUD operation on person
 * </pre>
 */
@Service
public interface PersonService {
	/**
	 * 
	 * @param personId
	 * @return {@link Optional} {@link Person} objects if present in database
	 *         for supplied person ID
	 */
	public Optional<Person> getPersonById(int personId);

	/**
	 * 
	 * @return {@link List} of {@link Person} model class fo rall available
	 *         entities
	 */
	public List<Person> getAllPersons();

	/**
	 * 
	 * @param personId
	 * @return Delete the person from database for supplied id
	 */
	public boolean removePerson(int personId);

	/**
	 * 
	 * @param person
	 * @return {@link Optional} {@link Person} objects after save or update Save
	 *         if no personId present else update
	 */
	public Optional<Person> saveUpdatePerson(Person person);
}
