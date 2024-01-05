package me.dri.exceptions;

public class NotFoundSellerByName extends RuntimeException {
    public NotFoundSellerByName(String msg) {
        super(msg);
    }
}
