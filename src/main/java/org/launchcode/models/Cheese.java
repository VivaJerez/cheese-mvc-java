package org.launchcode.models;

import org.launchcode.models.data.CategoryDao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity
public class Cheese {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

// PART TWO
    @ManyToOne // each cheese (many cheeses) belongs to (one) category
    private Category category;
//

// PART THREE
    @ManyToMany(mappedBy = "cheeses") // known as "cheeses" field on Menu side of m2m
    private List<Menu> menus; // not used in app so no need to initialize
//

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() { return category; }

    public void setCategory(int categoryId, CategoryDao categoryDao) {
        this.category = categoryDao.findOne(categoryId);
    }
}
