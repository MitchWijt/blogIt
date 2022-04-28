package com.example.blogit.comment;

public class CommentUserDto {
    private String slug;
    private String profilePicture;
    private String username;

    public CommentUserDto(String slug, String profilePicture, String username) {
        this.slug = slug;
        this.profilePicture = profilePicture;
        this.username = username;
    }

    public String getSlug() {
        return slug;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getUsername() {
        return username;
    }
}
