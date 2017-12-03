package cl.mypantry.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("last_name")
    private String last_name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("user_type_id")
    private int user_type_id;

    public User() {

    }

    public User(int id, String name, String last_name, String email, String password, int user_type_id) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.user_type_id = user_type_id;
    }

    public User(String name, String last_name, String email, String password, int user_type_id) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.user_type_id = user_type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return user_type_id;
    }

    public void setUserType(int user_type_id) {
        this.user_type_id = user_type_id;
    }
}
