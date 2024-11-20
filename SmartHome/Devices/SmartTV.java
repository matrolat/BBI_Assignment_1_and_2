package Devices;

import Interfaces.AudioControl;
import Interfaces.NetworkConnected;
import Interfaces.PowerControl;

public class SmartTV implements AudioControl, PowerControl, NetworkConnected {
    // sound variables
    private int volume = 50; 
    // private boolean isSoundActive = true; 
    private boolean isMuted = false; 
    // power variables
    private boolean isSoundActive = false; 
    // network variables
    private boolean isWifiEnabled = false;
    private String connectedNetwork = null;

    // Sound Methods
    @Override
    public void enableAudio() {
        isSoundActive = true;
        System.out.println("Audio enabled.");
    }

    @Override
    public void disableAudio() {
        isSoundActive = false;
        System.out.println("Audio disabled.");
    }

    @Override
    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
            System.out.println("Volume set to: " + volume);
        } else {
            System.out.println("Set a valid volume (0-100).");
        }
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void increaseVolume(int amount) {

        if(amount<0)
        {
            System.out.println("Volume amount is not valid");
            return;
        }

        if (!isMuted && isSoundActive ) {
            if (volume + amount <= 100) {
                volume += amount;
                System.out.println("Volume increased to: " + volume);
            } else {
                volume = 100; // Set to maximum if exceeding the limit
                System.out.println("Volume is at maximum: 100");
            }
        } else {
            System.out.println("Audio is muted or inactive. Cannot increase volume.");
        }
    }

    @Override
    public void decreaseVolume(int amount) {

        if(amount<0)
        {
            System.out.println("Volume amount is not valid");
            return;
        }

        if (!isMuted && isSoundActive) {
            if (volume - amount >= 0) {
                volume -= amount;
                System.out.println("Volume decreased to: " + volume);
            } else {
                volume = 0; // Set to minimum if falling below 0
                System.out.println("Volume is at minimum: 0");
            }
        } else {
            System.out.println("Audio is muted or inactive. Cannot decrease volume.");
        }
    }

    @Override
    public void mute() {
        isMuted = true;
        System.out.println("Audio muted.");
    }

    @Override
    public void unmute() {
        isMuted = false;
        System.out.println("Audio unmuted.");
    }

    @Override
    public boolean checkMuteStatus()
    {
        return isMuted;
    }

   // Power Methods
    @Override
    public void switchOn() {
        if (!isSoundActive) {
            isSoundActive = true;
            System.out.println("SmartTV is now ON.");
        } else {
            System.out.println("SmartTV is already ON.");
        }
    }

    @Override
    public void switchOff() {
        if (isSoundActive) {
            isSoundActive = false;
            System.out.println("SmartTV is now OFF.");
        } else {
            System.out.println("SmartTV is already OFF.");
        }
    }

    @Override
    public void togglePower() {
        if (isSoundActive) {
            switchOff();
        } else {
            switchOn();
        }
    }

    @Override
    public boolean checkPowerStatus() {
        return isSoundActive;
    }

    // NetworkConnected methods
    @Override
    public boolean checkConnectionStatus() {
        if (isWifiEnabled && connectedNetwork != null) {
            System.out.println("Connected to network: " + connectedNetwork);
            return true;
        } else {
            System.out.println("Not connected to any network.");
            return false;
        }
    }

    @Override
    public void enableWifi() {
        if (!isWifiEnabled) {
            isWifiEnabled = true;
            System.out.println("Wi-Fi enabled.");
        } else {
            System.out.println("Wi-Fi is already enabled.");
        }
    }

    @Override
    public void disableWifi() {
        if (isWifiEnabled) {
            isWifiEnabled = false;
            connectedNetwork = null; // Disconnect from the current network
            System.out.println("Wi-Fi disabled.");
        } else {
            System.out.println("Wi-Fi is already disabled.");
        }
    }

    @Override
    public void connectToNetwork(String network) {
        if (isWifiEnabled) {
            connectedNetwork = network;
            System.out.println("Connected to network: " + network);
        } else {
            System.out.println("Wi-Fi is disabled. Enable Wi-Fi to connect to a network.");
        }
    }

    @Override
    public void reconnect() {
        if (isWifiEnabled && connectedNetwork != null) {
            System.out.println("Reconnecting to network: " + connectedNetwork);
        } else {
            System.out.println("No previously connected network or Wi-Fi is disabled.");
        }
    }




}
