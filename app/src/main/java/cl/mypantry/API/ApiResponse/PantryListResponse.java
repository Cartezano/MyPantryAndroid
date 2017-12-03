package cl.mypantry.API.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.mypantry.Models.Pantry;

public class PantryListResponse {
    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("data")
    @Expose
    List<Pantry> pantry;

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

    public List<Pantry> getPantry() {
        return pantry;
    }

    public void setPantry(List<Pantry> pantry) {
        this.pantry = pantry;
    }
}
