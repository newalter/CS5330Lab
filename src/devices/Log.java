package devices;

public class Log extends Device {
    private double windowFloat = 1;

    @Override
    public void updateWindow() {
        windowFloat = windowFloat * (1 + 1 / (log2(windowFloat)));
        window = (int) windowFloat;
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}
