package com.sheryians.major.controller;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }
     @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user){
        System.out.print(user.toString());
        User newuser= new User();
        newuser.setFirstName(user.getFirstName());
        newuser.setLastName(user.getLastName());
        newuser.setEmail(user.getEmail());
        newuser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(newuser);
        return "redirect:/login";
    }
}
