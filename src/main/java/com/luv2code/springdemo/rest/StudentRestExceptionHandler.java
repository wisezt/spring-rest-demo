package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

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
