package pl.pjatk.car_service;

public class Car {
    private String brand;
    private String model;
    private String vin;
    private Standard standard;

    public Car(String brand, String model, String vin, Standard standard) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.standard = standard;
    }

    public String getVin() {
        return vin;
    }

    public Standard getStandard() {
        return standard;
    }
}