package com.example.EducationalSite.services;

import com.example.EducationalSite.models.Course;
import com.example.EducationalSite.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public ResponseEntity<Course> createCourse(Course course)
    {
        return new ResponseEntity(courseRepository.save(course), HttpStatus.OK);
    }


    //Update an already existing course
    public ResponseEntity<Course> updateCourse(long id, Course course)
    {
        Optional<Course> courseData = courseRepository.findById(id);

        if (courseData.isPresent()) {
            Course _course = courseData.get();
            _course.setTitle(course.getTitle());
            _course.setMaxStudents(course.getMaxStudents());

            return new ResponseEntity<>(courseRepository.save(_course), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete a course
    public ResponseEntity<HttpStatus> deleteCourse(long id) {
        Course courseToDelete=courseRepository.findById(id).get();
        try {
            courseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //View details of a course
    public ResponseEntity<Course> getCourseById(long id) {
        Optional<Course> courseData = courseRepository.findById(id);
        return courseData.map(course -> new ResponseEntity<>(course, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //List all courses
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = new ArrayList<>(courseRepository.findAll());
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
