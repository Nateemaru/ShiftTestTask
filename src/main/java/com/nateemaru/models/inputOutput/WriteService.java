package com.nateemaru.models.inputOutput;

import com.nateemaru.extensions.StringExtensions;
import com.nateemaru.models.dataFilter.buffers.BaseBuffer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteService {

    public void write(BaseBuffer<?> buffer, String fileName, String prefix, String path, boolean overwrite) {

        if (buffer == null) {
            return;
        }

        List<String> outputBufferLines = buffer.asLines();
        if (outputBufferLines == null || outputBufferLines.isEmpty()) {
            return;
        }

        String name = prepareFileName(fileName, prefix, path);
        File file = prepareFile(name);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, overwrite))) {
            for (String line : outputBufferLines) {
                writer.write(line + System.lineSeparator());
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

    private String prepareFileName(String name, String prefix, String path) {
        String result = name;

        if (StringExtensions.isStringValid(prefix)) {
            result = prefix + result;
        }

        if (StringExtensions.isStringValid(path)) {
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }
            if (StringExtensions.isValidPath(path)) {

                if (StringExtensions.isPathExist(path)) {
                    result = path + result;
                }
                else {
                    System.out.println("\nPath '" + path + "' does not exist or is not a directory. Using default path.");
                }
            }
            else {
                System.out.println("\nPath " + path + " is not a valid path. Using default path.");
            }
        }

        return result;
    }
}
