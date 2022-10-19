package com.example.EducationalSite.services;

import com.example.EducationalSite.models.Course;
import com.example.EducationalSite.models.Student;
import com.example.EducationalSite.repositories.CourseRepository;
import com.example.EducationalSite.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<Student> createStudent(Student student)
    {
        return new ResponseEntity(studentRepository.save(student), HttpStatus.OK);
    }
}
