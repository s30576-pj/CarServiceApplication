package pl.pjatk.car_service;

public class Car {
    private String brand;
    private String model;
    private String vin;
    private String standard;

    public Car(String brand, String model, String vin, String standard) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.standard = standard;
    }

    public String getVin() {
        return vin;
    }

    @Override
    public String toString() {
        return "Car:" + brand +
                " " + model +
                " (" + standard + ")";
    }
}