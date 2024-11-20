package src.test.java;

import org.junit.Test;
import Devices.SmartSpeaker;
import static org.junit.Assert.*;

public class SpeakerTest {

    @Test
    public void testInitialPowerStatus() {
        SmartSpeaker speaker = new SmartSpeaker();
        assertFalse(speaker.checkPowerStatus());
    }

    @Test
    public void testSwitchOn() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.switchOn();
        assertTrue(speaker.checkPowerStatus());
    }

    @Test
    public void testSwitchOff() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.switchOn();
        speaker.switchOff();
        assertFalse(speaker.checkPowerStatus());
    }

    @Test
    public void testTogglePower() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.togglePower();
        assertTrue(speaker.checkPowerStatus());
        speaker.togglePower();
        assertFalse(speaker.checkPowerStatus());
    }

    @Test
    public void testSetVolume() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.setVolume(70);
        assertEquals(70, speaker.getVolume());
    }

    @Test
    public void testIncreaseVolume() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.switchOn();
        speaker.increaseVolume(10);
        assertEquals(60, speaker.getVolume());
    }

    @Test
    public void testDecreaseVolume() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.switchOn();
        speaker.decreaseVolume(10);
        assertEquals(40, speaker.getVolume());
    }

    @Test
    public void testMuteUnmute() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.switchOn();
        speaker.mute();
        assertTrue(speaker.checkMuteStatus());
        speaker.unmute();
        assertFalse(speaker.checkMuteStatus());
        assertEquals(50, speaker.getVolume());
    }

    @Test
    public void testEnableWifi() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.enableWifi();
        System.out.println(speaker.checkConnectionStatus());
        assertFalse(speaker.checkConnectionStatus());
    }

    @Test
    public void testConnectToNetwork() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.enableWifi();
        speaker.connectToNetwork("HomeWiFi");
        assertTrue(speaker.checkConnectionStatus());
    }

    @Test
    public void testReconnectToNetwork() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.enableWifi();
        speaker.connectToNetwork("HomeWiFi");
        speaker.reconnect();
        assertTrue(speaker.checkConnectionStatus());
    }

    @Test
    public void testCheckConnectionStatusWithoutWifi() {
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.enableWifi();
        speaker.connectToNetwork("HomeWiFi");
        speaker.disableWifi();
        assertFalse(speaker.checkConnectionStatus());
    }
}
