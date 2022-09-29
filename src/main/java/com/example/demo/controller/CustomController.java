package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Person;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

@RestController
public class CustomController {
	
	@PostMapping("/home")
	public ResponseEntity<String> getHook(@RequestBody MultipartFile file) throws IOException {
		System.out.println("CustomController.getHook()" + file);
		System.out.println("Hello world");
		MappingIterator<Person> personIter = new CsvMapper().readerWithTypedSchemaFor(Person.class).readValues(file.getInputStream());
		List<Person> people = personIter.readAll();
		people.forEach(System.out::println);
		return new ResponseEntity<String>(file.getName(), HttpStatus.OK);
	}

}
