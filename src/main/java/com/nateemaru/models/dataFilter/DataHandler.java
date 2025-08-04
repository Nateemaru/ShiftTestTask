package com.nateemaru.models.dataFilter;

import com.nateemaru.models.dataFilter.buffers.BaseBuffer;
import com.nateemaru.models.dataFilter.buffers.FloatBuffer;
import com.nateemaru.models.dataFilter.buffers.IntegerBuffer;
import com.nateemaru.models.dataFilter.buffers.StringBuffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataHandler {
    private final List<BaseBuffer<?>> buffers = new ArrayList<>();

    public final List<BaseBuffer<?>> readOnlyBuffers = Collections.unmodifiableList(buffers);

    public DataHandler() {
        buffers.add(new FloatBuffer());
        buffers.add(new IntegerBuffer());
        buffers.add(new StringBuffer());
    }

    public void cache(List<String> lines) {
        for (String line : lines) {
            handle(line);
        }
    }

    private void handle(String line) {
        for (BaseBuffer<?> outputBuffer : buffers) {
            if (outputBuffer.tryAddLine(line)) {
                return;
            }
        }
    }
}
