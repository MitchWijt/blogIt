package com.example.blogit.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("api/blog")
public class BlogController {

    public final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public Map<String, Boolean> createBlog(@RequestBody Blog blog) {
        try {
            blogService.createBlog(blog);

            return Map.of("success", true);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping("/get-blogs")
    public BlogListDto getBlogs(@PathParam("page") int page) {
        return blogService.getBlogs(page);
    }
}
