package devices;

import static events.EventType.Claim;
import static events.EventType.WindowEnd;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import events.Event;

public abstract class Device {

    public static Random random = new Random();

    public int window = 1;
    public int tries = 0;
    public boolean isSuccessful = false;
    public abstract void updateWindow();

    public List<Event> nextRound(int time) {
        List<Event> events = new LinkedList<>();
        if (isSuccessful) return events;
        events.add(new Event(time + 1 + random.nextInt(window), this, Claim));
        events.add(new Event(time + window, this, WindowEnd));

        tries++;
        updateWindow();
        return events;
    }
}
