package com.mp.bechefbackend.bechefbackend.Controllers.CategoryControllers.CMS;

import com.mp.bechefbackend.bechefbackend.Models.InfoMessage;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryCMSController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping()
    public String findCategories(Model model){
        model.addAttribute("msgError", new InfoMessage("", 1));
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

}
