package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> theStudents;
    public StudentRestController() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Ting001", "Zheng001"));
        theStudents.add(new Student("Ting002", "Zheng002"));
        theStudents.add(new Student("Ting003", "Zheng003"));

    }



    // define endpoint for "/students" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){


        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        return theStudents.get(studentId);

    }


}
