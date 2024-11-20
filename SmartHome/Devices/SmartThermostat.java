package Devices;

import Interfaces.TemperatureControl;
import Interfaces.PowerControl;
import Interfaces.NetworkConnected;

public class SmartThermostat implements TemperatureControl, PowerControl, NetworkConnected {
    private double currentTemperature;
    private boolean powerStatus; 
    private boolean wifiEnabled; 
    private String connectedNetwork;

    // Constructor
    public SmartThermostat(double initialTemperature) {
        this.currentTemperature = initialTemperature;
        this.powerStatus = true;
        this.wifiEnabled = false; 
        this.connectedNetwork = null; 
    }
    
    public SmartThermostat()
    {    
        this.currentTemperature = 17;
        this.powerStatus = false;
        this.wifiEnabled = false; 
        this.connectedNetwork = null; 
    }

    // Implementing TemperatureControl methods
    @Override
    public void setTemperature(double temperature) {
        if (powerStatus) {
            this.currentTemperature = temperature;
            System.out.println("Temperature set to: " + currentTemperature + "°C");
        } else {
            System.out.println("Cannot set temperature. Power is OFF.");
        }
    }

    @Override
    public double getTemperature() {
        if (powerStatus) {
            System.out.println("Current temperature: " + currentTemperature + "°C");
            return currentTemperature;
        } else {
            System.out.println("Cannot get temperature. Power is OFF.");
            return -1;
        }
    }

    @Override
    public void increaseTemp(double increment) {
        if (powerStatus) {
            this.currentTemperature += increment;
            System.out.println("Temperature increased by " + increment + "°C. New temperature: " + currentTemperature + "°C");
        } else {
            System.out.println("Cannot increase temperature. Power is OFF.");
        }
    }

    @Override
    public void decreaseTemp(double decrement) {
        if (powerStatus) {
            this.currentTemperature -= decrement;
            System.out.println("Temperature decreased by " + decrement + "°C. New temperature: " + currentTemperature + "°C");
        } else {
            System.out.println("Cannot decrease temperature. Power is OFF.");
        }
    }

    // Implementing PowerControl methods
    @Override
    public void switchOn() {
        powerStatus = true;
        System.out.println("Thermostat is now ON.");
    }

    @Override
    public void switchOff() {
        powerStatus = false;
        System.out.println("Thermostat is now OFF.");
    }

    @Override
    public void togglePower() {
        powerStatus = !powerStatus;
        System.out.println("Thermostat power toggled. Current status: " + (powerStatus ? "ON" : "OFF"));
    }

    @Override
    public boolean checkPowerStatus() {
        System.out.println("Power status: " + (powerStatus ? "ON" : "OFF"));
        return powerStatus;
    }

    // Implementing NetworkConnected methods
    @Override
    public boolean checkConnectionStatus() {
        System.out.println("WiFi connection status: " + (wifiEnabled && connectedNetwork != null ? "Connected to " + connectedNetwork : "Disconnected"));
        return wifiEnabled && connectedNetwork != null;
    }

    @Override
    public void enableWifi() {
        wifiEnabled = true;
        System.out.println("WiFi enabled.");
    }

    @Override
    public void disableWifi() {
        wifiEnabled = false;
        connectedNetwork = null;
        System.out.println("WiFi disabled.");
    }

    @Override
    public void connectToNetwork(String network) {
        if (wifiEnabled) {
            connectedNetwork = network;
            System.out.println("Connected to network: " + network);
        } else {
            System.out.println("Cannot connect to network. WiFi is disabled.");
        }
    }

    @Override
    public void reconnect() {
        if (wifiEnabled && connectedNetwork != null) {
            System.out.println("Reconnecting to network: " + connectedNetwork);
        } else {
            System.out.println("Cannot reconnect. No network configured or WiFi is disabled.");
        }
    }
}
