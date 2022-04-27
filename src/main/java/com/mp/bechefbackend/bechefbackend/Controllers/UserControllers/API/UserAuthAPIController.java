package com.mp.bechefbackend.bechefbackend.Controllers.UserControllers.API;

import com.mp.bechefbackend.bechefbackend.Exceptions.ApiErrorMessage;
import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/auth")
public class UserAuthAPIController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping( value = "/login",produces = "application/json")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO user){
        UserDTO fullUser = authService.checkData(user);
        return fullUser != null ? new ResponseEntity(fullUser, HttpStatus.FOUND) : new ResponseEntity(new ApiErrorMessage("El usuario no existe"), HttpStatus.NOT_FOUND);
    }

    @PostMapping( value = "/register",produces = "application/json")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user){
        UserDTO registereddUser = authService.register(user);
        return registereddUser != null ? new ResponseEntity(registereddUser, HttpStatus.CREATED) : new ResponseEntity(new ApiErrorMessage("El usuario no pudo ser registrado"), HttpStatus.FORBIDDEN);
    }

}
