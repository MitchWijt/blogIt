package com.example.blogit.blog;

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
    private Long authorId;

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
