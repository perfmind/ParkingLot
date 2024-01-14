package commands;

import app.ParkingLot;
import constants.ErrorCode;
import exceptions.InvalidParkingCommandArgumentException;

import java.util.List;

public class GetRegistrationNumbersFromColorCommand extends Command{

    public GetRegistrationNumbersFromColorCommand(ParkingLot parkingLot) {
        super(parkingLot);
        this.argsCount = 1;
    }

    @Override
    public void run(String rawArgs) throws InvalidParkingCommandArgumentException {
        super.run(rawArgs);

        String color = this.args[0];

        if (parkingLot.isLotInitialized()) {
            List<String> registrationList = parkingLot.getRegistrationNumbersFromColor(color);
            if (registrationList == null || registrationList.isEmpty()) {
                System.out.println(ErrorCode.NOT_FOUND);
            } else {
                for (int index = 0; index < registrationList.size() - 1; index++)
                    System.out.print(registrationList.get(index) + ", ");

                System.out.println(registrationList.get(registrationList.size() - 1));
            }

        } else {
            System.out.println(ErrorCode.LOT_NOT_INITIALIZED);
        }
    }

    @Override
    public String getParameterInfo() {
        return "Masukkan warna kendaraan: ";
    }
}
