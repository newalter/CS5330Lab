package testDrives;

import static events.EventType.WindowEnd;

import java.util.ArrayList;
import java.util.PriorityQueue;

import events.Event;
import events.EventComparator;
import models.Model;

public class OneShotTestDrive extends TestDrive{

    public Result test(int n, Model model) {
        int duration = 0;
        int totalTries = n;

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
            if (split == 1) {
                duration = currentTime;
                break;
            }
            for (int i=split; i < eventsAtT.size(); i++) {
                Event event = eventsAtT.get(i);
                eventQueue.addAll(event.device.nextRound(currentTime));
                totalTries++;
            }
            if (!eventQueue.isEmpty()) {
                eventQueue.addAll(model.newArrivals(currentTime, eventQueue.peek().time));
            }
        }

        return new Result(duration, totalTries);
    }

    protected int findSplit(ArrayList<Event> eventsAtT) {
        for (int i = 0; i < eventsAtT.size(); i++) {
            if (eventsAtT.get(i).type == WindowEnd) {
                return i;
            }
        }
        return eventsAtT.size();
    }


}
