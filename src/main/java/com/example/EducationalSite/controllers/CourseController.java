package com.example.EducationalSite.controllers;

import com.example.EducationalSite.models.Course;
import com.example.EducationalSite.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    //Create a new Course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course)
    {
        return courseService.createCourse(course);
    }

    //Update an already existing course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course course)
    {
        return courseService.updateCourse(id, course);
    }

    //Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        return courseService.deleteCourse(id);
    }

    //View details of a course
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
        return courseService.getCourseById(id);
    }

    //List all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return courseService.getAllCourses();
    }
}
