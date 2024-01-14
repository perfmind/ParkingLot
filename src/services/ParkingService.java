package services;

import app.ParkingLot;
import commands.Command;
import constants.ErrorCode;
import constants.Leave;
import models.Vehicle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ParkingService {
    private final ParkingLot parkingLot;
    private final Parser parser;

    public ParkingService() {
        parkingLot = new ParkingLot();
        parser = new Parser();
    }

    public void runCommand(String commandMenu) {
        BufferedReader bufferReader = null;
        String args = "";

        Class<? extends Command> objCommand = parser.getCommand(commandMenu);
        try {
            Command command = objCommand.getDeclaredConstructor(ParkingLot.class).newInstance(this.parkingLot);

            if (command.getArgsCount() == 0) {
                args = null;
            } else {
                System.out.println(command.getParameterInfo());
                bufferReader = new BufferedReader(new InputStreamReader(System.in));
                args = bufferReader.readLine().trim();
            }

            command.run(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getSlotNumbersFromColor(String color) {

    }
}
