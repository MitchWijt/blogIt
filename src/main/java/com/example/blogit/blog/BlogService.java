package com.example.blogit.blog;

import com.example.blogit.lib.unsplash.Unsplash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {

    public final BlogRepository blogRepository;
    public final BlogTopicLinktableRepository blogTopicLinktableRepository;
    public final Unsplash unsplash;

    private final int BLOG_PAGE_SIZE = 20;

    public BlogService(BlogRepository blogRepository, Unsplash unsplash, BlogTopicLinktableRepository blogTopicLinktableRepository) {
        this.blogRepository = blogRepository;
        this.unsplash = unsplash;
        this.blogTopicLinktableRepository = blogTopicLinktableRepository;
    }

    public String getBannerImgUrl(String query) {
        if(query == null) return unsplash.getRandomPhoto();
        return unsplash.getPhoto(query);
    }

    public Blog createBlog(Blog blog) throws Exception {
        String title = blog.getTitle();

        if(blogWithTitleExists(title)) {
            throw new Exception("Blog already exists");
        }

        String url = unsplash.getPhoto(title);
        blog.setBannerImg(url);

        return blogRepository.save(blog);
    }

    public boolean blogWithTitleExists(String title) {
        Optional<Blog> blog = blogRepository.findByTitle(title);
        return blog.isPresent();
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
}
