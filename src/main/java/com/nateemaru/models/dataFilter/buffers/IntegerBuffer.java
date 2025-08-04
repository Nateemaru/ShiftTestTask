package com.nateemaru.models.dataFilter.buffers;

import java.util.regex.Pattern;

public class IntegerBuffer extends BaseBuffer<Integer> {

    private final String integersName = "Integers.txt";
    private final Pattern integerPattern = Pattern.compile("^-?\\d+$");

    @Override
    public boolean tryAddLine(String line) {
        if (integerPattern.matcher(line).matches()) {
            try {
                buffer.add(Integer.valueOf(line));
                return true;
            } catch (NumberFormatException e) {
                int maxLength = line.startsWith("-") ? 11 : 10;
                if (line.length() > maxLength) {
                    System.out.printf("Number '%s' too big and was skipped%n", line);
                } else {
                    System.out.printf("Invalid format: %s%n", line, e.getMessage());
                }
            }
        }

        return false;
    }

    @Override
    public void showSimpleStatistic () {
        System.out.println("Integers: " + buffer.size());
    }

    @Override
    public void showFullStatistic () {
        if (!buffer.isEmpty()) {
            System.out.println("\nMinInteger: " + buffer.stream().min(Integer::compareTo).get());
            System.out.println("MaxInteger: " + buffer.stream().max(Integer::compareTo).get());
            System.out.println("SumIntegers: " + buffer.stream().reduce(0, Integer::sum));
            System.out.println("AverageInteger: " + (double) buffer.stream().reduce(0, Integer::sum) / buffer.size());
        }
    }

    @Override
    public String getName() {
        return integersName;
    }
}
