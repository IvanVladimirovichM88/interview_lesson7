package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Student;
import ru.geekbrains.repositories.StudentRepository;
import ru.geekbrains.services.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("not found student with id = "+id));
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
