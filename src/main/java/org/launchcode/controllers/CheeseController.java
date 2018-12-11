package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

// PART TWO
    @Autowired
    private CategoryDao categoryDao;  // to access categories in template
//

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
    // PART TWO
        model.addAttribute("categories", categoryDao.findAll()); // to view categories in dropdown
    //
        model.addAttribute(new Cheese());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(
            @ModelAttribute @Valid Cheese newCheese,
            Errors errors,
            @RequestParam int categoryId,
            Model model
    ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            // PART TWO
            model.addAttribute("categories", categoryDao.findAll()); // to view categories in dropdown
            //
            return "cheese/add";
        }

        newCheese.setCategory(categoryId, categoryDao);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category/{categoryId}")
    public String categoryCheeses(
            Model templateVariables,
            @PathVariable(value = "categoryId") int categoryId
    ) {
        Category category = categoryDao.findOne(categoryId);
        List<Cheese> cheeses = category.getCheeses();
        templateVariables.addAttribute("title", "Category: " + category.getName() + " cheeses");
        templateVariables.addAttribute("cheeses", cheeses);
        return "cheese/index";
    }

}
