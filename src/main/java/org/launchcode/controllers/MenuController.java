package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "menu")
public class MenuController {
    // DAO, data access object(s): add any DAOs this controller needs access to
    @Autowired
    private MenuDao menuDao; // lets us perform CRUD operations on "menus" table

    @Autowired
    private CheeseDao cheeseDao; // lets us perform CRUD operations on "cheeses" table

    @RequestMapping(value = "")
    public String index(Model templateVariables) {
        templateVariables.addAttribute("title", "All Menus");
        templateVariables.addAttribute("menus", menuDao.findAll());

        return "menu/index";
    }

    @RequestMapping(value = "/view/{menuId}")
    public String viewMenu(
        Model templateVariables,
        @PathVariable(required = true) int menuId
    ) {
        Menu menu = menuDao.findOne(menuId);
        templateVariables.addAttribute("title", menu.getName());
        templateVariables.addAttribute("menu", menu);

        return "menu/view";
    }

    @RequestMapping(value = "add")
    public String addMenu(Model templateVariables) {
        templateVariables.addAttribute("title", "New Menu");
        templateVariables.addAttribute(new Menu());

        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addMenu(
        Model templateVariables,
        @ModelAttribute @Valid Menu newMenu,
        Errors errors
    ) {
        if (errors.hasErrors()) {
            templateVariables.addAttribute("title", "New Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);

        return "redirect:/menu/view/" + newMenu.getId();
    }

    @RequestMapping(value = "add-item/{menuId}")
    public String addItem(
        Model templateVariables,
        @PathVariable(required = true) int menuId
    ) {
        Menu menu = menuDao.findOne(menuId);

        templateVariables.addAttribute("form", new AddMenuItemForm(menu, cheeseDao.findAll()));
        templateVariables.addAttribute("title", "Add an item to "+ menu.getName());

        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(
        Model templateVariables,
        @ModelAttribute @Valid AddMenuItemForm form,
        Errors errors
    ) {
        if (errors.hasErrors()) {
            Menu menu = menuDao.findOne(form.getMenuId());

            templateVariables.addAttribute("title", "Add an item to "+ menu.getName());

            return "menu/add-item";
        }

        // processes the form: adds the cheese to the menu and saves to db
        form.process(menuDao, cheeseDao);

        return "redirect:/menu/view/" + form.getMenuId();
    }
}
