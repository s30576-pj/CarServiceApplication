package pl.pjatk.car_service.storage;

import org.springframework.stereotype.Component;
import pl.pjatk.car_service.Car;
import pl.pjatk.car_service.Standard;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarStorage {
    List<Car> catalog;

    public CarStorage() {
        catalog = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        catalog.add(new Car("BMW", "E92", "1HGCM82633A123456", Standard.NORMAL));
        catalog.add(new Car("BMW", "E60", "2T3BFREV5JW567890", Standard.NORMAL));
        catalog.add(new Car("BMW", "E30", "3VWFE21C04M234567", Standard.YOUNGTIMER));
        catalog.add(new Car("BMW", "E39", "5NPE24AF4FH123456", Standard.YOUNGTIMER));
        catalog.add(new Car("BMW", "G80", "JH4KA8260MC123456", Standard.PREMIUM));
    }

    public void listOfCars() {
        System.out.println("Cars:");
        for (Car car : catalog) {
            System.out.println("- " + car);
        }
    }
}