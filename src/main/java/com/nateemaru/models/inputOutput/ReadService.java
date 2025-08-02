package com.nateemaru.models.inputOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadService {

    public List<String> read(List<File> files) {

        List<String> result = new ArrayList<String>();

        for (File file : files) {
            List<String> readResult = read(file);
            if (readResult != null && !readResult.isEmpty()) {
                result.addAll(read(file));
            }
        }

        return result;
    }

    public List<String> read(File file) {
        List<String> lines = new ArrayList<>();

        if (file.isFile() && file.canRead()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String s;
                while ((s = reader.readLine()) != null) {
                    s = s.trim();

                    if (!s.isEmpty()) {
                        lines.add(s);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return lines;
    }
}
