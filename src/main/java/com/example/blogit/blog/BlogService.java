package com.example.blogit.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    public final BlogRepository blogRepository;
    private final int blogPageSize = 20;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void createBlog(Blog blog) throws Exception {
        String title = blog.getTitle();
        String content = blog.getContent();
        String bannerImg = blog.getBannerImg();

        if(title.equals("") || content.equals("") || bannerImg.equals("")) {
            throw new Exception("Missing values for blog");
        }

        if(blogWithTitleExists(title)) {
            throw new Exception("Blog already exists");
        }

        blogRepository.save(blog);
    }

    public boolean blogWithTitleExists(String title) {
        Optional<Blog> blog = blogRepository.findByTitle(title);
        return blog.isPresent();
    }

    public BlogListDto getBlogs(int page) {
        Page<Blog> blogPage = blogRepository.findAll(PageRequest.of(page, blogPageSize));
        BlogListDto blogListDto = new BlogListDto(blogPage.getContent());
        return blogListDto;
    }
}
