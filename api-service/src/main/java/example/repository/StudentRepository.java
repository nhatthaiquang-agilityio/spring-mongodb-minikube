package example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import example.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
    public Student findById(String id);

    public List<Student> findFirst10ByName(@Param("name") String name);

    Page<Student> findByName(String name, Pageable pageable);
}