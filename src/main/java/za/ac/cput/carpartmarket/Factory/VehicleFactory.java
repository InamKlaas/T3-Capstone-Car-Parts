package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Util.Helper;

public class VehicleFactory {

    public static Vehicle createVehicle(int year, String model, String engineType, String fuelType){
               if (year <= 0) {
            return null;
        }
        if (Helper.isEmptyOrNull(model)) {
            return null;
        }
        if (Helper.isEmptyOrNull(engineType)) {
            return null;
        }
        if (Helper.isEmptyOrNull(fuelType)) {
            return null;
        }

        return new Vehicle.Builder()
                .setYear(year)
                .setModel(model)
                .setEngineType(engineType)
                .setFuelType(fuelType)
                .build();
    }

}
