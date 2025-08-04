package com.nateemaru.models.parser;

import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ParseData implements Callable<Integer> {
    @CommandLine.Parameters(paramLabel = "FILES", description = "input files")
    private List<File> files = new ArrayList<>();

    @CommandLine.Option(names = "-s", description = "enable simple statistic")
    private boolean simpleStatistic = false;

    @CommandLine.Option(names = "-f", description = "enable full statistic")
    private boolean fullStatistic = false;

    @CommandLine.Option(names = "-a", description = "overwrite existing files")
    private boolean overwrite = false;

    @CommandLine.Option(names = "-p", description = "prefix")
    private String prefix = "";

    @CommandLine.Option(names = "-o", description = "specify the save path")
    private String path = "";

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public boolean isSimpleStatistic() {
        return simpleStatistic;
    }

    public void setSimpleStatistic(boolean simpleStatistic) {
        this.simpleStatistic = simpleStatistic;
    }

    public boolean isFullStatistic() {
        return fullStatistic;
    }

    public void setFullStatistic(boolean fullStatistic) {
        this.fullStatistic = fullStatistic;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
