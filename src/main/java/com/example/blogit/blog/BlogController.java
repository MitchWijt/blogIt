package com.example.blogit.blog;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/blog")
public class BlogController {

    public final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public BlogDto createBlog(@Valid @RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    @GetMapping("/get-blogs")
    public BlogListDto getBlogs(@PathParam("page") int page) {
        return blogService.getBlogs(page);
    }

    @GetMapping("/get-blogs/{authorId}")
    public BlogListDto getBlogsFromAuthor(@PathVariable Long authorId, @PathParam("page") int page) {
        return blogService.getBlogs(page, authorId);
    }

    @GetMapping("/{uuid}")
    public BlogDto getSingleBlog(@PathVariable String uuid) {
        return blogService.getBlog(uuid);
    }

    @GetMapping("/search-banner-img")
    public Map<String, String> getBannerImgUrl(@PathParam("query") String query) {
        String url = blogService.getBannerImgUrl(query);
        return Map.of("url", url);
    }

    @GetMapping("/topic-blogs/{topicSlug}")
    public List<Blog> getBlogsFromTopic(@PathVariable String topicSlug) {
        return blogService.getBlogs(topicSlug);
    }

    @PutMapping("/upload-banner-image/{blogUUID}")
    public Blog uploadBannerImage(@PathVariable String blogUUID, @RequestParam("banner") MultipartFile file) throws Exception {
        return blogService.uploadBannerImage(blogUUID, file);
    }
}
