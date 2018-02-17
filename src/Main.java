import static events.EventType.WindowEnd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import devices.BinaryExp;
import devices.Device;
import events.Event;
import models.Model;
import models.Zero;

public class Main {

    public static TestDrive testDrive;
    public static Model model;
    public static Device device;

    private static int Total = 10000;

    public static void main(String args[]){
        int duration; double tries;
        StringBuilder output = new StringBuilder("Backon'");
        setParameters();

        for (int n = 10; n<= 100000; n = n * 10) {
            int iterations = Math.max(Total / n, 5);
            duration = 0;
            tries = 0;
            for (int i = 0; i < iterations; i++) {
                Result result = testDrive.test(initialise(model.generateTime(n)));
                duration += result.getDuration();
                tries += (double) result.getTotalTries() / n - 1;
            }
            output.append(","); output.append((double) duration / iterations); output.append(",");output.append(tries / iterations);
        }
        output.append("\n");
        //writeToFile(output.toString());
        System.out.println(output.toString());
    }

    private static void setParameters() {
        testDrive = new TestDrive();
        model = new Zero();
        device = new BinaryExp();
    }

    public static List<Event> initialise(List<Integer> times){
        LinkedList<Event> events = new LinkedList<>();
        for (int i = 0; i < times.size(); i++) {
            Device newDevice = device.clone();
            events.add(new Event(times.get(i), newDevice, WindowEnd));
        }
        return events;
    }

    private static void writeToFile(String s) {
        String path = "C:\\Users\\Walter\\Documents\\NUS\\Computing\\CS5330\\lab\\Data.csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(s);
            writer.close();
        } catch (IOException e) {
        }

    }
}
