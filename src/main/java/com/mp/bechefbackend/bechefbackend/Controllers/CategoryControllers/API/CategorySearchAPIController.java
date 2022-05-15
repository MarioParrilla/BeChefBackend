package com.mp.bechefbackend.bechefbackend.Controllers.CategoryControllers.API;

import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/api")
public class CategorySearchAPIController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping( value = "/categories",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findUsers(){
        return new ResponseEntity(categoryService.findAll(), HttpStatus.OK);
    }
}