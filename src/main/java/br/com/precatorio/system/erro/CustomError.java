package br.com.precatorio.system.erro;

public class CustomError {
    String message;

    public CustomError(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
