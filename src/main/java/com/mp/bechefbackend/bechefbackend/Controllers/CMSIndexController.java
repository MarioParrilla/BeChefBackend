package com.mp.bechefbackend.bechefbackend.Controllers;

import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.CategoryServiceImpl;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.RecipeServiceImpl;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin()
public class CMSIndexController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RecipeServiceImpl recipeService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("usersAmount", userService.countUsers());
        model.addAttribute("recipesAmount", recipeService.countRecipes());
        model.addAttribute("CategoriesAmount", categoryService.countCategories());

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String resolveLogin(){
        return "redirect:/";
    }

}
