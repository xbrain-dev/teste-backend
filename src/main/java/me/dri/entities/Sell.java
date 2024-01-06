package me.dri.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.Objects;

public class Sell {


    private Date date;
    private Double value;

    @DBRef
    private Seller seller;

    public Sell() {

    }

    public Sell(Date date, Double value, Seller seller) {
        this.date = date;
        this.value = value;
        this.seller = seller;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sell sell = (Sell) o;
        return Objects.equals(date, sell.date) && Objects.equals(value, sell.value) && Objects.equals(seller, sell.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value, seller);
    }

    @Override
    public String toString() {
        return "Sell{" +
                "date=" + date +
                ", value=" + value;
    }
}
