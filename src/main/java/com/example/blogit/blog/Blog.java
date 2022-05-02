package com.example.blogit.blog;


import com.example.blogit.user.UserDto;
import com.example.blogit.user.UserMapper;
import com.example.blogit.user.Users;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity
public class Blog {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private UUID uuid;

    @NotNull
    private Long topicId;

    @NotNull
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Users author;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String slogan;

    private String bannerImg;

    private int likes;

    @NotNull
    private LocalDateTime publishDate;

    public Blog() {
        this.uuid = UUID.randomUUID();
        this.likes = 0;
    }

    public Blog(Long authorId, String title, String content, String bannerImg, Long topicId, LocalDateTime publishDate) {
        this.uuid = UUID.randomUUID();
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.bannerImg = bannerImg;
        this.topicId = topicId;
        this.publishDate = publishDate;
        this.likes = 0;
    }

    public Blog(UUID uuid, Long authorId, String title, String content, String bannerImg, Long topicId, LocalDateTime publishDate) {
        new Blog(authorId, title, content, bannerImg, topicId, publishDate);
        this.uuid = uuid;
        this.likes = 0;
    }

    public Blog(Users author, Long authorId, String title, String content, String bannerImg, Long topicId, LocalDateTime publishDate) {
        new Blog(authorId, title, content, bannerImg, topicId, publishDate);
        this.author = author;
        this.likes = 0;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public UserDto getAuthor() {
        return UserMapper.INSTANCE.userToUserDto(author);
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public int getLikes() {
        return likes;
    }

    public Long getId() {
        return id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getSlogan() {
        return slogan;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "uuid=" + uuid +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", bannerImg='" + bannerImg + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }

    public void setBannerImg(String url) {
        bannerImg = url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
}
