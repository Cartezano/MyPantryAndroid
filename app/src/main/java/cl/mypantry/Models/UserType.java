package cl.mypantry.Models;

public class UserType {

    private int id;
    private String name;

    public UserType(){
        this(null, 0);
    }

    public UserType(String name){
        this(name, 0);
    }

    public UserType(String name, int id){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
