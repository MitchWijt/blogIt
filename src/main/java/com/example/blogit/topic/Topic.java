package com.example.blogit.topic;

import com.example.blogit.utils.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Topic {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotBlank
    private String title;

    private String slug;

    @NotBlank
    private String description;

    private String status;

    public Topic() {
        this.status = TopicStatus.OPEN;
    }

    public Topic(String title, String description) {
        this.title = title;
        this.slug = Utils.generateSlugFromString(title);
        this.description = description;
        this.status = TopicStatus.OPEN;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        if(status == null) return TopicStatus.OPEN;
        return status;
    }

    public void setSlug() {
        if(title != null) this.slug = Utils.generateSlugFromString(title);
    }
}
