package com.sal.elecex.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sal.elecex.Utils.UtilsService;
import com.sal.elecex.entity.UserEntity;
import com.sal.elecex.model.login.LoginDto;
import com.sal.elecex.repository.UserRepository;
import com.sal.elecex.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilsService utilsService;

    @Override
    public List<LoginDto> findUsers(String user) {

        List<UserEntity> userEntityList = null == user ? this.userRepository.findAll() : this.userRepository.findByUsername(user).map(Collections::singletonList).orElseGet(Collections::emptyList);

        return userEntityList.stream()
        .map(p -> modelMapper.map(p, LoginDto.class)) // Mapea cada UserEntity a LoginDto
        .collect(Collectors.toList());
    }

    public boolean isRegisteredUser(String user, String password) {
        UserEntity userEntity = this.userRepository.findByUsernameAndPassword(user, password).orElse(null);

        if (null != userEntity && false == userEntity.getIsUp()) {
            return false;
        }

        return null != userEntity;
    }

    public String insertUser(LoginDto loginDto) {

        UserEntity userEntity = modelMapper.map(loginDto, UserEntity.class);
        boolean response = this.userRepository.save(userEntity) != null;
        return this.utilsService.resultMethods(response);
    }

    @Override
    public String blockUsers(LoginDto loginDto) {
        
        boolean response = false;

        UserEntity userEntity = this.userRepository.findByUsername(loginDto.getUsername()).orElse(null);

        if (null != userEntity) {
            userEntity.setIsUp(Boolean.FALSE);
            response = this.userRepository.save(userEntity) != null;
        }
        return this.utilsService.resultMethods(response);
    }

    @Override
    public String unlockUsers(LoginDto loginDto) {
        
        boolean response = false;

        UserEntity userEntity = this.userRepository.findByUsername(loginDto.getUsername()).orElse(null);

        if (null != userEntity) {
            userEntity.setIsUp(Boolean.TRUE);
            response = this.userRepository.save(userEntity) != null;
        }
        return this.utilsService.resultMethods(response);
    }

    @Override
    public String changePassword(LoginDto loginDto) {
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    
}
