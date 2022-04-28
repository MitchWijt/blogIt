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
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUuid(),
                user.getEmail(),
                user.getUsername(),
                user.getProfilePicture()
        );

        return userDto;
    }



}
