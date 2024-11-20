Assignment 2: Designing a Smart Home System

PROBLEM STATEMENT: Create a smart home system that demonstrates the use of interfaces for multiple inheritance and polymorphism in Java. 

The system will include various smart devices that can be controlled and monitored.Requirements 
Create the following interfaces:
PowerControl: Methods for turning devices on and off
NetworkConnected: Methods for connecting to WiFi and checking connection status
TemperatureControl: Methods for setting and getting temperature
AudioControl: Methods for adjusting volume and muting

Implement the following classes:
SmartThermostat: Implements PowerControl, NetworkConnected, and TemperatureControl
SmartSpeaker: Implements PowerControl, NetworkConnected, and AudioControl
SmartTV: Implements all four interfaces
Create a SmartHomeHub class that can control all types of smart devices

Implement a main program that demonstrates the use of these classes and interfaces
And one more addition is you guys have to write unit tests for each class and interface to ensure proper functionality.

Interfaces:

PowerControl : SwitchOn() , SwitchOff() , togglePower() , CheckPowerStatus() 

NetworkConnected : CheckConnectionStatus() , EnableWifi() , DisableWifi(), connectToNetwork() , reconnect() ,

TemperatureControl : SetTemperature() , GetTemperature() , increaseTemp() , decreaseTemp() ,

AudioControl : EnableAudio() , disableAudio() ,setVolume() , getVolume() , increaseVolume() , decreaseVolume() , mute() , umute() 


Classes:

SmartThermostat - 
SmartSpeaker
SmartTV

