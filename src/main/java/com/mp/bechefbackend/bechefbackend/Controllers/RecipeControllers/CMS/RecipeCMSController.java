package com.mp.bechefbackend.bechefbackend.Controllers.RecipeControllers.CMS;

import com.mp.bechefbackend.bechefbackend.Models.InfoMessage;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/delRecipe")
    public String delUser(Model model, @RequestParam(name = "id") Long id){
        boolean result = recipeService.remove(id);
        if(result) model.addAttribute("msgError", new InfoMessage("➖¡El usuario se eliminó correctamente!", 0));
        else model.addAttribute("msgError", new InfoMessage("❌¡No se pudo eliminar el usuario!", 0));

        model.addAttribute("users", recipeService.findAll());
        return "redirect:/recipes";
    }

}
