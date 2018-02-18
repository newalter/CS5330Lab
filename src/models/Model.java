package models;

import static events.EventType.WindowEnd;

import java.util.LinkedList;
import java.util.List;

import devices.Device;
import events.Event;

public abstract class Model {
    private Device device;
    protected int n;

    public Model(Device device) {
        this.device = device;
    }

    public abstract List<Event> newArrivals(int startTime, int endTime);


    public Device newDevice() {
        return device.clone();
    }

    public List<Event> initialise(int n) {
        this.n = n;
        LinkedList<Event> events = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            events.add(new Event(0, newDevice(), WindowEnd));
        }
        return events;
    }
}
