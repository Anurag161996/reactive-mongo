package com.tech.reactive.reactivemongo.controller;


import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.reactive.reactivemongo.repository.PersonRepository;
import com.tech.recative.reactivemongo.model.Person;
import com.tech.recative.reactivemongo.model.PersonEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/hello")
	public String sayHi() {
		return "Hi";
	}
	
	@GetMapping("/all")
	public Flux<Person> getAll() {
		return personRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Person> getId(@PathVariable final String id) {
		return personRepository.findById(id);
	}
	
	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<PersonEvent> getEvents(@PathVariable final String id) {
		return personRepository.findById(id)
		.flatMapMany(person -> {
			Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(2));
			Flux<PersonEvent> personEventFlux = Flux.fromStream(Stream.generate(() -> 
			new PersonEvent(person, new Date())));
			return Flux.zip(intervalFlux, personEventFlux)
			.map(objects -> {
				return objects.getT2();
			});
		});
	}
	
	@PostMapping("/add")
	public Mono<Person> addPerson(@RequestBody Person person) {
		return personRepository.save(person);
	}
	
}