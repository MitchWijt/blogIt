package com.example.blogit.utils;

public class Utils {
    public static String generateSlugFromString(String string) {
        String slug = string.toLowerCase().trim().replaceAll("/ /g", "-");
        return slug;
    }
}
