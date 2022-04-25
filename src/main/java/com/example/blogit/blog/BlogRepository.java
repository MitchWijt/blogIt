package com.example.blogit.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Optional<Blog> findByTitle(String title);
    Page<Blog> findAll(Pageable pageable);
}
