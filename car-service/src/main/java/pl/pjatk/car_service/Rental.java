package pl.pjatk.car_service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private Car car;

    public Rental(int id, String firstName, String lastName, LocalDate startDate, LocalDate endDate, double price, Car car) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.car = car;
    }

    public double calculatePrice() {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        return days * price;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "rental: " +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price per day=" + price +
                ", " + car + '\n';
    }
}