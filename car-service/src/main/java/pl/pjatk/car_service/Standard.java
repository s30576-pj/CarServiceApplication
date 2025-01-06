package pl.pjatk.car_service;

public enum Standard {
    NORMAL(300.0),
    YOUNGTIMER(500.0),
    PREMIUM(700.0);

    private double value;

    Standard(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
