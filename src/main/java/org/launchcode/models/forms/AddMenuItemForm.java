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

    @NotNull
    private int cheeseId; // adds one cheese at a time

    private Menu menu;
    private Iterable<Cheese> cheeses;

    public AddMenuItemForm() {}

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    /**
     * finds the menu by menuId ->
     * finds the cheese by cheeseId ->
     * adds it to the menus associated cheeses list ->
     * saves the updated menu to the db
     *
     * @param menuDao access to "menus" table in db
     * @param cheeseDao access to "cheeses" table in db
     */
    public void process(MenuDao menuDao, CheeseDao cheeseDao) {
        Menu menu = menuDao.findOne(this.menuId);
        Cheese cheeseToAdd = cheeseDao.findOne(this.cheeseId);

        menu.addItem(cheeseToAdd);
        menuDao.save(menu);
    }

    public Menu getMenu() { return menu; }
    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }
    public int getMenuId() {
        return menuId;
    }
    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }
    public void setMenuId(int menuId) { this.menuId = menuId; }
    public void setCheeseId(int cheeseId) { this.cheeseId = cheeseId; }
}
