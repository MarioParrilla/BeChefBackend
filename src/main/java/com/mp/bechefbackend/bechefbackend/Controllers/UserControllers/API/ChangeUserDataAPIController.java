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
    public ResponseEntity<UserDTO> changeBasicData(@RequestParam(name = "img", required = false) MultipartFile file, @RequestParam(name = "token") String token, @RequestParam(name = "username") String username,
                                                   @RequestParam(name = "description") String description){
        String result = null;
        boolean changed = false;
        UserDTO user = new UserDTO();

        if (file != null ) result = userService.changeImgProfile(file);

        user.setToken(token);
        user.setUsername(username);
        user.setDescription(description);

        if (result!=null) user.setUrlImg(result);
        changed = userService.changeUsernameAndDescByToken(user);

       if (file != null)  return changed != false && result!=null ? new ResponseEntity(user, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("El nombre de usuario ya esta escogido, prueba con otro"), HttpStatus.FORBIDDEN);
        return changed != false ? new ResponseEntity(user, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("El nombre de usuario ya esta escogido, prueba con otro"), HttpStatus.FORBIDDEN);
    }
}
