package com.example.blogit.user;

public class UserDto {
    private String email;
    private String username;
    private String slug;
    private String profilePicture;

    public UserDto(String email, String username, String slug, String profilePicture) {
        this.email = email;
        this.username = username;
        this.slug = slug;
        this.profilePicture = profilePicture;
    }

    public String getSlug() {
        return slug;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
