package cl.mypantry.API.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cl.mypantry.Models.User;

public class UserModelResponse {
    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("data")
    @Expose
    User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
