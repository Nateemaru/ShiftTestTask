package com.nateemaru.models.dataFilter.buffers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBuffer<T> {
    protected final List<T> buffer = new ArrayList<>();
    public abstract boolean tryAddLine(String line);
    public abstract void showSimpleStatistic();
    public abstract void showFullStatistic();
    public abstract String getName();

    public List<String> asLines() {
        ArrayList<String> lines = new ArrayList<>();
        for (T item : buffer) {
            lines.add(item.toString());
        }
        return lines;
    }
}
