package com.mp.bechefbackend.bechefbackend.Controllers.RecipeControllers.API;

import com.mp.bechefbackend.bechefbackend.Exceptions.ApiErrorMessage;
import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.RecipeServiceImpl;
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
    @Autowired
    RecipeServiceImpl recipeService;

    @PutMapping( value = "/recipes",produces = "application/json")
    public ResponseEntity<RecipeDTO> changeBasicData(@RequestParam(name = "img", required = false) MultipartFile file, @RequestParam(name = "token") String token, @RequestParam(name = "id", required = false) String id, @RequestParam(name = "name") String name,
                                                   @RequestParam(name = "description") String description, @RequestParam(name = "steps") String steps, @RequestParam(name = "category") String category){
        String result = null;
        boolean changed = false;
        RecipeDTO recipe = new RecipeDTO();

        Long idAutor = userService.findUserByToken(token).getId();

        if (file != null ) result = recipeService.changeImgProfile(file);
        else {
            RecipeDTO url = recipeService.findById(Long.parseLong(id));
            if (url != null) {
                recipe.setUrlImg(url.getUrlImg());
            } else {
                recipe.setUrlImg("");
            }
        }
        if(id != null)recipe.setId(Long.parseLong(id));
        recipe.setId_autor(idAutor);
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setSteps(steps);
        recipe.setCategory(category);
        if (result!=null) recipe.setUrlImg(result);
        changed = recipeService.changeInfoRecipe(recipe);

        if (file != null)  return changed != false && result != null ? new ResponseEntity(recipe, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("Error al modificar la receta"), HttpStatus.FORBIDDEN);
        return changed != false ? new ResponseEntity(recipe, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("Error al modificar la receta"), HttpStatus.FORBIDDEN);
    }

    @DeleteMapping( value = "/recipes/{recipeID}",produces = "application/json")
     public ResponseEntity<Boolean> removeRecipe(@PathVariable Long recipeID){
        boolean changed = false;

        changed = recipeService.remove(recipeID);

        return changed != false ? new ResponseEntity(changed, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("Error al eliminar la receta"), HttpStatus.FORBIDDEN);
    }

}
