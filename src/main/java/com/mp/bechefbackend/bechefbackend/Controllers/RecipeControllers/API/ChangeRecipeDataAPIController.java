package com.mp.bechefbackend.bechefbackend.Controllers.RecipeControllers.API;

import com.mp.bechefbackend.bechefbackend.Exceptions.ApiErrorMessage;
import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin()
@RestController()
@RequestMapping("/api")
public class ChangeRecipeDataAPIController {

    @Autowired
    UserServiceImpl userService;

    @PutMapping( value = "/recipes",produces = "application/json")
    public ResponseEntity<RecipeDTO> changeBasicData(@RequestParam(name = "img", required = false) MultipartFile file, @RequestParam(name = "token") String token, @RequestParam(name = "id") String id, @RequestParam(name = "name") String name,
                                                   @RequestParam(name = "description") String description, @RequestParam(name = "steps") String steps, @RequestParam(name = "category") String category){
        String result = null;
        boolean changed = false;
        RecipeDTO recipe = new RecipeDTO();
        //TODO: Tengo que buscar el id del usuario por su token para poner idAutor
        recipe.setId(Long.parseLong(id));
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setSteps(steps);
        recipe.setCategory(category);
        //if (result!=null) recipe.setUrlImg(result);
        System.out.println(recipe);


       return changed != false ? new ResponseEntity(recipe, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("Error al modificar la receta"), HttpStatus.FORBIDDEN);
    }
}
