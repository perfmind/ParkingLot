package commands;

import app.ParkingLot;
import constants.ErrorCode;
import exceptions.InvalidParkingCommandArgumentException;
import models.Vehicle;

import java.util.Map;

public class StatusCommand extends Command {

    public StatusCommand(ParkingLot parkingLot) {
        super(parkingLot);
        this.argsCount = 0;
    }

    public void run(String rawArgs) throws InvalidParkingCommandArgumentException {
        super.run(rawArgs);

        if (parkingLot.isLotInitialized()) {
            Map<Integer, Vehicle> map = parkingLot.status();
            if (!map.isEmpty()) {
                System.out.println("No. Slot\tNomor registrasi\tWarna");

                map.keySet().forEach(slot -> {
                    Vehicle car = map.get(slot);
                    System.out.println(slot + "\t\t\t" + car.getRegistrationNumber() + "\t\t\t" + car.getColor());
                });

            } else
                System.out.println(ErrorCode.NO_CAR_PARKED);
        } else {
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
        }
    }

    @Override
    public String getParameterInfo() {
        return "";
    }
}
