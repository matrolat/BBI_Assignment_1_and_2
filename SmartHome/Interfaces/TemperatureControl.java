package Interfaces;

public interface TemperatureControl {
    void setTemperature(double temperature);
    double getTemperature();
    void increaseTemp(double increment);
    void decreaseTemp(double decrement);
}
