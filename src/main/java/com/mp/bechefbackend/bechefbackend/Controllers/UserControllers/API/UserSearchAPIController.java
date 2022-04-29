package com.mp.bechefbackend.bechefbackend.Controllers.UserControllers.API;

import com.mp.bechefbackend.bechefbackend.Exceptions.ApiErrorMessage;
import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/api")
public class UserSearchAPIController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping( value = "/users",produces = "application/json")
    public ResponseEntity<List<UserDTO>> findUsers(){
        return new ResponseEntity(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping( value = "/users/{userID}",produces = "application/json")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long userID){
        UserDTO user = userService.findUserById(userID);
        return user != null ? new ResponseEntity(user, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro un usuario con el ID: "+userID), HttpStatus.NOT_FOUND);
    }

    @GetMapping( value = "/users/getUser/{token}",produces = "application/json")
    public ResponseEntity<UserDTO> getUserByToken(@PathVariable String token){
        UserDTO username = userService.findUserByToken(token);
        return username != null ? new ResponseEntity(username, HttpStatus.OK) : new ResponseEntity(new ApiErrorMessage("No se encontro un usuario con el token: "+token), HttpStatus.NOT_FOUND);
    }
}
