package cl.mypantry.API.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.mypantry.Models.Product;

public class ProductListResponse {
    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("data")
    @Expose
    List<Product> product;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
