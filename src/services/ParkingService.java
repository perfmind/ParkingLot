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
            throw new RuntimeException(e);
        }
    }

    public void getRegistrationNumbersFromColor(String registrationNumber) {
        if (parkingLot.isLotInitialized()) {
            List<String> registrationList = parkingLot.getRegistrationNumbersFromColor(registrationNumber);
            if (registrationList == null || registrationList.isEmpty()) {
                System.out.println(ErrorCode.NOT_FOUND);
            } else {
                int index;
                for (index = 0; index < registrationList.size() - 1; index++)
                    System.out.print(registrationList.get(index) + ", ");

                System.out.println(registrationList.get(index));
            }

        } else {
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
        }
    }

    public void getSlotNumbersFromColor(String color) {
        if (parkingLot.isLotInitialized()) {
            List<Integer> slots = parkingLot.getSlotNumbersFromColor(color);
            if (slots == null || slots.isEmpty()) {
                System.out.println(ErrorCode.NOT_FOUND);
            } else {
                int i = 0;
                for (i = 0; i < slots.size() - 1; i++) {
                    System.out.print(slots.get(i) + ", ");
                }
                System.out.println(slots.get(i));
            }
        } else
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
    }

    public void getSlotNumberFromRegistrationNumber(String registrationNumber) {
        if (parkingLot.isLotInitialized()) {
            int slot = parkingLot.getSlotNumberFromRegistrationNumber(registrationNumber);
            if (slot != -1) {
                System.out.println(slot);
            } else {
                System.out.println(ErrorCode.NOT_FOUND);
            }
        } else
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
    }
}
