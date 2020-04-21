package com.demo.SpringSecurity.controller;

import com.demo.SpringSecurity.entity.CRMUser;
import com.demo.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(){
        return "<h1> Welcome </h1>";
    }

    @GetMapping("/user")
    public String homeUser(){
        return "<h1> Welcome USER</h1>";
    }

    @GetMapping("/employee")
    public String getUser(){
        return "<h1> Welcome EMPLOYEE </h1>";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "<h1> Welcome ADMIN </h1>";
    }

    @GetMapping("/client")
    public String getClient(){
        return "<h1> Welcome CLIENT </h1>";
    }

    @PostMapping("/user")
    public ResponseEntity<CRMUser> saveUser(@RequestBody CRMUser crmUser){
        crmUser.setId(0);
        CRMUser userAdded = userService.save(crmUser);
        return new ResponseEntity<CRMUser>(userAdded, HttpStatus.OK);
    }
}
