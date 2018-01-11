package example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import example.model.Student;
import example.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;


	@PostMapping("/student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
		studentService.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


	@GetMapping("/students/{studentId}")
	public Student retrieveStudent(@PathVariable String studentId) {
		return studentService.retrieveStudent(studentId);
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentService.retrieveStudents();
	}

	@GetMapping("/students/page")
	public List<Student> getStudentsByName(@RequestParam String name) {
		Page<Student> resultPage = studentService.findByName(name, new PageRequest(0, 2));
		return resultPage.getContent();
	}
}