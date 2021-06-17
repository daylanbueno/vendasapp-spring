package oi.github.daylanbueno.controller.exception;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ApiError {
    private List<String> erros;

    public ApiError(List<String> erros) {
        this.erros = erros;
    }
    public ApiError(String erro) {
        this.erros = Collections.singletonList(erro);
    }


}
