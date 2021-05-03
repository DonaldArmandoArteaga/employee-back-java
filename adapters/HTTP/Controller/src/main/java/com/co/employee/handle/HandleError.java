package com.co.employee.handle;


import co.com.employee.exceptions.ExceptionList;
import co.com.employee.exceptions.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class HandleError {

    @ExceptionHandler({ExceptionList.class, NotFound.class,RuntimeException.class})
    public Mono<ResponseEntity<ErrorInformation>> requiredException(RuntimeException runtimeException) {
        return Mono.just(
                ResponseEntity
                        .badRequest()
                        .body(
                                ErrorInformation
                                        .builder()
                                        .code(HttpStatus.BAD_REQUEST.value())
                                        .message(runtimeException.getMessage())
                                        .type(HttpStatus.BAD_REQUEST)
                                        .build()
                        )
        );
    }

}
