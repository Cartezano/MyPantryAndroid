package cl.mypantry.Models;

import java.util.Date;

public class Pantry {

    private int id;
    private int user_id;
    private int product_id;
    private Date expiration_date;
    private int quality;

    public Pantry() {

    }

    public Pantry(int id, int user_id, int product_id, Date expiration_date, int quality){
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.expiration_date = expiration_date;
        this.quality = quality;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUserId(){
        return user_id;
    }

    public void setUserId(int user_id){
        this.user_id = user_id;
    }

    public int getProductId(){
        return product_id;
    }

    public void setProductId(int product_id){
        this.product_id = product_id;
    }

    public Date getExpirationDate(){
        return expiration_date;
    }

    public void setExpirationDate(Date expiration_date){
        this.expiration_date = expiration_date;
    }

    public int getQuality(){
        return quality;
    }

    public void setQuality(int quality){
        this.quality = quality;
    }
}
