package commands;

import app.ParkingLot;
import constants.ErrorCode;
import exceptions.InvalidParkingCommandArgumentException;

public class ParkCommand extends Command {
    public ParkCommand(ParkingLot parkingLot) {
        super(parkingLot);
        this.argsCount = 2;
    }


    public void run(String rawArgs) throws InvalidParkingCommandArgumentException {
        super.run(rawArgs);

        String registrationNumber = this.args[0];
        String color = this.args[1];

        if (parkingLot.isLotInitialized()) {
            if (parkingLot.getAvailableSlotsCount() == 0) {
                System.out.println(ErrorCode.NO_AVAILABLE_SLOTS);
            } else {
                int slotNumber = parkingLot.park(registrationNumber, color);
                System.out.println("Allocated slot number: " + slotNumber);
            }
        } else {
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
        }
    }

    @Override
    public String getParameterInfo() {
        return "Masukkan kendaraan yang akan parkir (2 parameter, contoh: B-1111-Z Hitam): ";
    }
}
