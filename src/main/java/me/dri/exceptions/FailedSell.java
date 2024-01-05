package me.dri.exceptions;

public class FailedSell extends RuntimeException {
    public FailedSell(String s) {
        super(s);
    }
}
