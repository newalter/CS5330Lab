package testDrives;

import static events.EventType.WindowEnd;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import devices.Device;
import events.Event;
import events.EventComparator;
import models.Model;

public class InterruptionTestDrive extends TestDrive{
    private int k;
    private Device workingDevice;
    private int successTime;
    private boolean isWorking = false;


    public Result test(int n, Model model) {
        int duration = 0;
        int totalTries = 0;
        Random r = new Random();
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

            if (isWorking && successTime< currentTime) {
                workingDevice.isSuccessful = true;
                isWorking = false;
                duration = successTime;
            }
            if (split == 1) {
                workingDevice = eventsAtT.get(0).device;
                isWorking = true;
                k = (int) Math.abs(2 * r.nextGaussian());
                successTime = currentTime + k;
            }
            if (split >= 2) {
                isWorking = false;
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
