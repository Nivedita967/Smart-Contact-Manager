package com.scm.project.controllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.project.entities.User;
import com.scm.project.form.UserForm;
import com.scm.project.services.UserService;

import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("name","Aman");
        model.addAttribute("email","aman@gmail.com");
        System.out.println("home page handler");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("about page handler");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("service page loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm) {

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setEnabled(false);
        user.setProfilePic(
                "https://media.istockphoto.com/id/1300845620/vector/user-icon-flat-isolated-on-white-background-user-symbol-vector-illustration.jpg?s=612x612&w=0&k=20&c=yBeyba0hUkh14_jgv1OKqIH0CCSWU_4ckRkAoy2p73o=");

        userService.saveUser(user);

        System.out.println("user saved :");

        // redirect to login page
        return "redirect:/home";
    }
}
