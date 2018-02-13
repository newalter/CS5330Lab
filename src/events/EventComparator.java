package events;

import static events.EventType.Claim;

import java.util.Comparator;

public class EventComparator implements Comparator<Event>{
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.time == e2.time) {
            return e1.type == Claim ? -1 : 1;
        } else {
            return Integer.compare(e1.time, e2.time);
        }
    }
}
