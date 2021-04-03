package ru.geekbrains.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Long id);
    void deleteById(Long id);
    Student save(Student student);
}
