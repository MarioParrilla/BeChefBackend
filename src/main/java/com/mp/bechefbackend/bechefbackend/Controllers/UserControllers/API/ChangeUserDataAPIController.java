package com.mp.bechefbackend.bechefbackend.Controllers.UserControllers.API;

import com.mp.bechefbackend.bechefbackend.Exceptions.ApiErrorMessage;
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
public class ChangeUserDataAPIController {

    @Autowired
    UserServiceImpl userService;

    @PutMapping( value = "/users",produces = "application/json")
    public ResponseEntity<UserDTO> changeBasicData(@RequestBody UserDTO user){
        boolean changed = userService.changeUsernameAndDescByToken(user);
        return changed != false ? new ResponseEntity(user, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("El nombre de usuario ya esta escogido, prueba con otro"), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/uploadImgProfile")
    public ResponseEntity create(@RequestParam(name = "img") MultipartFile file) {
        String result = null;

        try {
            result = userService.changeImgProfile(file);
        } catch (Exception e) {
            //  throw internal error;
        }
        return result != null ? new ResponseEntity(result, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("La imagen de perfil no se pudo subir"), HttpStatus.FORBIDDEN);

    }

}
