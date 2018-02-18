package models;

import java.util.LinkedList;
import java.util.List;

import devices.Device;
import events.Event;

public class Zero extends Model {

    public Zero(Device device) {
        super(device);
    }

    public List<Event> newArrivals(int startTime, int endTime){
        return new LinkedList<>();
    }
}
