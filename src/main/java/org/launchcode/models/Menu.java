package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15) // varchar(SIZE)
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;


    public Menu() {}
    public Menu(String name) { this.name = name; }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Cheese> getCheeses() { return cheeses; }

    public void setName(String name) { this.name = name; }

    public void addItem(Cheese item) { cheeses.add(item); }
}












//@Entity
//public class Menu {
//    @Id
//    @GeneratedValue
//    private int id;
//
//    @NotNull
//    @Size(min = 5, max = 20)
//    private String name;
//
//    @ManyToMany // known as "menus" field on Cheese side of m2m
//    private List<Cheese> cheeses = new ArrayList<>(); // used in app, initialize as new empty ArrayList
//
//    public Menu() {}
//    public Menu(String name) { this.name = name; }
//
//    public int getId() { return id; }
//    public List<Cheese> getCheeses() { return cheeses; }
//    public String getName() { return name; }
//
//    public void setName(String name) { this.name = name; }
//
//    public void addItem(Cheese item) { cheeses.add(item); }
//}
