package Interfaces;

public interface AudioControl {
    void enableAudio();
    void disableAudio();
    void setVolume(int volume);
    int getVolume();
    void increaseVolume(int amount);
    void decreaseVolume(int amount);
    void mute();
    void unmute();
    boolean checkMuteStatus();
}