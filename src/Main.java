import static events.EventType.WindowEnd;

import java.util.LinkedList;
import java.util.List;

import devices.*;
import events.Event;

public class Main {

    private static final int NUM_DEVICES = 1000;

    public static void main(String args[]){
        TestDrive testDrive = new TestDrive();
        Result result = testDrive.test(initialise());
        System.out.println(result.getDuration());
        System.out.println(result.getTotalTries()/ NUM_DEVICES);
    }

    private static List<Event> initialise(){
        LinkedList<Event> events = new LinkedList<>();
        for (int i = 0; i < NUM_DEVICES; i++) {
            Device device = new Exp();
            events.add(new Event(0, device, WindowEnd));
        }
        return events;
    }
}
