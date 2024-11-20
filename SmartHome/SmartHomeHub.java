import Devices.SmartThermostat;
import Devices.SmartSpeaker;
import Devices.SmartTV;

public class SmartHomeHub {
    public static void main(String[] args) {
        SmartThermostat thermostat = new SmartThermostat();
        SmartSpeaker speaker = new SmartSpeaker();
        SmartTV tv = new SmartTV();

        thermostat.switchOn();
        thermostat.enableWifi();
        thermostat.setTemperature(24.5);

        speaker.switchOn();
        speaker.enableWifi();
        speaker.setVolume(50);
        speaker.mute();

        tv.switchOn();
        tv.enableWifi();
        tv.setVolume(30);
        tv.connectToNetwork("Home_Network");

        thermostat.checkPowerStatus();
        thermostat.checkConnectionStatus();
        thermostat.getTemperature();

        speaker.checkPowerStatus();
        speaker.checkConnectionStatus();
        speaker.getVolume();
        speaker.checkMuteStatus();

        tv.checkPowerStatus();
        tv.checkConnectionStatus();
        tv.getVolume();
    }
}
