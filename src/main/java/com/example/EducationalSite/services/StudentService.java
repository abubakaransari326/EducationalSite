package com.example.EducationalSite.services;

import com.example.EducationalSite.models.Student;
import com.example.EducationalSite.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<Student> createStudent(Student student)
    {
        return new ResponseEntity(studentRepository.save(student), HttpStatus.OK);
    }

    //Update an already existing student
    public ResponseEntity<Student> updateStudent(long id, Student student)
    {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setFirstName(student.getFirstName());
            _student.setLastName(student.getLastName());
            _student.setCourses(student.getCourses());
            return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete a student
    public ResponseEntity<HttpStatus> deleteStudent(long id) {
        Student studentToDelete=studentRepository.findById(id).get();
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //View details of a student
    public ResponseEntity<Student> getStudentById(long id) {
        Optional<Student> studentData = studentRepository.findById(id);
        return studentData.map(student -> new ResponseEntity<>(student, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //List all students
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = new ArrayList<>(studentRepository.findAll());
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
