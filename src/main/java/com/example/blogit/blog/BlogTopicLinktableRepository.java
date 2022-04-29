package com.example.blogit.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTopicLinktableRepository extends JpaRepository<BlogTopicLinktable, Long> {
    List<BlogTopicLinktable> findByTopic_Slug(String slug);
}
