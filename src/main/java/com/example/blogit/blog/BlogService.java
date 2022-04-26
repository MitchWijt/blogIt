package com.example.blogit.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {

    public final BlogRepository blogRepository;
    private final int blogPageSize = 20;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(Blog blog) throws Exception {
        String title = blog.getTitle();

        if(blogWithTitleExists(title)) {
            throw new Exception("Blog already exists");
        }

        return blogRepository.save(blog);
    }

    public boolean blogWithTitleExists(String title) {
        Optional<Blog> blog = blogRepository.findByTitle(title);
        return blog.isPresent();
    }

    private BlogListDto mapBlogsToBlogListDto(Page<Blog> blogs) {
        return new BlogListDto(blogs.getContent(), blogs.isLast(), blogs.getTotalPages(), blogs.getNumber());
    }

    public BlogListDto getBlogs(int page) {
        Page<Blog> blogPage = blogRepository.findAll(PageRequest.of(page, blogPageSize));
        return mapBlogsToBlogListDto(blogPage);
    }

    public BlogListDto getBlogs(int page, Long authorId) {
        Page<Blog> blogPage = blogRepository.findAllByAuthorId(authorId, PageRequest.of(page, blogPageSize));
        return mapBlogsToBlogListDto(blogPage);
    }

    public Blog getBlog(String blogUUID) {
        UUID uuid = UUID.fromString(blogUUID);
        return blogRepository.findBlogByUuid(uuid);
    }
}
