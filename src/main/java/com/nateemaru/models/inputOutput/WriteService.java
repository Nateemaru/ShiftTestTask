package com.nateemaru.models.inputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteService {

    public void writeIntegers(List<Integer> list, String name, boolean rewrite) {
        if (list == null || list.isEmpty()) return;

        File file = prepareFile(name);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, rewrite))) {
            for (Integer num : list) {
                writer.write(num.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeFloats(List<Float> list, String name, boolean rewrite) {
        if (list == null || list.isEmpty()) return;

        File file = prepareFile(name);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, rewrite))) {
            for (Float num : list) {
                writer.write(num.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeStrings(List<String> list, String name, boolean rewrite) {
        if (list == null || list.isEmpty()) return;

        File file = prepareFile(name);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, rewrite))) {
            for (String str : list) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private File prepareFile(String fileName) {
        File file = new File(fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return file;
    }
}
