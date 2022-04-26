package com.example.blogit.blog;

import java.util.List;

public class BlogListDto {
    private List<Blog> blogs;
    private boolean isLastPage;
    private int totalPages;
    private int currentPage;

    public BlogListDto(List<Blog> blogs, boolean isLastPage, int totalPages, int currentPage) {
        this.blogs = blogs;
        this.isLastPage = isLastPage;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
