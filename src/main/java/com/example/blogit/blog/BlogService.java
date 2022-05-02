package com.example.blogit.blog;

import com.example.blogit.lib.gcs.GCS;
import com.example.blogit.lib.unsplash.Unsplash;
import com.example.blogit.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogTopicLinktableRepository blogTopicLinktableRepository;
    private final Unsplash unsplash;
    private final GCS gcs;


    private final int BLOG_PAGE_SIZE = 20;

    public BlogService(BlogRepository blogRepository, Unsplash unsplash, BlogTopicLinktableRepository blogTopicLinktableRepository, GCS gcs) {
        this.blogRepository = blogRepository;
        this.unsplash = unsplash;
        this.blogTopicLinktableRepository = blogTopicLinktableRepository;
        this.gcs = gcs;
    }

    public String getBannerImgUrl(String query) {
        if(query == null) return unsplash.getRandomPhoto();
        return unsplash.getPhoto(query);
    }

    public Blog createBlog(Blog blog) throws Exception {
        String title = blog.getTitle();

        String url = unsplash.getPhoto(title);
        blog.setBannerImg(url);

        Blog createdBlog = blogRepository.save(blog);

        Long blogId = createdBlog.getId();
        Long topicId = createdBlog.getTopicId();

        BlogTopicLinktable blogTopicLinktableEntry = new BlogTopicLinktable(blogId, topicId);
        blogTopicLinktableRepository.save(blogTopicLinktableEntry);

        return createdBlog;
    }

    public BlogListDto mapBlogsToBlogListDto(Page<Blog> blogs) {
        return new BlogListDto(blogs.getContent(), blogs.isLast(), blogs.getTotalPages(), blogs.getNumber());
    }

    public BlogListDto getBlogs(int page) {
        Page<Blog> blogPage = blogRepository.findAll(PageRequest.of(page, BLOG_PAGE_SIZE));
        return mapBlogsToBlogListDto(blogPage);
    }

    public BlogListDto getBlogs(int page, Long authorId) {
        Page<Blog> blogPage = blogRepository.findAllByAuthorId(authorId, PageRequest.of(page, BLOG_PAGE_SIZE));
        return mapBlogsToBlogListDto(blogPage);
    }

    public List<Blog> getBlogs(String topicSlug) {
        List<Blog> blogList = new ArrayList<>();
        List<BlogTopicLinktable> blogTopicLinktableList = blogTopicLinktableRepository.findByTopic_Slug(topicSlug);

        for (BlogTopicLinktable blogTopicLinktable : blogTopicLinktableList) {
            Blog blog = blogTopicLinktable.getBlog();
            blogList.add(blog);
        }

        return blogList;
    }

    public Blog getBlog(String blogUUID) {
        UUID uuid = UUID.fromString(blogUUID);
        return blogRepository.findBlogByUuid(uuid);
    }

    public Blog uploadBannerImage(String blogUUID, MultipartFile file) throws Exception {
        if(file.isEmpty()) throw new Exception("No file was given");

        String filename = blogUUID + "." + Utils.getExtensionFromFilename(file.getOriginalFilename());
        String fileUrl = gcs.uploadFile("blogs/" + filename, file);

        Blog blog = blogRepository.findBlogByUuid(UUID.fromString(blogUUID));
        blog.setBannerImg(fileUrl);
        return blogRepository.save(blog);
    }
}
