package com.example.blogit.blog;

import com.example.blogit.topic.Topic;
import com.example.blogit.user.Users;

import java.util.UUID;

public class BlogDto {
    private UUID uuid;
    private String title;
    private String content;
    private String banner;
    private Users author;
    private Topic topic;
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

    public Users getAuthor() {
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

    public void setAuthor(Users author) {
        this.author = author;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }
}
