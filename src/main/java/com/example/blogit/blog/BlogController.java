package com.example.blogit.blog;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/blog")
public class BlogController {

    public final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity createBlog(@RequestBody BlogDto blogDto) {
        Blog blog = BlogMapper.INSTANCE.blogDtoToBlog(blogDto);
        Blog createdBlogEntity = blogService.createBlog(blog);

        return ResponseEntity.ok(Collections.singletonMap("uuid", createdBlogEntity.getUuid()));
    }

    @GetMapping("/get-blogs")
    public Page<BlogDto> getBlogs(@PathParam("page") int page) {
        Page<Blog> blogPage = blogService.getBlogs(page);
        return blogPage.map(BlogMapper.INSTANCE::blogToBlogDto);
    }

    @GetMapping("/get-blogs/{authorId}")
    public Page<BlogDto> getBlogsFromAuthor(@PathVariable Long authorId, @PathParam("page") int page) {
        Page<Blog> blogEntityPage = blogService.getBlogs(page, authorId);
        return blogEntityPage.map(BlogMapper.INSTANCE::blogToBlogDto);
    }

    @GetMapping("/{uuid}")
    public BlogDto getSingleBlog(@PathVariable UUID uuid) {
        Blog blog = blogService.getBlog(uuid);
        return BlogMapper.INSTANCE.blogToBlogDto(blog);
    }

    @GetMapping("/search-banner-img")
    public ResponseEntity getBannerImgUrl(@PathParam("query") String query) {
        String url = blogService.getBannerImgUrl(query);
        return ResponseEntity.ok(Collections.singletonMap("url", url));
    }

    @GetMapping("/topic-blogs/{topicSlug}")
    public List<BlogDto> getBlogsFromTopic(@PathVariable String topicSlug) {
         List<Blog> blogEntityList = blogService.getBlogs(topicSlug);
         return blogEntityList.stream().map(BlogMapper.INSTANCE::blogToBlogDto).collect(Collectors.toList());
    }

    @PutMapping("/upload-banner-image/{blogUUID}")
    public ResponseEntity uploadBannerImage(@PathVariable String blogUUID, @RequestParam("banner") MultipartFile file) throws Exception {
        String fileUrl = blogService.uploadBannerImage(blogUUID, file);
        return ResponseEntity.ok(Collections.singletonMap("url", fileUrl));
    }
}
