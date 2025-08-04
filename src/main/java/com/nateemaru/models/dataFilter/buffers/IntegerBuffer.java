package com.nateemaru.models.dataFilter.buffers;

import java.util.regex.Pattern;

public class IntegerBuffer extends BaseBuffer<Long> {

    private final String integersName = "Integers.txt";
    private final Pattern integerPattern = Pattern.compile("^-?\\d+$");

    @Override
    public boolean tryAddLine(String line) {
        if (integerPattern.matcher(line).matches()) {
            try {
                buffer.add(Long.valueOf(line));
                return true;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid format: %s%n", line, e.getMessage());
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
            System.out.println("\nMinInteger: " + buffer.stream().min(Long::compareTo).get());
            System.out.println("MaxInteger: " + buffer.stream().max(Long::compareTo).get());
            System.out.println("SumIntegers: " + buffer.stream().reduce(0L, Long::sum));
            System.out.println("AverageInteger: " + (double) buffer.stream().reduce(0L, Long::sum) / buffer.size());
        }
    }

    @Override
    public String getName() {
        return integersName;
    }
}
