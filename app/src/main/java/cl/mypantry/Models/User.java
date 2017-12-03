package cl.mypantry.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private int user_type_id;

    public User() {
        this(null, null, null, null, 0, 0);
    }

    public User(String name) {
        this(name, null, null, null, 0, 0);
    }

    public User(String name, String last_name) {
        this(name, last_name, null, null, 0, 0);
    }

    public User(String name, String last_name, String email) {
        this(name, last_name, email, null, 0, 0);
    }

    public User(String name, String last_name, String email, String password) {
        this(name, last_name, email, password, 0, 0);
    }

    public User(String name, String last_name, String email, String password, int user_type_id) {
        this(name, last_name, email, password, user_type_id, 0);
    }

    public User(String name, String last_name, String email, String password, int user_type_id, int id) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.user_type_id = user_type_id;
        this.id = id;
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
