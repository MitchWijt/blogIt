package com.example.blogit.lib.unsplash;

import java.util.List;

public class UnsplashApiResponse {
    private int total;
    private List<UnsplashResults> results;

    public UnsplashApiResponse() {}

    public int getTotal() {
        return total;
    }

    public List<UnsplashResults> getResults() {
        return results;
    }
}
