package com.mp.bechefbackend.bechefbackend.Controllers.UserControllers.CMS;

import com.mp.bechefbackend.bechefbackend.Models.InfoMessage;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserCMSController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping()
    public String findUsers(Model model){
        model.addAttribute("msgError", new InfoMessage("", 1));
        model.addAttribute("users", userService.findAll());
        return "users";
    }

}
