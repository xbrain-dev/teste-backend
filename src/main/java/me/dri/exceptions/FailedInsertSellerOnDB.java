package me.dri.exceptions;

public class FailedInsertSellerOnDB extends RuntimeException {
    public FailedInsertSellerOnDB(String s) {
        super(s);
    }
}
