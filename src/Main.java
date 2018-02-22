import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import devices.Backon;
import devices.BackonPrime;
import devices.Device;
import devices.Exp;
import devices.Linear;
import devices.Log;
import devices.LogLog;
import devices.Polynomial;
import devices.Sqrt3t;
import models.Model;
import models.Zero;
import testDrives.HalvesTestDrive;
import testDrives.InterruptionTestDrive;
import testDrives.OneShotTestDrive;
import testDrives.Result;
import testDrives.TestDrive;

public class Main {

    public static TestDrive testDrive;
    public static Model model;
    public static Device device;
    public static String header;
    private static int Total;
    private static String path;
    private static int[] number = {10, 20, 50, 100, 200, 500, 1000, 10000};

    public static void main(String args[]){
        setParameters();
        int duration; double tries;
        StringBuilder output = new StringBuilder();
        output.append(header);
        //int n = 11000;
        for (int j = 0; j< 8; j++) {
            int n = number[j];
            int iterations =  100;
            duration = 0;
            tries = 0;
            for (int i = 0; i < iterations; i++) {
                Result result = testDrive.test(n, model);
                duration += result.getDuration();
                tries += (double) result.getTotalTries() / model.getTotalNum() - 1;
            }
            output.append(","); output.append((double) duration / iterations); output.append(",");output.append(tries / iterations);
        }
        output.append("\n");
        writeToFile(output.toString());
        System.out.println(output.toString());
        System.out.println(model.getTotalNum());
    }

    /**
     * Manual parameter setting
     */
    private static void setParameters() {
        testDrive = new InterruptionTestDrive();
        device = new Linear(5);
        model = new Zero(device);
        header = "Linear(5)";
        Total = 13000;
        path = "C:\\Users\\Walter\\Documents\\NUS\\Computing\\CS5330\\lab\\Data.csv";
    }

    /**
     * Writes the output to a csv file specified in path
     * @param output
     */
    private static void writeToFile(String output) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(output);
            writer.close();
        } catch (IOException e) {
        }

    }
}
