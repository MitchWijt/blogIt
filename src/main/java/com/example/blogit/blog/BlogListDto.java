package com.example.blogit.blog;

import java.util.List;

public class BlogListDto {
    private List<Blog> blogs;
    private boolean isLastPage;

    public BlogListDto(List<Blog> blogs) {
        this.blogs = blogs;
        this.isLastPage = false;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }
}
