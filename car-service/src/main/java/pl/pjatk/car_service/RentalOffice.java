package pl.pjatk.car_service;

import org.springframework.stereotype.Component;
import pl.pjatk.car_service.storage.RentalStorage;

import java.time.LocalDate;

@Component
public class RentalOffice {
    private final RentalStorage rentalStorage;
    private final String name = "Car NET";

    public RentalOffice(RentalStorage rentalStorage) {
        this.rentalStorage = rentalStorage;
    }

    public void rentalCar(Rental rental) {
        if (finalizeRental(rental)) {
            carRelease(rental);
        } else {
            System.out.println("Car rental could not be completed.");
        }
    }

    public boolean finalizeRental(Rental rental) {
        if (isCarAvailable(rental.getCar().getVin(), rental.getStartDate(), rental.getEndDate())) {
            rentalStorage.addRental(rental);
            double totalPrice = rental.calculatePrice(rental.getCar());
            System.out.println("Total price for rental: PLN " + totalPrice);
            return true;
        } else {
            System.out.println("Sorry, the car is not available for the selected dates.");
            return false;
        }
    }

    public void carRelease(Rental rental) {
        System.out.println("Car rental completed successfully!\n");
        System.out.println("Car is being prepared for: " + rental.getFirstName() + " " + rental.getLastName());
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