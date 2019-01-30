package com.example.demo.db.repositories;

import com.example.demo.db.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author Satish Sharma
 * <pre>
 *  	JPA repository interface for {@link PersonEntity} class
 * </pre>
 */
@Repository
public interface PersonRepository  extends JpaRepository<PersonEntity, Integer> {

}
