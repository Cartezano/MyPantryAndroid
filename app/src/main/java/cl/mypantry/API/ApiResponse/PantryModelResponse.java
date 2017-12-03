package cl.mypantry.API.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cl.mypantry.Models.Pantry;

public class PantryModelResponse {
    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("data")
    @Expose
    Pantry pantry;

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

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        this.pantry = pantry;
    }
}
