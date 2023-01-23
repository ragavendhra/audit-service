package com.jsw.auditSystem.exceptionHandler;
import com.jsw.auditSystem.exceptions.AddressInfoNotFoundException;
import com.jsw.auditSystem.exceptions.UserInfoNotFoundException;
import com.jsw.auditSystem.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserInfoNotFoundException.class)
    public ResponseEntity<?> UserInfoNotFoundException(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        return new ResponseEntity<>(new ErrorResponse(status, e.getMessage()), status);
    }
    @ExceptionHandler(AddressInfoNotFoundException.class)
    public ResponseEntity<?> AddressInfoNotFound(Exception e){
        HttpStatus status =HttpStatus.NOT_FOUND;
        return  new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
    }
}
