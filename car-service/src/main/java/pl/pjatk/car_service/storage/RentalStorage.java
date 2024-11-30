package pl.pjatk.car_service.storage;

import org.springframework.stereotype.Component;
import pl.pjatk.car_service.Rental;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
    List<Rental> rentalList;

    public RentalStorage() {
        this.rentalList = new ArrayList<>();
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void addRental(Rental rental) {
        rentalList.add(rental);
    }
}