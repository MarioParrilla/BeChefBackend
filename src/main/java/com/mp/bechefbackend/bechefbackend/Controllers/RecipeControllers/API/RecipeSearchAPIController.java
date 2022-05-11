package com.mp.bechefbackend.bechefbackend.Controllers.RecipeControllers.API;

import com.mp.bechefbackend.bechefbackend.Exceptions.ApiErrorMessage;
import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/api")
public class RecipeSearchAPIController {

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping( value = "/recipes",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findUsers(){
        return new ResponseEntity(recipeService.findAll(), HttpStatus.OK);
    }

    @GetMapping( value = "/recipes/category/{category}",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByCategory(@PathVariable String category){
        List<RecipeDTO> recipes = recipeService.findRecipesByCategory(category);
        return recipes != null ? new ResponseEntity(recipes, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro recetas de la categoria: "+category), HttpStatus.NOT_FOUND);
    }

    @GetMapping( value = "/recipes/{token}",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByToken(@PathVariable String token){
        List<RecipeDTO> recipes = recipeService.findRecipesByToken(token);
        return recipes != null ? new ResponseEntity(recipes, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro recetas con el token: "+token), HttpStatus.NOT_FOUND);
    }
}
