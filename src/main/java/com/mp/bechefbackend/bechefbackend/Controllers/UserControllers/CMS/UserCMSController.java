package com.mp.bechefbackend.bechefbackend.Controllers.UserControllers.CMS;

import com.mp.bechefbackend.bechefbackend.Models.InfoMessage;
import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.AuthServiceImpl;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserCMSController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    AuthServiceImpl authService;

    @GetMapping()
    public String findUsers(Model model, @RequestParam(required = false) String done){
        if (done == null) model.addAttribute("msgError", new InfoMessage("", 1));
        else if (done.equals("1")) model.addAttribute("msgError", new InfoMessage("➕¡El usuario se agregó correctamente!", 0));
        else if (done.equals("-1")) model.addAttribute("msgError", new InfoMessage("❌¡Ya existe un usuario con datos del nuevo!", 0));
        else if (done.equals("2")) model.addAttribute("msgError", new InfoMessage("\uD83D\uDCDD¡El usuario se modificó correctamente!\"", 0));
        else if (done.equals("-2")) model.addAttribute("msgError", new InfoMessage("❌¡No se pudo modificar el usuario correctamente, algún dato ya existen en algún usuario!", 0));
        else if (done.equals("3")) model.addAttribute("msgError", new InfoMessage("➖¡El usuario se eliminó correctamente!", 0));
        else if (done.equals("-3")) model.addAttribute("msgError", new InfoMessage("❌¡No se pudo eliminar el usuario!", 0));
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/addUser")
    public String toAddUser(){
        return "addUsers";
    }

    @PostMapping("/addUser")
    public String addUser(Model model, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password
            , @RequestParam(name = "urlImg") String urlImg, @RequestParam(name = "description") String description,
                          @RequestParam(name = "token") String token, @RequestParam(name = "isAdmin") String isAdmin){
        String done = "-1";
        UserDTO newUser = new UserDTO();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setDescription(description);
        newUser.setUrlImg(urlImg);
        newUser.setToken(token);
        newUser.setAdmin(false);
        System.out.println(newUser);
        if (authService.register(newUser) != null) done = "1";
        else done = "-1";

        model.addAttribute("users", userService.findAll());
        return "redirect:/users?done="+done;
    }

    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("user", userService.findUserById(id));
        return "editUsers";
    }

    @PostMapping("/editUser")
    public String toEditUser(Model model, @RequestParam(name = "id") Long id, @RequestParam(name = "username") String username, @RequestParam(name = "email") String email,
                             @RequestParam(name = "isAdmin") boolean isAdmin, @RequestParam(name = "clearDesc") boolean clearDesc, @RequestParam(name = "clearImg") boolean clearImg){
        String done = "-2";
        UserDTO oldUser = userService.findUserById(id);

        oldUser.setUsername(username);
        oldUser.setEmail(email);
        oldUser.setAdmin(isAdmin);
        if (clearDesc) oldUser.setDescription("No Description");
        if (clearImg) oldUser.setUrlImg("");

        if (userService.save(oldUser)) done = "2";
        else done = "-2";

        model.addAttribute("users", userService.findAll());
        return "redirect:/users?done="+done;
    }

    @PostMapping("/delUser")
    public String delUser(Model model, @RequestParam(name = "id") Long id){
        String done = "-3";
        boolean result = userService.remove(id);
        if(result) done = "3";
        else done = "-3";

        model.addAttribute("users", userService.findAll());
        return "redirect:/users?done="+done;
    }
}
