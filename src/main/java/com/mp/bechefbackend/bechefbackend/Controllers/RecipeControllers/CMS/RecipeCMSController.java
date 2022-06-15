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
    public String findRecipes(Model model, @RequestParam(required = false) String done){
        if (done == null) model.addAttribute("msgError", new InfoMessage("", 1));
        else if (done.equals("1")) model.addAttribute("msgError", new InfoMessage("➖¡La receta se eliminó correctamente!", 0));
        else if (done.equals("-1")) model.addAttribute("msgError", new InfoMessage("❌¡No se pudo eliminar la receta!", 0));
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes";
    }

    @PostMapping("/delRecipe")
    public String delUser(Model model, @RequestParam(name = "id") Long id){
        String done = "-1";
        boolean result = recipeService.remove(id);
        if(result) done = "1";
        else done = "-1";

        model.addAttribute("recipes", recipeService.findAll());
        return "redirect:/recipes?done="+done;
    }

}
