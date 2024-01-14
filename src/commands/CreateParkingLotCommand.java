package commands;

import app.ParkingLot;
import constants.ErrorCode;
import exceptions.InvalidParkingCommandArgumentException;

public class CreateParkingLotCommand extends Command {
    public CreateParkingLotCommand(ParkingLot parkingLot) {
        super(parkingLot);
        this.argsCount = 1;
    }

    @Override
    public void run(String args) throws InvalidParkingCommandArgumentException {
        super.run(args);

        String countLots = this.args[0];

        if (parkingLot.isLotInitialized()) {
            System.out.println("Parking Lot sudah dibuat, silakan pilih menu lainnya!");
        }

        try {
            int countLotsNumber = Integer.parseInt(countLots);
            if (countLotsNumber > 0) {
                parkingLot.createParkingLot(countLotsNumber);
                System.out.println("Parking lot berhasil dibuat dengan " + countLots + " slot");
            }
        } catch (Exception e) {
            System.out.println(ErrorCode.INVALID_SLOT_NUMBER);
        }
    }

    @Override
    public String getParameterInfo() {
        return "Masukkan berapa banyak slot parkir: ";
    }
}
