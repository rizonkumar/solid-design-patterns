package DesignPatterns.BehaviouralPatterns.OLD.ObserverPattern.GoodCode;

import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(float temp);
}

// Subject Interface
interface Subject {
    void attach(Observer obs);

    void detach(Observer obs);

    void notifyObservers();
}

// Weather Station India (Publisher)
class WeatherStationIn implements Subject {
    private final List<Observer> observerList;
    private float temperature;

    WeatherStationIn() {
        observerList = new ArrayList<>();
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers(); // notify all observers
    }

    @Override
    public void attach(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void detach(Observer obs) {
        observerList.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observerList) {
            obs.update(temperature);
        }
    }
}

// Concrete Observer
class DisplayDeviceIn implements Observer {
    private final String name;

    public DisplayDeviceIn(String deviceName) {
        this.name = deviceName;
    }

    @Override
    public void update(float temp) {
        System.out.println("Temp on " + name + " is " + temp);
    }
}

// Another Concrete Observer
class MobileDevice implements Observer {
    @Override
    public void update(float temp) {
        System.out.println("Temp on Mobile is " + temp);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        WeatherStationIn weatherStationIn = new WeatherStationIn();

        DisplayDeviceIn device = new DisplayDeviceIn("SamsungLCD");
        MobileDevice mobileDevice = new MobileDevice();

        weatherStationIn.attach(device);
        weatherStationIn.attach(mobileDevice);

        weatherStationIn.setTemperature(25);
    }
}