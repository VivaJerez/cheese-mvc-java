package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

// PART TWO
    @OneToMany // category (one) has (many) cheeses that belong to it
    @JoinColumn(name = "category_id") // foreign key held on cheeses table
    private List<Cheese> cheeses = new ArrayList<>();
//

    public Category() {}
    public Category(String name) { this.name = name; }

    public int getId() { return id; }

    public List<Cheese> getCheeses() { return cheeses; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
