package com.example.EducationalSite.controllers;

import com.example.EducationalSite.models.Student;
import com.example.EducationalSite.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //Create a new Student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student)
    {
        return studentService.createStudent(student);
    }

    //Update an already existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student)
    {
        return studentService.updateStudent(id, student);
    }

    //Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }

    //View details of a student
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }

    //List all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }
}
