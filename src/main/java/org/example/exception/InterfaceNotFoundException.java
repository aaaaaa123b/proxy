package org.example.exception;

public class InterfaceNotFoundException extends IllegalStateException {

    public InterfaceNotFoundException() {
        super();
    }

    public InterfaceNotFoundException(String s) {
        super(s);
    }
}
