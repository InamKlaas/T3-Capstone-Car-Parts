package za.ac.cput.carpartmarket.factory;

import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.util.Helper;

public class VehicleFactory {

    public static Vehicle createVehicle(Long vehicleId, int year, String model, String engineType, String fuelType){
        if(Helper.isEmptyOrNull(vehicleId)){
            return null;
        }
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
                .setVehicleId(vehicleId)
                .setYear(year)
                .setModel(model)
                .setEngineType(engineType)
                .setFuelType(fuelType)
                .build();
    }

}
