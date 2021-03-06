package models;

import static events.EventType.WindowEnd;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import devices.Device;
import events.Event;
import events.EventType;

public class Sqrt extends Model {

    private double _lambda;
    public Sqrt(double lambda, Device device){
        super(device);
        _lambda = lambda;
    }

    public List<Event> newArrivals(int startTime, int endTime){
        double p = 1/ Math.sqrt(n);
        int batchSize = (int) Math.sqrt(n);

        LinkedList<Event> events = new LinkedList<>();
        Random r = new Random();
        for (int time = startTime + 1; time <= endTime; time++) {
            if (r.nextDouble() < p) {
                for (int i = 0; i < batchSize; i++) {
                    events.add(new Event(time, newDevice(), EventType.WindowEnd));
                }
                totalNum+= batchSize;
                break;
            } else if (r.nextDouble() < _lambda) {
                events.add(new Event(time, newDevice(), EventType.WindowEnd));
                totalNum++;
                break;
            }
        }
        return events;
    }

    public List<Event> initialise(int n) {
        this.n = n;
        totalNum = 2;
        LinkedList<Event> events = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
            events.add(new Event(0, newDevice(), WindowEnd));
        }
        return events;
    }
}
