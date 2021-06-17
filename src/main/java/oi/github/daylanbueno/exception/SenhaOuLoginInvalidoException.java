package oi.github.daylanbueno.exception;

public class SenhaOuLoginInvalidoException extends RuntimeException {
    public SenhaOuLoginInvalidoException() {
        super("A senha ou login informado não é  inválido");
    }
}
