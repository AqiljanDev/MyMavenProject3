package org.example.exeption;

public class DaoExeption extends RuntimeException{
    public DaoExeption() {
    }

    public DaoExeption(String message) {
        super(message);
    }

    public DaoExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoExeption(Throwable cause) {
        super(cause);
    }

    public DaoExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
