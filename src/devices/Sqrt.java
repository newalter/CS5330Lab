package devices;

public class Sqrt extends Device {
    private final double _alpha = Math.E;

    private double windowFloat = 1;
    private int sqrtT = 0;

    @Override
    public void updateWindow() {
        if (sqrtT < Math.sqrt(3 * tries)) {
            sqrtT++;
            windowFloat = windowFloat * _alpha;
            window = (int) windowFloat;
        }
    }
}
