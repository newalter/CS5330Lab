import java.util.LinkedList;
import java.util.List;

import devices.BinaryExp;
import devices.Device;
import events.Event;

public class Main {

    public static int numDevices = 1000;
    public static LinkedList<Device> devices = new LinkedList<>();
    public static void main(String args[]){
        TestDrive testDrive = new TestDrive();
        int duration = testDrive.test(initialise());
        System.out.println(duration);
    }

    public static List<Event> initialise(){
        LinkedList<Event> events = new LinkedList<>();
        for (int i = 0; i < numDevices; i++) {
            Device device = new BinaryExp();
            devices.addLast(device);
            events.add(new Event(0, device, Event.EventType.WindowEnd));
        }
        return events;
    }
}
