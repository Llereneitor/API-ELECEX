package com.sal.elecex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sal.elecex.model.login.LoginDto;
import com.sal.elecex.service.LoginService;

@RestController
@RequestMapping("/elecex")
public class ElecexLoginController {
    
    @Autowired
    private LoginService loginService;

    @GetMapping("request_user")
    public ResponseEntity<List<LoginDto>> findUsers(String user) {
        
        return new ResponseEntity<>(this.loginService.findUsers(user), HttpStatus.OK);
    }

    @GetMapping("is_valid_user")
    public ResponseEntity<Boolean> isValidUser(@RequestParam(required = true) String user,
                                                @RequestParam(required = true) String password) {

        boolean isRegistered = loginService.isRegisteredUser(user, password);
        if (isRegistered) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/insert_user")
    public ResponseEntity<String> insertUser(@RequestBody LoginDto loginDto) {

        String isRegistered = loginService.insertUser(loginDto);
        return new ResponseEntity<>(isRegistered, HttpStatus.OK);
    }

    @PostMapping("/block_user")
    public ResponseEntity<String> blockUser(@RequestBody LoginDto loginDto) {
            return new ResponseEntity<>(this.loginService.blockUsers(loginDto), HttpStatus.OK);
    }

    @PostMapping("/unlock_user")
    public ResponseEntity<String> unlockUser(@RequestBody LoginDto loginDto) {
            return new ResponseEntity<>(this.loginService.unlockUsers(loginDto), HttpStatus.OK);
    }

    @PostMapping("/{userId}/change-password")
    public ResponseEntity<String> changePassword(PathVariable user, @RequestBody LoginDto loginDto) {
        return null;
    }


}
