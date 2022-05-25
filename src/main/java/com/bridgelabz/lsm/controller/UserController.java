package com.bridgelabz.lsm.controller;


import com.bridgelabz.lsm.dto.LoginDTO;
import com.bridgelabz.lsm.dto.ResponseDTO;
import com.bridgelabz.lsm.dto.UserDTO;
import com.bridgelabz.lsm.model.User;
import com.bridgelabz.lsm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService service;


    @PostMapping({"/register"})
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO dto){
        String user = service.registerUser(dto);
        ResponseDTO response = new ResponseDTO("User Registered", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping({"/getAll"})
    public ResponseEntity<String> getUser(){
        List<User> user = service.getUser();
        ResponseDTO response = new ResponseDTO("Users", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO dto){
        ResponseDTO response = new ResponseDTO("User Login successefully: ", service.loginUser(dto));
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }
}
