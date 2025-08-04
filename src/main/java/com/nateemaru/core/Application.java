package com.nateemaru.core;

import com.nateemaru.models.dataFilter.DataFilterService;
import com.nateemaru.models.dataFilter.DataHandler;
import com.nateemaru.models.inputOutput.ReadService;
import com.nateemaru.models.inputOutput.WriteService;
import com.nateemaru.models.parser.ArgsParserService;
import com.nateemaru.models.parser.ParseData;

public class Application {
    public static void main(String[] args) {

        ParseData data = new ParseData();
        int exitCode = new ArgsParserService().parse(args, data);

        if (exitCode == 0) {
            var readService = new ReadService();
            var writeService = new WriteService();
            var dataHandler = new DataHandler();
            var filter = new DataFilterService(readService, writeService, dataHandler);
            filter.execute(data);
        }

        System.exit(exitCode);
    }
}
