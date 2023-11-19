package org.example.exception;

public class ProxyNotFoundException extends IllegalStateException {
    public ProxyNotFoundException() {
        super();
    }

    public ProxyNotFoundException(String s) {
        super(s);
    }
}
