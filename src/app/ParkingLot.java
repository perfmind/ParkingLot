package app;


import constants.Leave;
import models.Vehicle;

import java.util.*;

public class ParkingLot {
    int NUMBER_OF_LOTS = 0;

    List<Integer> availableSlots;
    Map<Integer, Vehicle> slotCarMapping;
    Map<String, Integer> slotRegistrationMapping;
    Map<String, ArrayList<String>> colorRegistrationMapping;

    public void createParkingLot(int countLots) {
        this.NUMBER_OF_LOTS = countLots;

        this.availableSlots = new ArrayList<>();
        for (int i = 1; i <= this.NUMBER_OF_LOTS; i++) {
            availableSlots.add(i);
        }

        this.colorRegistrationMapping = new HashMap<>();
        this.slotCarMapping = new HashMap<>();
        this.slotRegistrationMapping = new HashMap<>();
    }

    public Leave leave(int slotNumber) {
        if (!this.slotCarMapping.isEmpty()) {

            Vehicle carInfo = slotCarMapping.get(slotNumber);

            if (carInfo != null) {
                slotRegistrationMapping.remove(carInfo.registrationNumber());
                slotCarMapping.remove(slotNumber);

                ArrayList<String> registrationList = colorRegistrationMapping.get(carInfo.color());

                registrationList.remove(carInfo.registrationNumber());

                availableSlots.add(slotNumber);
                return Leave.LEAVE_SUCCESS;
            } else {
                return Leave.LEAVE_SLOT_FREE;
            }
        } else
            return Leave.LEAVE_LOT_EMPTY;
    }

    public List<String> getRegistrationNumbersFromColor(String color) {
        if (colorRegistrationMapping == null) {
            return null;
        }
        return colorRegistrationMapping.get(color);
    }

    public int park(String registrationNumber, String color) {

        Collections.sort(availableSlots);
        int slot = availableSlots.get(0);
        Vehicle newCar = new Vehicle(registrationNumber, color);

        slotCarMapping.put(slot, newCar);
        slotRegistrationMapping.put(registrationNumber, slot);

        if (colorRegistrationMapping.get(color) != null) {
            ArrayList<String> registrationNumbers = colorRegistrationMapping.get(color);
            registrationNumbers.add(registrationNumber);
            colorRegistrationMapping.put(color, registrationNumbers);
        } else {
            ArrayList<String> registrationNumbers = new ArrayList<>();
            registrationNumbers.add(registrationNumber);
            colorRegistrationMapping.put(color, registrationNumbers);
        }

        availableSlots.remove(0);

        return slot;

    }

    public Map<Integer, Vehicle> status() {
        return slotCarMapping;
    }

    public List<Integer> getSlotNumbersFromColor(String color) {
        List<Integer> slots = new ArrayList<>();

        if (colorRegistrationMapping == null) {
            return null;
        } else if (colorRegistrationMapping.get(color) != null) {
            ArrayList<String> registrationList = colorRegistrationMapping.get(color);

            for (String s : registrationList) {
                slots.add(slotRegistrationMapping.get(s));
            }

            Collections.sort(slots);
            return slots;

        } else {
            return slots;
        }
    }

    public int getSlotNumberFromRegistrationNumber(String registrationNumber) {
        if (slotRegistrationMapping == null || slotRegistrationMapping.get(registrationNumber) == null) {
            return -1;
        } else {
            return slotRegistrationMapping.get(registrationNumber);
        }
    }

    public int getNumberOfLots() {
        return this.NUMBER_OF_LOTS;
    }

    public int getAvailableSlotsCount() {
        return this.availableSlots.size();
    }

    public boolean isLotInitialized() {
        return this.NUMBER_OF_LOTS != 0;
    }
}