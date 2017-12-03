package cl.mypantry.Models;

import java.math.BigInteger;
import java.util.Date;

public class Product {

    private int id;
    private BigInteger code;
    private String name;
    private String brand;
    private int dear_date;
    private int category_id;


    public Product() {
        this(new BigInteger("0"), null, null, 0, 0 ,0);
    }

    public Product(BigInteger code) {
        this(code, null, null, 0, 0 ,0);
    }

    public Product(BigInteger code, String name) {
        this(code, name, null, 0, 0 ,0);
    }

    public Product(BigInteger code, String name, String brand) {
        this(code, name, brand, 0, 0 ,0);
    }

    public Product(BigInteger code, String name, String brand, int dear_date) {
        this(code, name, brand, dear_date, 0 ,0);
    }

    public Product(BigInteger code, String name, String brand, int dear_date, int category_id) {
        this(code, name, brand, dear_date, category_id ,0);
    }

    public Product(BigInteger code, String name, String brand, int dear_date, int category_id, int id) {
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

    public int getDearDate() {
        return dear_date;
    }

    public void setDearDate(int dear_date) {
        this.dear_date = dear_date;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }
}
