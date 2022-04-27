package com.mp.bechefbackend.bechefbackend.Controllers;

import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController()
@RequestMapping("/api")
public class APIIndexController {

    @GetMapping(produces = "application/json")
    public String index(){
        return new String("Hey!, Bienvenido a BeChef");
    }

}
