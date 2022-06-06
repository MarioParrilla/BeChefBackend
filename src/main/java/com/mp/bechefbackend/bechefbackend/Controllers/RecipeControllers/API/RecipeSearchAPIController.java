package com.mp.bechefbackend.bechefbackend.Controllers.RecipeControllers.API;

import com.mp.bechefbackend.bechefbackend.Exceptions.ApiErrorMessage;
import com.mp.bechefbackend.bechefbackend.Models.RateDTO;
import com.mp.bechefbackend.bechefbackend.Models.RateInfo;
import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/api")
public class RecipeSearchAPIController {

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping( value = "/recipes",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipes(){
        return new ResponseEntity(recipeService.findAll(), HttpStatus.OK);
    }

    @GetMapping( value = "/recipes/q/",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByQueryNukk(){
        return new ResponseEntity(new ArrayList<RecipeDTO>() , HttpStatus.OK);
    }

    @GetMapping( value = "/recipes/q/{query}",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByQuery(@PathVariable(required = false) String query){
        return new ResponseEntity(recipeService.findByQuery(query), HttpStatus.OK);
    }

    @GetMapping( value = "/recipes/rate/{rate}",produces = "application/json")
    public ResponseEntity<Double> findRateFromRecipe(@PathVariable() Long rate){
        return new ResponseEntity(recipeService.findRate(rate), HttpStatus.OK);
    }

    @GetMapping( value = "/recipes/rateOf/{data}",produces = "application/json")
    public ResponseEntity<Double> findRateFromRecipe(@PathVariable() String data){
        String[] d = data.split("-");
        return new ResponseEntity(recipeService.findRateOfUser(Long.parseLong(d[0]), Long.parseLong(d[1])), HttpStatus.OK);
    }

    @PostMapping( value = "/recipes/rate",produces = "application/json")
    public ResponseEntity<Boolean> setRateToRecipe(@RequestBody() RateInfo rate){
        return new ResponseEntity(recipeService.setRate(rate), HttpStatus.OK);
    }

    @GetMapping( value = "/recipes/category/{category}",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByCategory(@PathVariable String category){
        List<RecipeDTO> recipes = recipeService.findRecipesByCategory(category);
        return recipes != null ? new ResponseEntity(recipes, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro recetas de la categoria: "+category), HttpStatus.NOT_FOUND);
    }

    @GetMapping( value = "/recipes/category/{category}/{lastID}",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByCategoryPaged(@PathVariable String category, @PathVariable Long lastID){
        List<RecipeDTO> recipes = recipeService.findRecipesByCategoryPaged(category, lastID);
        return recipes != null ? new ResponseEntity(recipes, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro recetas de la categoria: "+category), HttpStatus.NOT_FOUND);
    }

    @PostMapping( value = "/recipes/",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByToken(@RequestParam String token){
        List<RecipeDTO> recipes = recipeService.findRecipesByToken(token);
        return recipes != null ? new ResponseEntity(recipes, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro recetas con el token: "+token), HttpStatus.NOT_FOUND);
    }

    @GetMapping( value = "/recipes/user/{userID}",produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> findRecipesByUserID(@PathVariable Long userID){
        List<RecipeDTO> recipes = recipeService.findRecipesByUserID(userID);
        return recipes != null ? new ResponseEntity(recipes, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro recetas con el id: "+userID), HttpStatus.NOT_FOUND);
    }
}
