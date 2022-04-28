package com.example.blogit.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBlogUUID(UUID blogUUID);
    Optional<Comment> findByUuid(UUID commentUUID);

}
