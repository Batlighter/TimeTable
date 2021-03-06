package org.itstep.controller;

import java.util.List;

import org.itstep.model.Student;
import org.itstep.model.Subject;
import org.itstep.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Student> save(@RequestBody Student student) {
		Student savedStudent = studentService.save(student);
		if(savedStudent != null) {
			return new ResponseEntity<Student>(savedStudent, HttpStatus.OK);
		}
		return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Student> update(@RequestBody Student student) {
		Student savedStudent = studentService.save(student);
		if(savedStudent != null) {
			return new ResponseEntity<Student>(savedStudent, HttpStatus.OK);
		}
		return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping( path = "/get-one", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	ResponseEntity<Student> getOne(@RequestBody String login) {
		Student student = studentService.get(login);
		if( student != null) {
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping( path = "/get-by-group", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	ResponseEntity<List<Student>> findAllByGroup(@RequestBody String groupName){
		List<Student> students = studentService.findAllByGroup(groupName);
		if(students.isEmpty() != true) {
			return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		}
		return new ResponseEntity<List<Student>>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping (consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE })
	ResponseEntity delete(@RequestBody Student student) {
		studentService.delete(student);
		return new ResponseEntity(HttpStatus.OK);
	
	}
}
