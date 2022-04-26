package com.example.blogit.blog;


import com.example.blogit.user.UserDto;
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

    private UUID uuid;

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
    private String bannerImg;

    @NotNull
    private LocalDateTime publishDate;

    public Blog() {
        this.uuid = UUID.randomUUID();
    }

    public Blog(Long authorId, String title, String content, String bannerImg, LocalDateTime publishDate) {
        this.uuid = UUID.randomUUID();
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.bannerImg = bannerImg;
        this.publishDate = publishDate;
    }

    public Blog(UUID uuid, Long authorId, String title, String content, String bannerImg, LocalDateTime publishDate) {
        new Blog(authorId, title, content, bannerImg, publishDate);
        this.uuid = uuid;
    }

    public Blog(Users author, Long authorId, String title, String content, String bannerImg, LocalDateTime publishDate) {
        new Blog(authorId, title, content, bannerImg, publishDate);
        this.author = author;
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
        if(author == null) return null;
        return new UserDto(
                author.getId(),
                author.getUuid(),
                author.getEmail(),
                author.getUsername(),
                author.getProfilePicture()
        );
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
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
}
