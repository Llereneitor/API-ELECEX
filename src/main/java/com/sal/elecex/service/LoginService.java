package com.sal.elecex.service;

import java.util.List;

import com.sal.elecex.model.login.LoginDto;

public interface LoginService {

    List<LoginDto> findUsers (String user);
    boolean isRegisteredUser(String user, String password);
    String insertUser(LoginDto loginDto);
    String blockUsers(LoginDto loginDto);
    String unlockUsers(LoginDto loginDto);
    String changePassword(LoginDto loginDto);
}
