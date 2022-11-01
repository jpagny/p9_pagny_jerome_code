package com.mediscreen.app.exception;

import com.mediscreen.app.exception.code.BadRequestException;
import com.mediscreen.app.exception.code.ConflictException;
import com.mediscreen.app.exception.code.InternalServerErrorException;
import com.mediscreen.app.exception.code.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyErrorController {

    @ExceptionHandler(BadRequestException.class)
    public String badRequestException() {
        return "error/400.html";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundException() {
        return "error/404.html";
    }

    @ExceptionHandler(ConflictException.class)
    public String conflictException() {
        return "error/409.html";
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public String internalServerErrorException() {
        return "error/500.html";
    }


}
