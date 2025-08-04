package com.nateemaru.models.dataFilter.buffers;

import java.util.Comparator;

public class StringBuffer extends BaseBuffer<String> {

    private final String stringsName = "Strings.txt";

    @Override
    public boolean tryAddLine(String line) {
        try {
            buffer.add(line);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void showSimpleStatistic() {
        System.out.println("Strings: " + buffer.size());
    }

    @Override
    public void showFullStatistic() {
        if (!buffer.isEmpty()) {
            System.out.println("\nMinString: " + buffer.stream().min(Comparator.comparingInt(String::length)).get());
            System.out.println("MaxString: " + buffer.stream().max(Comparator.comparingInt(String::length)).get());
            System.out.println();
        }
    }

    @Override
    public String getName() {
        return stringsName;
    }
}
