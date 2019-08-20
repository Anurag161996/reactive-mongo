package com.tech.reactive.reactivemongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.tech.recative.reactivemongo.model.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

}
