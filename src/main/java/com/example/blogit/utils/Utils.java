package com.example.blogit.utils;

public class Utils {
    public static String generateSlugFromString(String string) {
        return string.toLowerCase().trim().replaceAll("/ /g", "-");
    }

    public static String getExtensionFromFilename(String filename) {
        return filename.split("\\.")[1];
    }
}
