package com.example.blogit.blog;

import com.example.blogit.user.UserDto;

import java.util.UUID;

public class BlogDto {
    private UUID uuid;
    private String title;
    private String content;
    private String banner;
    private UserDto author;
    private String slogan;
    private int likes;

    public BlogDto() {
        this.uuid = UUID.randomUUID();
        this.likes = 0;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getBanner() {
        return banner;
    }

    public UserDto getAuthor() {
        return author;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getLikes() {
        return likes;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
