package src.test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import Devices.SmartThermostat;

public class SmartThermostatTest {

    // Test Power Control

    @Test
    public void testInitialPowerStatus() {
        SmartThermostat thermostat = new SmartThermostat();
        assertFalse("Thermostat should be off initially", thermostat.checkPowerStatus());
    }

    @Test
    public void testSwitchOn() {
        SmartThermostat thermostat = new SmartThermostat();
        thermostat.switchOn();
        assertTrue("Thermostat should be on after switching on", thermostat.checkPowerStatus());
    }

    @Test
    public void testSwitchOff() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.switchOn();
        thermostat.switchOff();
        assertFalse("Thermostat should be off after switching off", thermostat.checkPowerStatus());
    }

    @Test
    public void testTogglePower() {
        SmartThermostat thermostat = new SmartThermostat();
        thermostat.switchOn();  
        assertTrue("Thermostat should be on after toggle", thermostat.checkPowerStatus());
        thermostat.togglePower(); 
        assertFalse("Thermostat should be off after toggle", thermostat.checkPowerStatus());
    }

    // Test Temperature Control

    @Test
    public void testSetTemperature() {
        SmartThermostat thermostat = new SmartThermostat();
        thermostat.switchOn();
        thermostat.setTemperature(25.0);
        assertEquals("Temperature should be set to 25.0°C", 25.0, thermostat.getTemperature(), 0.01);
    }

    @Test
    public void testIncreaseTemperature() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.switchOn();  
        thermostat.increaseTemp(3.0);
        assertEquals("Temperature should be increased to 25.0°C", 25.0, thermostat.getTemperature(), 0.01);
    }

    @Test
    public void testDecreaseTemperature() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.switchOn();  
        thermostat.decreaseTemp(2.0);
        assertEquals("Temperature should be decreased to 20.0°C", 20.0, thermostat.getTemperature(), 0.01);
    }

    @Test
    public void testSetTemperatureWhenOff() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.switchOff(); 
        thermostat.setTemperature(25.0);
        assertEquals("Temperature should not be set when power is off", -1, thermostat.getTemperature(), 0.01);
    }

    // Test Network Control

    @Test
    public void testEnableWifi() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.enableWifi();
        assertFalse("Wi-Fi should be connected", thermostat.checkConnectionStatus());
    }

    @Test
    public void testDisableWifi() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.enableWifi();
        thermostat.disableWifi();
        assertFalse("Wi-Fi should be disabled", thermostat.checkConnectionStatus());
    }

    @Test
    public void testConnectToNetwork() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.enableWifi();
        thermostat.connectToNetwork("home_network");
        assertTrue("Thermostat should be connected to home_network", thermostat.checkConnectionStatus());
    }

    @Test
    public void testReconnectToNetwork() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.enableWifi();
        thermostat.connectToNetwork("home_network");
        thermostat.reconnect();
        assertTrue("Thermostat should reconnect to home_network", thermostat.checkConnectionStatus());
    }

    @Test
    public void testCheckConnectionStatusWithoutWifi() {
        SmartThermostat thermostat = new SmartThermostat(22.0);
        thermostat.enableWifi();
        thermostat.connectToNetwork("home_network");
        thermostat.disableWifi();
        assertFalse("Thermostat should not be connected when Wi-Fi is disabled", thermostat.checkConnectionStatus());
    }
}
