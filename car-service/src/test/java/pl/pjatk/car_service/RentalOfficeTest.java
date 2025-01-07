package pl.pjatk.car_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.car_service.storage.RentalStorage;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalOfficeTest {

    @Captor
    private ArgumentCaptor<Rental> rentalCaptor;

    @Mock
    private RentalStorage rentalStorage;

    @InjectMocks
    @Spy
    private RentalOffice rentalOffice;

    @Test
    void shouldPassCorrectRental() {
        //GIVE
        Car car = new Car("BMW", "E92", "VIN123", Standard.NORMAL);
        Rental rental = new Rental(1,"Jan","Kowalski", LocalDate.of(2024,12,10),LocalDate.of(2024,12,15), car);
        when(rentalStorage.getRentalList()).thenReturn(List.of());
        when(rentalOffice.isCarAvailable(anyString(), any(LocalDate.class), any(LocalDate.class))).thenReturn(true);

        //WHEN
        rentalOffice.finalizeRental(rental);

        //THEN
        verify(rentalStorage).addRental(rentalCaptor.capture());
        Rental capturedRental = rentalCaptor.getValue();
        assertThat(capturedRental).isNotNull();
        assertThat(capturedRental).isEqualTo(rental);
    }

    @Test
    void shouldReturnTrueWhenCarIsAvailable() {
        //GIVEN
        Car car = new Car("BMW", "E92", "VIN123", Standard.NORMAL);
        Rental rental = new Rental(1,"Jan","Kowalski", LocalDate.of(2024,12,10),LocalDate.of(2024,12,15), car);
        when(rentalStorage.getRentalList()).thenReturn(List.of());

        //WHEN
        boolean result = rentalOffice.isCarAvailable(car.getVin(), rental.getStartDate(), rental.getEndDate());

        //THEN
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenCarIsNotAvailable() {
        //GIVEN
        Car car = new Car("BMW", "E92", "VIN123", Standard.NORMAL);
        Rental rental = new Rental(1,"Jan","Kowalski", LocalDate.of(2024,12,10),LocalDate.of(2024,12,15), car);
        Rental existingRental = new Rental(2, "Natan", "Nowak", LocalDate.of(2024, 12, 8), LocalDate.of(2024, 12, 11), car);
        when(rentalStorage.getRentalList()).thenReturn(List.of(rental));

        //WHEN
        boolean result = rentalOffice.isCarAvailable(car.getVin(), existingRental.getStartDate(), existingRental.getEndDate());

        //THEN
        assertThat(result).isFalse();
    }
}
