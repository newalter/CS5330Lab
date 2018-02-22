package testDrives;

import java.util.ArrayList;
import java.util.PriorityQueue;

import events.Event;
import events.EventComparator;
import models.Model;

public class CollisionTestDrive extends TestDrive {
    private int k;
    private int availableTime = -1;

    public CollisionTestDrive(int k) {
        this.k = k;
    }

    public Result test(int n, Model model) {
        int duration = 0;
        int totalTries = 0;

        PriorityQueue<Event> eventQueue = new PriorityQueue(new EventComparator());
        eventQueue.addAll(model.initialise(n));
        ArrayList<Event> eventsAtT = new ArrayList<>();
        while (!eventQueue.isEmpty()) {
            eventsAtT.clear();
            eventsAtT.add(eventQueue.poll());
            int currentTime = eventsAtT.get(0).time;
            while (!eventQueue.isEmpty() && eventsAtT.get(0).isSameTime(eventQueue.peek())) {
                eventsAtT.add(eventQueue.poll());
            }

            int split = findSplit(eventsAtT);
            if (split == 1 && currentTime > availableTime) {
                eventsAtT.get(0).device.isSuccessful = true;
                duration = currentTime;
                totalTries += eventsAtT.get(0).device.tries;
            }
            if (split >= 2) {
                availableTime = currentTime + k;
            }
            for (int i=split; i < eventsAtT.size(); i++) {
                Event event = eventsAtT.get(i);
                eventQueue.addAll(event.device.nextRound(currentTime));
            }
            if (!eventQueue.isEmpty()) {
                eventQueue.addAll(model.newArrivals(currentTime, eventQueue.peek().time));
            }
        }

        return new Result(duration, totalTries);
    }
}
