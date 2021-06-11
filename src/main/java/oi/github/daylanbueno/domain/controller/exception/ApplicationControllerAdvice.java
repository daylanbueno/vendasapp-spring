package oi.github.daylanbueno.domain.controller.exception;

import oi.github.daylanbueno.domain.exception.ObjetoNaoEncontradoExeption;
import oi.github.daylanbueno.domain.exception.RegraNegocioException;
import oi.github.daylanbueno.domain.exception.SenhaOuLoginInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRegraNegocioException(RegraNegocioException ex) {

        return new ApiError(ex.getMessage());
     }

     @ExceptionHandler(ObjetoNaoEncontradoExeption.class)
     @ResponseStatus(HttpStatus.NOT_FOUND)
     public ApiError handleObjetoNaoEncontradoException(ObjetoNaoEncontradoExeption ex) {
        return  new ApiError(ex.getMessage());
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
         List<String> errors = ex.getBindingResult()
                 .getAllErrors()
                 .stream().map(erro ->  erro.getDefaultMessage()
         ).collect(Collectors.toList());

         return new ApiError(errors);
     }

     @ExceptionHandler(SenhaOuLoginInvalidoException.class)
     @ResponseStatus(HttpStatus.UNAUTHORIZED)
     public ApiError handleSenhaInformadaInvalida(SenhaOuLoginInvalidoException ex) {
        return new ApiError(ex.getMessage());
     }


}
