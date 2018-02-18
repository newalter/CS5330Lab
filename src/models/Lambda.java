package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import devices.Device;
import events.Event;
import events.EventType;

public class Lambda extends Model {

    private double _lambda;
    public Lambda(double lambda, Device device){
        super(device);
        _lambda = lambda;
    }

    public List<Event> newArrivals(int startTime, int endTime){
        LinkedList<Event> events = new LinkedList<>();
        Random r = new Random();
        for (int time = startTime + 1; time <= endTime; time++) {
            if (r.nextDouble() < _lambda) {
                events.add(new Event(time, newDevice(), EventType.WindowEnd));
            }
        }
        return events;
    }
}
