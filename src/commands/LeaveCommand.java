package commands;

import app.ParkingLot;
import constants.ErrorCode;
import exceptions.InvalidParkingCommandArgumentException;

public class LeaveCommand extends Command {
    public LeaveCommand(ParkingLot parkingLot) {
        super(parkingLot);
        this.argsCount = 1;
    }

    @Override
    public void run(String rawArgs) throws InvalidParkingCommandArgumentException {
        super.run(rawArgs);

        String slot = this.args[0];

        if (parkingLot.isLotInitialized()) {
            try {
                constants.Leave leaveEnum = parkingLot.leave(Integer.parseInt(slot));
                if (leaveEnum == constants.Leave.LEAVE_SUCCESS) {
                    System.out.println("Slot number " + slot + " is free");
                } else if (leaveEnum == constants.Leave.LEAVE_SLOT_FREE) {
                    System.out.println(ErrorCode.NO_CAR_PARKED_SLOT);
                } else if (leaveEnum == constants.Leave.LEAVE_LOT_EMPTY) {
                    System.out.println(ErrorCode.NO_CAR_PARKED);
                }
            } catch (Exception e) {
                System.out.println(ErrorCode.INVALID_SLOT_NUMBER);
            }
        } else {
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
        }
    }

    @Override
    public String getParameterInfo() {
        return "Masukkan slot parkir yang akan keluar: ";
    }
}
