package src.test.java;

import org.junit.Test;

import Devices.SmartSpeaker;

public class TempTest {
    @Test
    public void CheckGetVolume(){
        SmartSpeaker a = new SmartSpeaker();
        a.getVolume();
    }
}
