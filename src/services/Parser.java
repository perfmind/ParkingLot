package services;

import commands.*;

import java.util.Map;
import java.util.HashMap;

public class Parser {
    private static ParkingService parkingService;

    private Map<String, Class<? extends Command>> mapParser;

    public Parser() {
        this.populateData();
    }

    public Class<? extends Command> getCommand(String commandNumber) {
        return this.mapParser.get(commandNumber);
    }

    ;

    private void populateData() {
        mapParser = new HashMap<>();
        this.mapParser.put("1", CreateParkingLotCommand.class);
        this.mapParser.put("2", ParkCommand.class);
        this.mapParser.put("3", LeaveCommand.class);
        this.mapParser.put("4", StatusCommand.class);
    }
}
