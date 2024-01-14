package commands;

import app.ParkingLot;
import exceptions.InvalidParkingCommandArgumentException;
import exceptions.InvalidParkingCommandException;

abstract public class Command {
    protected int argsCount;
    protected String[] args;
    protected ParkingLot parkingLot;

    public Command(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean validate(String rawArgs) {
        if (this.argsCount == 0) {
            return true;
        }
        this.args = rawArgs.split(" ");
        return args.length == this.argsCount;
    }

    public void run(String rawArgs) throws InvalidParkingCommandArgumentException {
        if (!this.validate(rawArgs)) {
            throw new InvalidParkingCommandArgumentException(rawArgs);
        }
    }

    public int getArgsCount() {
        return this.argsCount;
    }

    abstract public String getParameterInfo();
}
