package com.example.blogit.user;


import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUserData(String slug) {
        // we will need the slug when we are going to fetch from the DB.

        User user = new User("mitchel@wijt.net", "mitchelwijt", "test123", "https://image.good.com");
        return user;
    }

}
