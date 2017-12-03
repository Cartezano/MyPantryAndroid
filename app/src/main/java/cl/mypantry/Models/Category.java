package cl.mypantry.Models;

public class Category {

    private int id;
    private String name;

    public Category() {
        this(null, 0);
    }

    public Category(String name){
        this(null, 0);
    }

    public Category(String name, int id){
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
