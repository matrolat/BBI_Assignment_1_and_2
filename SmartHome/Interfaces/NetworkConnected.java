package Interfaces;

public interface NetworkConnected {
    boolean checkConnectionStatus();
    void enableWifi();
    void disableWifi();
    void connectToNetwork(String network);
    void reconnect();
}
