package me.dri.entities;


import java.util.Date;

public class Sell {


    private String id;
    private Date date;
    private Double value;
    private Seller seller;

    public Sell() {

    }

    public Sell(String id, Date date, Double value, Seller seller) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
