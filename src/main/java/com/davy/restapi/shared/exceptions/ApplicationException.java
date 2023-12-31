package com.davy.restapi.shared.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException{

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;
    private final Set<String> fieldErrors;
}
