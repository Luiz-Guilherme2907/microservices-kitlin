package com.guilherme.microserviceskotlin.services

import com.guilherme.microserviceskotlin.exceptions.ResourceNotFoundException
import com.guilherme.microserviceskotlin.model.Person
import com.guilherme.microserviceskotlin.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger


@Service
class PersonServices {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findAll(): List<Person> {
        logger.info("Finding all people!")

        return repository.findAll()
    }


    fun findById(id: Long): Person {
        logger.info("Finding one person!")
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this id!") }
    }


    fun createPeople(person: Person): Person {
        logger.info("Creating One Person with name ${person.firstName}")
        return repository.save(person)
    }

    fun updatePeople(person: Person) : Person {
        logger.info("Updating One Person with ID ${person.id}")
        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this id!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.adress = person.adress
        entity.gender = person.adress
        return repository.save(entity)
    }


    fun deletePeople(id: Long) {
        logger.info("Deleting One Person with ID $id")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this id!") }
        repository.delete(entity)

    }
}
