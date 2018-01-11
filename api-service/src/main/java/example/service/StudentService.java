package example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import example.model.Student;
import example.repository.StudentRepository;


@Service
public class StudentService {
	@Autowired
    StudentRepository repository;

    public void save(Student student) {
		repository.save(student);
	}

	public Page<Student> listAllByPage(Pageable pageable) {
		return repository.findAll(pageable);
    }

	public List<Student> retrieveStudents() {
		return repository.findAll();
    }

    public Student retrieveStudent(String studentId) {
		return repository.findById(studentId);
	}

	public List<Student> findFirst10ByName(String name) {
		return repository.findFirst10ByName(name);
	}

	public Page<Student> findByName(String name, PageRequest pageRequest) {
        return repository.findByName(name, pageRequest);
	}

}