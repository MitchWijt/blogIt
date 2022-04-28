package com.example.blogit.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRespository userRespository;

    @Autowired
    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public UserDto getUserData(String slug) {
        Users user = userRespository.findBySlug(slug);
        return UserMapper.INSTANCE.userToUserDto(user);
    }



}
