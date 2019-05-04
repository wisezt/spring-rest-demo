package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> theStudents;
//    public StudentRestController() {
//        theStudents = new ArrayList<>();
//        theStudents.add(new Student("Ting001", "Zheng001"));
//        theStudents.add(new Student("Ting002", "Zheng002"));
//        theStudents.add(new Student("Ting003", "Zheng003"));
//
//    }

    @PostConstruct
    public void loadData(){
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

        // check the sutdentId against list size

        if ((studentId >= theStudents.size()) || (studentId <0)){
            throw new StudentNotFoundException("Stduent id not found - " + studentId);
        }



        return theStudents.get(studentId);

    }

    // Add an excepitonhandler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse theStudentErrorResponse = new StudentErrorResponse();
        theStudentErrorResponse.setMessage(exc.getMessage());
        theStudentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        theStudentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(theStudentErrorResponse, HttpStatus.NOT_FOUND);
    }


    // add another exception handler ... to catch any exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        StudentErrorResponse theStudentErrorResponse = new StudentErrorResponse();
        theStudentErrorResponse.setMessage(exc.getMessage());
        theStudentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        theStudentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(theStudentErrorResponse, HttpStatus.BAD_REQUEST);
    }


}
