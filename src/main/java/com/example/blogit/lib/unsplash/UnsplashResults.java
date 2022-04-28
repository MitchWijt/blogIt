package com.example.blogit.lib.unsplash;

public class UnsplashResults {
    private String id;
    private String width;
    private String height;
    private String description;
    private UnsplashImageUrls urls;

    public UnsplashResults() {}

    public String getId() {
        return id;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }

    public UnsplashImageUrls getUrls() {
        return urls;
    }
}
