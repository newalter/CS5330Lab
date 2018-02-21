package models;

import static events.EventType.WindowEnd;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import devices.Device;
import events.Event;
import events.EventType;

public class SqrtNew extends Model {

    private double _lambda;
    private LinkedList<Event> events = new LinkedList<>();
    
    public SqrtNew(double lambda, Device device){
        super(device);
        _lambda = lambda;
    }

    public List<Event> newArrivals(int startTime, int endTime){
        return events;
    }

    public List<Event> initialise(int n) {
        this.n = n;
        totalNum = n;
        double p = 1/ Math.sqrt(n);
        int batchSize = (int) Math.sqrt(n);
        Random r = new Random();
        LinkedList<Event> events = new LinkedList<>();
        int time = 0;
        while (n > 0) {
            if (r.nextDouble() < p) {
                for (int i = 0; i < Math.min(n, batchSize); i++) {
                    events.add(new Event(time, newDevice(), EventType.WindowEnd));
                }
                n -= batchSize;
            }else if (r.nextDouble() < _lambda) {
                events.add(new Event(time, newDevice(), EventType.WindowEnd));
                n--;
            }
            time++;
        }
        return events;
    }
}
