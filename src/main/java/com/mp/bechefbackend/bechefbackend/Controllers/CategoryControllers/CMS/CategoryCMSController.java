package com.mp.bechefbackend.bechefbackend.Controllers.CategoryControllers.CMS;

import com.mp.bechefbackend.bechefbackend.Models.InfoMessage;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public String addCategory(Model model, @RequestParam String newCategory, @RequestParam String oldCategory, @RequestParam String method){
        if (method.equals("POST")){
            try {
                categoryService.save(newCategory);
                model.addAttribute("msgError", new InfoMessage("➕¡La categoria se agregó correctamente!", 0));
            } catch (Exception e) {
                model.addAttribute("msgError", new InfoMessage("❌¡Ya existe una categoria o no se pudo agregar correctamente!", 0));
            }
        }
        else if (method.equals("PUT")){
            try {
                categoryService.edit(newCategory, oldCategory);
                model.addAttribute("msgError", new InfoMessage("➕¡La categoria se modificó correctamente!", 0));
            } catch (Exception e) {
                model.addAttribute("msgError", new InfoMessage("❌¡No se pudo modificar correctamente!", 0));
            }
        }
        else if (method.equals("DELETE")){
            try {
                categoryService.delete(newCategory);
                model.addAttribute("msgError", new InfoMessage("➕¡La categoria se eliminó correctamente!", 0));
            } catch (Exception e) {
                model.addAttribute("msgError", new InfoMessage("❌¡No se pudo eliminar correctamente!", 0));
            }
        }

        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

}
