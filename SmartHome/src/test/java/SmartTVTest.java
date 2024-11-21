package src.test.java;

import org.junit.Test;
import Devices.SmartTV;
import static org.junit.Assert.*;

public class SmartTVTest {

    // Test Power Control
    @Test
    public void testInitialPowerStatus() {
        SmartTV tv = new SmartTV();
        assertFalse("TV should be off initially", tv.checkPowerStatus());
    }

    @Test
    public void testSwitchOn() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        assertTrue("TV should be on after switching on", tv.checkPowerStatus());
    }

    @Test
    public void testSwitchOff() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        tv.switchOff();
        assertFalse("TV should be off after switching off", tv.checkPowerStatus());
    }

    @Test
    public void testTogglePower() {
        SmartTV tv = new SmartTV();
        tv.togglePower();  
        assertTrue("TV should be on after toggle", tv.checkPowerStatus());
        tv.togglePower();  
        assertFalse("TV should be off after toggle", tv.checkPowerStatus());
    }

    // Test Audio Control
    @Test
    public void testSetVolume() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        tv.setVolume(70);
        assertEquals("Volume should be set to 70", 70, tv.getVolume());
    }

    @Test
    public void testIncreaseVolume() {
        SmartTV tv = new SmartTV();
        tv.switchOn(); 
        tv.increaseVolume(10); 
        assertEquals("Volume should be increased to 60", 60, tv.getVolume());
    }

    @Test
    public void testDecreaseVolume() {
        SmartTV tv = new SmartTV();
        tv.switchOn(); 
        tv.decreaseVolume(10);  
        assertEquals("Volume should be decreased to 40", 40, tv.getVolume());
    }

    @Test
    public void testMuteUnmute() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        tv.mute();
        assertTrue("Mute function works successfully", tv.checkMuteStatus());
        tv.unmute();
        assertFalse("Unmute function works successfully", tv.checkMuteStatus());
        assertEquals("Volume should return to 50 after unmute", 50, tv.getVolume());
    }

    // Test Network Control
    @Test
    public void testEnableWifi() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        tv.enableWifi();
        tv.connectToNetwork("HomeWiFi");
        assertTrue("Wi-Fi should be enabled", tv.checkConnectionStatus());
    }
    
    @Test
    public void testDisableWifi() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        tv.enableWifi();
        tv.disableWifi();
        assertFalse("Wi-Fi should be disabled", tv.checkConnectionStatus());
    }

    @Test
    public void testConnectToNetwork() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        tv.enableWifi();
        tv.connectToNetwork("HomeWiFi");
        assertTrue("TV should be connected to HomeWiFi", tv.checkConnectionStatus());
    }

    @Test
    public void testReconnectToNetwork() {
        SmartTV tv = new SmartTV();
        tv.switchOn();
        tv.enableWifi();
        tv.connectToNetwork("HomeWiFi");
        tv.reconnect();
        assertTrue("TV should reconnect to HomeWiFi", tv.checkConnectionStatus());
    }

    @Test
    public void testCheckConnectionStatusWithoutWifi() {
        SmartTV tv = new SmartTV();
        tv.enableWifi();
        tv.connectToNetwork("HomeWiFi");
        tv.disableWifi();
        assertFalse("TV should not be connected when Wi-Fi is disabled", tv.checkConnectionStatus());
    }
}
