package cl.mypantry.Models;

public class PantryProduct {
    private String name;
    private String brand;
    private int quality;
    private String expiration_date;

    public PantryProduct() {
        this(null, null, 0, "0000-00-00");
    }

    public PantryProduct(String name) {
        this(name, null, 0, "0000-00-00");
    }

    public PantryProduct(String name, String brand) {
        this(name, brand, 0, "0000-00-00");
    }

    public PantryProduct(String name, String brand, int quality) {
        this(name, brand, quality, "0000-00-00");
    }

    public PantryProduct(String name, String brand, int quality, String expiration_date) {
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

    public String getExpirationDate() {
        return expiration_date;
    }

    public void setExpirationDate(String expiration_date) {
        this.expiration_date = expiration_date;
    }
}
