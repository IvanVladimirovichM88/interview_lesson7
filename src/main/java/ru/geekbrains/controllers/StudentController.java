package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.entities.Student;
import ru.geekbrains.services.StudentService;

import javax.persistence.GeneratedValue;
import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/allStudents")
    public ModelAndView showAllStudents(){
        ModelAndView modelAndView = new ModelAndView();
        List<Student> students = studentService.findAll();
        modelAndView.addObject("students", students);
        modelAndView.setViewName("allStudents");
        return modelAndView;
    }

    @GetMapping("/addStudent")
    public ModelAndView showFormAddStudent(){
        ModelAndView modelAndView = new ModelAndView("addStudent");
        modelAndView.addObject("headerMessage","Add Student details");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView saveNewStudent(@ModelAttribute Student student){
        ModelAndView modelAndView = new ModelAndView("redirect:/allStudents");
        studentService.save(student);
        return modelAndView;
    }

    @GetMapping("/deleteStudent/{id}")
    public ModelAndView deleteStudentById(@PathVariable Long id){
        studentService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/allStudents");
        return modelAndView;
    }

    @GetMapping("/editStudent/{id}")
    public ModelAndView showEditStudentForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("editStudent");
        modelAndView.addObject("headerMessage","Edit Student");
        modelAndView.addObject("student", studentService.findById(id));
        return  modelAndView;
    }

    @PostMapping("/editStudent/{id}")
    public ModelAndView editStudent(@ModelAttribute Student student){
        ModelAndView modelAndView = new ModelAndView("redirect:/allStudents");
        studentService.save(student);
        return modelAndView;
    }
}
