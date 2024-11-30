package pl.pjatk.car_service;

import org.springframework.stereotype.Component;
import pl.pjatk.car_service.storage.CarStorage;
import pl.pjatk.car_service.storage.RentalStorage;

import java.time.LocalDate;

@Component
public class RentalOffice {
    private final CarStorage carStorage;
    private final RentalStorage rentalStorage;
    private final String name = "Car NET";

    public RentalOffice(CarStorage carStorage, RentalStorage rentalStorage) {
        this.carStorage = carStorage;
        this.rentalStorage = rentalStorage;
    }

    public void rentalCar(Rental rental) {
        welcome();
        chooseCar(rental);
        if (finalizeRental(rental)) {
            carRelease(rental);
        } else {
            System.out.println("Car rental could not be completed.");
        }
    }

    public void welcome() {
        System.out.println("Welcome to " + getName() + "!");
        System.out.println("We are here to help you find the perfect car for your needs.");
    }

    public void chooseCar(Rental rental) {
        System.out.println("Choose the car you want to rental:");
        carStorage.listOfCars();
        System.out.println("Car you selected: " + rental.getCar());
    }

    public boolean finalizeRental(Rental rental) {
        System.out.println("Checking car availability...");
        if (isCarAvailable(rental.getCar().getVin(), rental.getStartDate(), rental.getEndDate())) {
            System.out.println("Car is available! Finalizing rental...");
            rentalStorage.addRental(rental);
            double totalPrice = rental.calculatePrice();
            System.out.println("Total price for rental: PLN " + totalPrice);
            return true;
        } else {
            System.out.println("Sorry, the car is not available for the selected dates.");
            return false;
        }
    }

    public void carRelease(Rental rental) {
        System.out.println("Car rental completed successfully!");
        System.out.println("Car is being prepared for: " + rental.getFirstName() + " " + rental.getLastName());
        System.out.println("Details of the " + rental);
    }

    private boolean isCarAvailable(String vin, LocalDate startDate, LocalDate endDate) {
        return rentalStorage.getRentalList().stream().noneMatch(rental ->
                rental.getCar().getVin().equals(vin) &&
                        (startDate.isBefore(rental.getEndDate()) && endDate.isAfter(rental.getStartDate()))
        );
    }

    public String getName() {
        return name;
    }
}