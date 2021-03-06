package io.github.Hattinger04.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.Hattinger04.RestServices;
import io.github.Hattinger04.user.model.User;
import io.github.Hattinger04.user.model.UserService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private UserService userService;
	@Autowired
	private RestServices restServices; 

	
	/**
	 * Get all students in class from database
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('TEACHER')")
	@PostMapping("/getAllStudents")
	@ResponseBody
	public ResponseEntity<?> getAllStudents(@RequestBody String json) {
		// TODO: adding to database class table or smth like that
		// and return here all students of class
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@PreAuthorize("hasAuthority('TEACHER')")
	@PutMapping("/createClass")
	@ResponseBody
	public ResponseEntity<?> createClass(@RequestBody String json) {
		// TODO: Create new class with teacher and students
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('TEACHER')")
	@DeleteMapping("/deleteClass")
	@ResponseBody
	public ResponseEntity<?> deleteClass(@RequestBody String json) {
		// TODO: deleting existing class and remove all database entries
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('TEACHER')")
	@PostMapping("/addStudentClass")
	@ResponseBody
	public ResponseEntity<?> addStudentClass(@RequestBody String json) {
		// TODO: adding students to class 
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@PreAuthorize("hasAuthority('TEACHER')")
	@DeleteMapping("/removeStudentClass")
	@ResponseBody
	public ResponseEntity<?> removeStudentClass(@RequestBody String json) {
		// TODO: removing students from class 
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@PreAuthorize("hasAuthority('TEACHER')")
	@PutMapping("/createExercise")
	@ResponseBody
	public ResponseEntity<?> createExercise(@RequestBody String json) {
		// TODO: create new exercise for class 
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@PreAuthorize("hasAuthority('TEACHER')")
	@PostMapping("/rateExercise")
	@ResponseBody
	public ResponseEntity<?> rateExercise(@RequestBody String json) {
		// TODO: rate exercise from student 
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@PreAuthorize("hasAuthority('TEACHER')")
	@PostMapping("/publishExercise")
	@ResponseBody
	public ResponseEntity<?> publishExercise(@RequestBody String json) {
		// TODO: rate exercise from student 
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@PreAuthorize("hasAuthority('TEACHER')")
	@PutMapping("/deleteExercise")
	@ResponseBody
	public ResponseEntity<?> deleteExercise(@RequestBody String json) {
		// TODO: delete existing exercise
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}
