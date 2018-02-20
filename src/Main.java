import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import devices.*;
import models.*;
import models.Sqrt;

public class Main {

    public static TestDrive testDrive;
    public static Model model;
    public static Device device;
    public static String header;
    private static int Total;
    private static String path;

    public static void main(String args[]){
        setParameters();
        int duration; double tries;
        StringBuilder output = new StringBuilder();
        output.append(header);

        for (int n = 10; n<= 10; n = n * 10) {
            int iterations = Math.max(Total / n, 5);
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
        //writeToFile(output.toString());
        System.out.println(output.toString());
    }

    private static void setParameters() {
        testDrive = new TestDrive();
        device = new Sqrt3t();
        model = new Sqrt(0.0001, device);
        header = "Sqrt";
        Total = 10;
        path = "C:\\Users\\Walter\\Documents\\NUS\\Computing\\CS5330\\lab\\Lambda.csv";
    }


    private static void writeToFile(String s) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(s);
            writer.close();
        } catch (IOException e) {
        }

    }
}
