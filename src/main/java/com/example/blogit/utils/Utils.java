package com.example.blogit.utils;

public class Utils {
    public static String generateSlugFromString(String string) {
        return string.toLowerCase().trim().replaceAll("/ /g", "-");
    }
}
