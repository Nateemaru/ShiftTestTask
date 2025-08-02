package com.nateemaru.extensions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StringExtensions {

    public static boolean isStringValid(String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean isValidPath(String pathString) {
        try {
            Path path = Paths.get(pathString);
            return Files.exists(path);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPathExist(String pathString) {
        File dir = new File(pathString);
        return dir.exists() && dir.isDirectory();
    }
}
