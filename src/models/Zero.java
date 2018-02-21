package models;

import java.util.LinkedList;
import java.util.List;

import devices.Device;
import events.Event;

public class Zero extends Model {
    private LinkedList<Event> events = new LinkedList<>();

    public Zero(Device device) {
        super(device);

    }

    public List<Event> newArrivals(int startTime, int endTime){
        return events;
    }
}
