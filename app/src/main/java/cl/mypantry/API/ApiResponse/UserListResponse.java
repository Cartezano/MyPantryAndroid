package cl.mypantry.API.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.mypantry.Models.User;

public class UserListResponse {
    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("data")
    @Expose
    List<User> user;

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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
