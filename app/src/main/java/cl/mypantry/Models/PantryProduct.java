package cl.mypantry.Models;

import java.util.Date;

public class PantryProduct {
    private String name;
    private String brand;
    private int quality;
    private Date expiration_date;

    public PantryProduct(String name, String brand, int quality, Date expiration_date) {
        this.name = name;
        this.brand = brand;
        this.quality = quality;
        this.expiration_date = expiration_date;
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

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Date getExpirationDate() {
        return expiration_date;
    }

    public void setExpirationDate(Date expiration_date) {
        this.expiration_date = expiration_date;
    }
}
