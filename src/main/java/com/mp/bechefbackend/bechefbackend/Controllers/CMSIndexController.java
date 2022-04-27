package com.mp.bechefbackend.bechefbackend.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin()
public class CMSIndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
