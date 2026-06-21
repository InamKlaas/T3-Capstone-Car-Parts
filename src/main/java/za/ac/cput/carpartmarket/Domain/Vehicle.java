package za.ac.cput.carpartmarket.Domain;

public class Vehicle {
    private Long vehicleId;
    private String model;
    private int year;
    private String engineType;
    private String fuelType;

    protected Vehicle(){

    }

    public Vehicle(Builder builder){
        this.vehicleId = builder.vehicleId;
        this.model = builder.model;
        this.year = builder.year;
        this.engineType= builder.engineType;
        this.fuelType = builder.fuelType;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getFuelType() {
        return fuelType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", engineType='" + engineType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }

    public static class Builder{
        private Long vehicleId;
        private String model;
        private int year;
        private String engineType;
        private String fuelType;

        public Builder setVehicleId(Long vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setEngineType(String engineType) {
            this.engineType = engineType;
            return this;
        }

        public Builder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder copy(Vehicle vehicle){
            this.vehicleId= vehicle.vehicleId;
            this.year= vehicle.year;
            this.model= vehicle.model;
            this.engineType=vehicle.engineType;
            this.fuelType= vehicle.fuelType;
            return this;
        }
        public Vehicle build(){
            return new Vehicle(this);
        }
    }
}
