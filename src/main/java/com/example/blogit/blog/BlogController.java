package com.example.blogit.blog;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/blog")
public class BlogController {

    public final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public Blog createBlog(@Valid @RequestBody Blog blog) throws Exception {
        return blogService.createBlog(blog);
    }

    @GetMapping("/get-blogs")
    public BlogListDto getBlogs(@PathParam("page") int page) {
        return blogService.getBlogs(page);
    }
}
