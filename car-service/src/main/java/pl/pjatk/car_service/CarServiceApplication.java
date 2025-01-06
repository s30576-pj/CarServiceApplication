package pl.pjatk.car_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class CarServiceApplication {
	private final RentalOffice rentalOffice;

	public CarServiceApplication(RentalOffice rentalOffice) {
		this.rentalOffice = rentalOffice;
		StartApplication();
	}

	public void StartApplication() {
		Car car1 = new Car("BMW", "E92", "1HGCM82633A123456", Standard.NORMAL);

		Rental rental1 = new Rental(1,"Natan","Kowalski", LocalDate.of(2024,12,1),LocalDate.of(2024,12,3), car1);
		rentalOffice.rentalCar(rental1);

		Rental rental2 = new Rental(2,"Szymon","Nowak", LocalDate.of(2024,12,5),LocalDate.of(2024,12,10), car1);
		rentalOffice.rentalCar(rental2);

		Rental rental3 = new Rental(3,"Jakub","Szymczak", LocalDate.of(2024,12,10),LocalDate.of(2024,12,20), car1);
		rentalOffice.rentalCar(rental3);

		Rental rental4 = new Rental(4,"Patryk","Pek", LocalDate.of(2024,12,22),LocalDate.of(2024,12,25), car1);
		rentalOffice.rentalCar(rental4);

		Rental rental5 = new Rental(5,"Natan","Kowalski", LocalDate.of(2024,12,5),LocalDate.of(2024,12,20), car1);
		rentalOffice.rentalCar(rental5);

		Rental rental6 = new Rental(6,"Szymon","Nowak", LocalDate.of(2024,12,8),LocalDate.of(2024,12,11), car1);
		rentalOffice.rentalCar(rental6);

		Rental rental7 = new Rental(7,"Jakub","Szymczak", LocalDate.of(2024,12,10),LocalDate.of(2024,12,15), car1);
		rentalOffice.rentalCar(rental7);

		Rental rental8 = new Rental(8,"Patryk","Pek", LocalDate.of(2024,12,13),LocalDate.of(2024,12,17), car1);
		rentalOffice.rentalCar(rental8);

		Rental rental9 = new Rental(9,"Patryk","Pek", LocalDate.of(2024,12,12),LocalDate.of(2024,12,14), car1);
		rentalOffice.rentalCar(rental9);
	}

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}

}