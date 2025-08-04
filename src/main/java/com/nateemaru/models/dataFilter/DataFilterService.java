package com.nateemaru.models.dataFilter;

import com.nateemaru.models.dataFilter.buffers.BaseBuffer;
import com.nateemaru.models.inputOutput.ReadService;
import com.nateemaru.models.inputOutput.WriteService;
import com.nateemaru.models.parser.ParseData;

import java.util.List;

public class DataFilterService {
    private final ReadService reader;
    private final WriteService writer;
    private final DataHandler dataHandler;

    public DataFilterService(ReadService readService, WriteService writeService, DataHandler dataHandler) {
        this.reader = readService;
        this.writer = writeService;
        this.dataHandler = dataHandler;
    }

    public void execute(ParseData data) {

        dataHandler.cache(reader.read(data.getFiles()));
        write(data.getPrefix(), data.getPath(), data.isOverwrite());
        showStatistics(data.isSimpleStatistic(), data.isFullStatistic());
    }

    private void showStatistics(boolean showSimpleStats, boolean showFullStats) {
        if (showSimpleStats && showFullStats) {
            System.out.println("\nDon't use the -s and -f functions at the same time.");
            System.out.println("Otherwise only -f will be called as a priority.");
        }

        if (showSimpleStats && !showFullStats) {
            showSimpleStatistic();

        } else if (showFullStats) {
            showSimpleStatistic();
            showFullStatistic();
        }
    }

    private void write(String prefix, String path, boolean overwrite) {

        for (BaseBuffer<?> buffer : dataHandler.readOnlyBuffers) {
            writer.write(buffer, buffer.getName(), prefix, path, overwrite);
        }
    }

    private void showSimpleStatistic() {
        System.out.println("\nStatistics:");
        for (BaseBuffer<?> buffer : dataHandler.readOnlyBuffers) {
            buffer.showSimpleStatistic();
        }
    }

    private void showFullStatistic() {
        for (BaseBuffer<?> buffer : dataHandler.readOnlyBuffers) {
            buffer.showFullStatistic();
        }
    }
}
