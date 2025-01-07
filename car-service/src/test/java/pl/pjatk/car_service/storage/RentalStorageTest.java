package pl.pjatk.car_service.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pjatk.car_service.Car;
import pl.pjatk.car_service.Rental;
import pl.pjatk.car_service.Standard;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RentalStorageTest {
    private RentalStorage rentalStorage;

    @BeforeEach
    void setUp() {this.rentalStorage = new RentalStorage();}

    @Test
    void returnCorrectListOfRentals() {
        //GIVEN
        Car car1 = new Car("BMW", "E92", "1HGCM82633A123456", Standard.NORMAL);
        Car car2 = new Car("BMW", "E60", "2T3BFREV5JW567890", Standard.NORMAL);
        Rental rental1 = new Rental(1, "Patryk", "Kowalski", LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 15), car1);
        Rental rental2 = new Rental(2, "Natan", "Nowak", LocalDate.of(2024, 12, 8), LocalDate.of(2024, 12, 11), car2);

        rentalStorage.addRental(rental1);
        rentalStorage.addRental(rental2);

        //WHEN
        List<Rental> rentalList = rentalStorage.getRentalList();

        //THEN
        assertThat(rentalList).containsExactly(rental1, rental2);
    }

    @Test
    void shouldNotContainRentalNotAdded() {
        // GIVEN
        Car car1 = new Car("BMW", "E92", "1HGCM82633A123456", Standard.NORMAL);
        Car car2 = new Car("BMW", "E60", "2T3BFREV5JW567890", Standard.NORMAL);
        Rental rental1 = new Rental(1, "Patryk", "Kowalski", LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 15), car1);
        Rental rental2 = new Rental(2, "Natan", "Nowak", LocalDate.of(2024, 12, 8), LocalDate.of(2024, 12, 11), car2);

        rentalStorage.addRental(rental1);

        // WHEN
        List<Rental> rentals = rentalStorage.getRentalList();

        // THEN
        assertThat(rentals).doesNotContain(rental2);
    }

    @Test
    void shouldAllowAddingTheSameHireMultipleTimes() {
        // GIVEN
        Car car = new Car("BMW", "M5", "VIN123", Standard.NORMAL);
        Rental rental = new Rental(1, "Jan", "Kowalski", LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 15), car);
        rentalStorage.addRental(rental);
        rentalStorage.addRental(rental);

        // WHEN
        List<Rental> rentalList = rentalStorage.getRentalList();

        // THEN
        assertThat(rentalList).containsExactly(rental, rental);
    }

    @Test
    void shouldAddHireToList() {
        // GIVEN
        Car car = new Car("BMW", "E92", "1HGCM82633A123456", Standard.NORMAL);
        Rental rental = new Rental(1, "Jan", "Kowalski", LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 15), car);

        // WHEN
        rentalStorage.addRental(rental);

        // THEN
        assertThat(rentalStorage.getRentalList()).contains(rental);
    }
}
