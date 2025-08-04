package com.nateemaru.models.dataFilter.buffers;

import java.util.regex.Pattern;

public class FloatBuffer extends BaseBuffer<Float> {

    private final String floatsName = "Floats.txt";
    private final Pattern floatPattern = Pattern.compile("^-?\\d+\\.\\d+(?:[eE][-+]?\\d+)?$");

    @Override
    public boolean tryAddLine(String line) {
        if (floatPattern.matcher(line).matches()) {
            try {
                buffer.add(Float.valueOf(line));
                return true;
            } catch (NumberFormatException e) {
                System.out.printf("Number processing error '%s' for type Float: %s%n", line, e.getMessage());
                if (line.toLowerCase().contains("e") &&
                        (line.length() > 15 || Math.abs(Double.parseDouble(line)) > Float.MAX_VALUE)) {
                    System.out.println("  Note: the number is too large to be accurately represented in Float");
                }
            }
        }

        return false;
    }

    @Override
    public void showSimpleStatistic() {
        System.out.println("Floats: " + buffer.size());
    }

    @Override
    public void showFullStatistic() {
        if (!buffer.isEmpty()) {
            System.out.println("\nMinFloat: " + buffer.stream().min(Float::compareTo).get());
            System.out.println("MaxFloat: " + buffer.stream().max(Float::compareTo).get());

            float sum = buffer.stream().reduce(0F, Float::sum);
            System.out.println("SumFloats: " + (Float.isInfinite(sum) ? "Too large" : sum));
            System.out.println("AverageFloat: " + sum / buffer.size());
        }
    }

    @Override
    public String getName() {
        return floatsName;
    }
}
