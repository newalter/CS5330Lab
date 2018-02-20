package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import devices.Device;
import events.Event;
import events.EventType;

public class Gaussian extends Model {

    public Gaussian( Device device){
        super(device);
    }

    public List<Event> newArrivals(int startTime, int endTime){
        LinkedList<Event> events = new LinkedList<>();
        Random r = new Random();
        for (int time = startTime + 1; time <= endTime; time++) {
            int num = (int)r.nextGaussian();
            if (num > 0) {
                for (int i = 0; i < num; i++)
                    events.add(new Event(time, newDevice(), EventType.WindowEnd));
                totalNum+= num;
                break;
            }
        }
        return events;
    }
}
