package com.mp.bechefbackend.bechefbackend.Controllers.RecipeControllers.CMS;

import com.mp.bechefbackend.bechefbackend.Models.InfoMessage;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipes")
public class RecipeCMSController {

    @Autowired
    RecipeServiceImpl recipeService;

    @GetMapping()
    public String findRecipes(Model model){
        model.addAttribute("msgError", new InfoMessage("", 1));
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes";
    }

}
