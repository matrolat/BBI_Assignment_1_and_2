package Interfaces;

public interface PowerControl {
    void switchOn();
    void switchOff();
    void togglePower();
    boolean checkPowerStatus();
}