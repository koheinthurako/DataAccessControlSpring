package com.dbtest.DBDemo.Controller;

import java.util.List;

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

import com.dbtest.DBDemo.DTO.StudentDTO;
import com.dbtest.DBDemo.entities.Student;
import com.dbtest.DBDemo.service.StudentService;

@RestController
@RequestMapping("/std")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Student> getStd(@PathVariable int id) {
		Student get_std = studentService.retrieve(id);
		return (get_std==null) ?
				new ResponseEntity<Student>(HttpStatus.BAD_REQUEST) :
				new ResponseEntity<Student>(get_std, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<StudentDTO> saveStd(@RequestBody StudentDTO std) {
		StudentDTO new_std = studentService.insert(std);
		return new ResponseEntity<StudentDTO>(new_std, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Student>> getAll() {
		return new ResponseEntity<List<Student>>(studentService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Student> getStdByName(@PathVariable String name) {
		Student get_std = studentService.findByName(name);
		return (get_std==null) ?
				new ResponseEntity<Student>(HttpStatus.BAD_REQUEST) :
				new ResponseEntity<Student>(get_std, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Student> updateStd(@RequestBody Student std) {
		Student update_std = studentService.updateStd(std);
		return (update_std==null) ?
				new ResponseEntity<Student>(HttpStatus.BAD_REQUEST) :
				new ResponseEntity<Student>(update_std, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public String deleteById(@RequestBody Student std) {
		return studentService.deleteById(std);
	}

	@GetMapping("/getDis/{mark}")
	public ResponseEntity<List<Student>> getDistinction(@PathVariable int mark) {
		return new ResponseEntity<List<Student>>(studentService.getDistinction(mark), HttpStatus.OK);
	}
	
	
}