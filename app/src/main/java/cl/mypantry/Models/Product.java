package cl.mypantry.Models;

public class Product {

    private int id;
    private int code;
    private String name;
    private String brand;
    private int dear_date;


    public Product() {

    }

    public Product(int id, int code, String name, String brand, int dear_date) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.dear_date = dear_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
