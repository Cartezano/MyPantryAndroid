package cl.mypantry.Models;

import java.math.BigInteger;
import java.util.Date;

public class Product {

    private int id;
    private BigInteger code;
    private String name;
    private String brand;
    private Date dear_date;
    private int category_id;


    public Product() {

    }

    public Product(int id, BigInteger code, String name, String brand, Date dear_date, int category_id) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.dear_date = dear_date;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getCode() {
        return code;
    }

    public void setCode(BigInteger code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getDearDate() {
        return dear_date;
    }

    public void setDearDate(Date dear_date) {
        this.dear_date = dear_date;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }
}
