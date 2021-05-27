package oi.github.daylanbueno.domain.controller.exception;

import oi.github.daylanbueno.domain.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRegraNegocioException(RegraNegocioException ex) {
        return new ApiError(ex.getMessage());
     }
}
