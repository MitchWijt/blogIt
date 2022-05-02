package com.example.blogit.blog;

import com.example.blogit.topic.Topic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class BlogTopicLinktable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotNull
    private Long blog_id;

    @NotNull
    private Long topic_id;

    @ManyToOne
    @JoinColumn(name = "blog_id", insertable = false, updatable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    private Topic topic;

    public Topic getTopic() {
        return topic;
    }

    public Blog getBlog() {
        return blog;
    }

    public BlogTopicLinktable() {

    }

    public BlogTopicLinktable(Long blogId, Long topicId) {
        this.blog_id = blogId;
        this.topic_id = topicId;
    }

    public Long getId() {
        return id;
    }

    public Long getBlogId() {
        return blog_id;
    }

    public Long getTopicId() {
        return topic_id;
    }
}
