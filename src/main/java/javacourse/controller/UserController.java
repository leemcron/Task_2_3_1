package javacourse.controller;

import javacourse.model.User;
import javacourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        // model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());
        return "users-list";
    }


    @GetMapping(value = "/user-create")
    public String createUseForm(User user) {
        return "user-create";
    }

    @PostMapping(value = "/user-create")
    public String addUser(/*@ModelAttribute("user")*/ User user) {
        userService.addUser(user);
       /* if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }*/
        return "redirect:/users";
    }

    @GetMapping(value = "user-delete/{id}")
    public String deleteUSer(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


    @GetMapping(value = "/user-update/{id}")
    public String updateUserForm(@PathVariable("id")int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping(value = "/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }


    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "index";
    }
}
