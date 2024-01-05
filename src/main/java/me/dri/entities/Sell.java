package me.dri.entities;


import org.springframework.data.annotation.Id;

import java.util.Date;

public class Sell {


    @Id
    private String id;
    private Date date;
    private Double value;

    public Sell() {

    }

    public Sell(String id, Date date, Double value) {
        this.id = id;
        this.date = date;
        this.value = value;
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
}
