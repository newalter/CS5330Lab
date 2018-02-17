import static events.EventType.WindowEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import events.Event;
import events.EventComparator;

public class TestDrive {

    public Result test(List<Event> events) {
        int duration = 0;
        int totalTries = 0;

        PriorityQueue<Event> eventQueue = new PriorityQueue(new EventComparator());
        eventQueue.addAll(events);
        ArrayList<Event> eventsAtT = new ArrayList<>();
        while (!eventQueue.isEmpty()) {
            eventsAtT.clear();
            eventsAtT.add(eventQueue.poll());
            while (!eventQueue.isEmpty() && eventsAtT.get(0).isSameTime(eventQueue.peek())) {
                eventsAtT.add(eventQueue.poll());
            }

            int split = findSplit(eventsAtT);
            if (split == 1) {
                eventsAtT.get(0).device.isSuccessful = true;
                duration = eventsAtT.get(0).time;
                totalTries += eventsAtT.get(0).device.tries;
            }
            for (int i=split; i < eventsAtT.size(); i++) {
                Event event = eventsAtT.get(i);
                eventQueue.addAll(event.device.nextRound(event.time));
            }
        }

        return new Result(duration, totalTries);
    }

    private int findSplit(ArrayList<Event> eventsAtT) {
        for (int i = 0; i < eventsAtT.size(); i++) {
            if (eventsAtT.get(i).type == WindowEnd) {
                return i;
            }
        }
        return eventsAtT.size();
    }


}
