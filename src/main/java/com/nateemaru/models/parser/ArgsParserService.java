package com.nateemaru.models.parser;

import picocli.CommandLine;

public class ArgsParserService {

    public int parse(String[] args, ParseData data) {

        CommandLine cmd = new CommandLine(data);
        return cmd.execute(args);
    }
}
