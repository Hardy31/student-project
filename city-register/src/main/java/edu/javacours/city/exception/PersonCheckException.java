package edu.javacours.city.exception;

public class PersonCheckException extends  Exception{
    public PersonCheckException() {
    }

    public PersonCheckException(String s) {
        super(s);
    }

    public PersonCheckException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PersonCheckException(Throwable throwable) {
        super(throwable);
    }

    public PersonCheckException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
