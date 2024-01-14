package commands;

import app.ParkingLot;
import constants.ErrorCode;
import exceptions.InvalidParkingCommandArgumentException;

import java.util.List;

public class GetSlotNumbersFromColorCommand extends Command {
    public GetSlotNumbersFromColorCommand(ParkingLot parkingLot) {
        super(parkingLot);
        this.argsCount = 1;
    }

    @Override
    public void run(String rawArgs) throws InvalidParkingCommandArgumentException {
        super.run(rawArgs);

        String color = this.args[0];

        if (parkingLot.isLotInitialized()) {
            List<Integer> slots = parkingLot.getSlotNumbersFromColor(color);
            if (slots == null || slots.isEmpty()) {
                System.out.println(ErrorCode.NOT_FOUND);
            } else {
                for (int i = 0; i < slots.size() - 1; i++) {
                    System.out.print(slots.get(i) + ", ");
                }
                System.out.println(slots.get(slots.size() - 1));
            }
        } else
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
    }

    @Override
    public String getParameterInfo() {
        return "Masukkan warna kendaraan: ";
    }
}
