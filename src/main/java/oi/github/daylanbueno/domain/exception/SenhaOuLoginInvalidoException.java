package oi.github.daylanbueno.domain.exception;

public class SenhaOuLoginInvalidoException extends RuntimeException {
    public SenhaOuLoginInvalidoException() {
        super("A senha ou login informado não é  inválido");
    }
}
