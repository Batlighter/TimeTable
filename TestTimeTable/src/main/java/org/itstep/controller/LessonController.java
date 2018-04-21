package org.itstep.controller;

import java.util.List;

import org.itstep.model.Lesson;
import org.itstep.model.Subject;
import org.itstep.service.LessonService;
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
@RequestMapping(path="/lesson")
public class LessonController {

	@Autowired
	 LessonService lessonService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Lesson> save(@RequestBody Lesson lesson) {
		Lesson savedLesson = lessonService.save(lesson);
		if(savedLesson != null) {
			return new ResponseEntity<Lesson>(savedLesson, HttpStatus.OK);
		}
		return new ResponseEntity<Lesson>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity update(@RequestBody Lesson lesson) {
		Lesson savedLesson = lessonService.save(lesson);
		if(savedLesson != null) {
			return new ResponseEntity(savedLesson, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping( path = "/get-one", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	ResponseEntity<Lesson> getOne(@RequestBody Integer id) {
		Lesson lesson = lessonService.get(id);
		if( lesson != null) {
			return new ResponseEntity<Lesson>(lesson, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping( path = "/get-all-by-time", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	ResponseEntity<List<Lesson>> getAllByTime(@RequestBody long start, long end) {
		List<Lesson> lessons = lessonService.findAllByStartTime(start, end);
		if( lessons != null) {
			return new ResponseEntity<List<Lesson>>(lessons, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping (consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE })
	ResponseEntity delete(@RequestBody Lesson lesson) {
		lessonService.delete(lesson);
		return new ResponseEntity(HttpStatus.OK);
	}
}
