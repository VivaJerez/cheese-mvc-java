package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AddMenuItemForm {
    @NotNull
    private int menuId;
    private Menu menu;

    @NotNull
    private int cheeseId; // adds one cheese at a time
    private Iterable<Cheese> cheeses; // all the cheeses for the menu

    public AddMenuItemForm() {}

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    /**
     * finds the cheese, adds it to the menu, and saves to the db
     * @param menuDao access to "menus" table in db
     * @param cheeseDao access to "cheeses" table in db
     */
//    public void process(MenuDao menuDao, CheeseDao cheeseDao) {
//        Menu menu = menuDao.findOne(this.menuId);
//        Cheese cheeseToAdd = cheeseDao.findOne(this.cheeseId);
//
//        menu.addItem(cheeseToAdd);
//
//        menuDao.save(menu);
//    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }
}
