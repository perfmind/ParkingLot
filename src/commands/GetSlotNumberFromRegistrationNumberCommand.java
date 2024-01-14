package commands;

import app.ParkingLot;
import constants.ErrorCode;
import exceptions.InvalidParkingCommandArgumentException;

import java.util.List;

public class GetSlotNumberFromRegistrationNumberCommand extends Command {
    public GetSlotNumberFromRegistrationNumberCommand(ParkingLot parkingLot) {
        super(parkingLot);
        this.argsCount = 1;
    }

    @Override
    public void run(String rawArgs) throws InvalidParkingCommandArgumentException {
        super.run(rawArgs);

        String registrationNumber = this.args[0];

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

    @Override
    public String getParameterInfo() {
        return "Masukkan nomor registrasi kendaraan: ";
    }
}
