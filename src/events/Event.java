package events;

import devices.Device;

public class Event {

    public int time;
    public Device device;
    public EventType type;

    public Event(int time, Device device, EventType type) {
        this.time = time;
        this.device = device;
        this.type = type;
    }

    public boolean isSameTime(Event other) {
        return this.time == other.time;
    }
}
