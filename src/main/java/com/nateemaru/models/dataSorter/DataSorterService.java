package com.nateemaru.models.dataSorter;

import com.nateemaru.extensions.StringExtensions;
import com.nateemaru.keys.FileNameKeys;
import com.nateemaru.models.inputOutput.ReadService;
import com.nateemaru.models.inputOutput.WriteService;
import com.nateemaru.models.parser.ParseData;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataSorterService {
    private final ReadService reader;
    private final WriteService writer;

    private List<Float> floats = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    public DataSorterService(ReadService readService, WriteService writeService) {
        this.reader = readService;
        this.writer = writeService;
    }

    public void sortAndWrite(ParseData data) {

        List<String> readData = reader.read(data.getFiles());

        clear();
        sortByType(readData);
        write(data.getPrefix(), data.getPath(), data.isRewrite());


        if (data.isSimpleStatistic() && data.isFullStatistic()) {
            System.out.println("\nDon't use the -s and -f functions at the same time.");
            System.out.println("Otherwise only -f will be called as a priority.");
        }

        if (data.isSimpleStatistic() && !data.isFullStatistic()) {
            showSimpleStatistic();

        } else if (data.isFullStatistic()) {
            showSimpleStatistic();
            showFullStatistic();
        }
    }

    private void write(String prefix, String path, boolean rewrite) {

        String finalFloatsName = FileNameKeys.FloatsName;
        String finalIntegersName = FileNameKeys.IntegersName;
        String finalStringsName = FileNameKeys.StringsName;

        if (StringExtensions.isStringValid(prefix)) {
            finalFloatsName = prefix + finalFloatsName;
            finalIntegersName = prefix + finalIntegersName;
            finalStringsName = prefix + finalStringsName;
        }

        if (StringExtensions.isStringValid(path)) {
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }
            if (StringExtensions.isValidPath(path)) {

                if (StringExtensions.isPathExist(path)) {
                    finalFloatsName = path + finalFloatsName;
                    finalIntegersName = path + finalIntegersName;
                    finalStringsName = path + finalStringsName;
                }
                else {
                    System.out.println("\nPath '" + path + "' does not exist or is not a directory. Using default path.");
                }
            }
            else {
                System.out.println("\nPath " + path + " is not a valid path. Using default path.");
            }
        }

        writer.writeFloats(floats, finalFloatsName, rewrite);
        writer.writeIntegers(integers, finalIntegersName, rewrite);
        writer.writeStrings(strings, finalStringsName, rewrite);
    }

    private void sortByType(List<String> readData) {
        Pattern longPattern = Pattern.compile("^-?\\d+$");
        Pattern floatPattern = Pattern.compile("^-?\\d+\\.\\d+(?:[eE][-+]?\\d+)?$");

        for (String item : readData) {
            if (longPattern.matcher(item).matches()) {
                try {
                    integers.add(Integer.valueOf(item));
                } catch (NumberFormatException e) {
                    int maxLength = item.startsWith("-") ? 11 : 10;
                    if (item.length() > maxLength) {
                        System.out.printf("Number '%s' too big and was skipped%n", item);
                    } else {
                        System.out.printf("Invalid format: %s%n", item, e.getMessage());
                    }
                }
            } else if (floatPattern.matcher(item).matches()) {
                try {
                    floats.add(Float.valueOf(item));
                } catch (NumberFormatException e) {
                    System.out.printf("Number processing error '%s' for type Float: %s%n", item, e.getMessage());
                    if (item.toLowerCase().contains("e") &&
                            (item.length() > 15 || Math.abs(Double.parseDouble(item)) > Float.MAX_VALUE)) {
                        System.out.println("  Note: the number is too large to be accurately represented in Float");
                    }
                }
            } else {
                strings.add(item);
            }
        }
    }

    private void clear() {
        floats.clear();
        integers.clear();
        strings.clear();
    }

    private void showSimpleStatistic() {
        System.out.println("\nStatistics:");
        System.out.println("Integers: " + integers.size());
        System.out.println("Floats: " + floats.size());
        System.out.println("Strings: " + strings.size());
    }

    private void showFullStatistic() {
        if (!integers.isEmpty()) {
            System.out.println("\nMinInteger: " + integers.stream().min(Integer::compareTo).get());
            System.out.println("MaxInteger: " + integers.stream().max(Integer::compareTo).get());
            System.out.println("SumIntegers: " + integers.stream().reduce(0, Integer::sum));
            System.out.println("AverageInteger: " + (double) integers.stream().reduce(0, Integer::sum) / integers.size());
        }

        if (!floats.isEmpty()) {
            System.out.println("\nMinFloat: " + floats.stream().min(Float::compareTo).get());
            System.out.println("MaxFloat: " + floats.stream().max(Float::compareTo).get());

            float sum = floats.stream().reduce(0F, Float::sum);
            System.out.println("SumFloats: " + (Float.isInfinite(sum) ? "Too large" : sum));
            System.out.println("AverageFloat: " + sum / floats.size());
        }

        if (!strings.isEmpty()) {
            System.out.println("\nMinString: " + strings.stream().min(Comparator.comparingInt(String::length)).get());
            System.out.println("MaxString: " + strings.stream().max(Comparator.comparingInt(String::length)).get());
            System.out.println();
        }
    }
}
