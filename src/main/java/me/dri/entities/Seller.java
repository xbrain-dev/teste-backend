package me.dri.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "sellers")
public class Seller {

    @Id
    private String id;
    private String name;

    private  List<Sell> sells;

    public Seller() {

    }


    public Seller(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public List<Sell> getSells() {
        return sells;
    }

    public void setSells(List<Sell> sells) {
        this.sells = sells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id) && Objects.equals(name, seller.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
