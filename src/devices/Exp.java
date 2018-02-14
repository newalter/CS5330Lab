package devices;

public class Exp extends Device {
    private double _alpha = Math.E;

    private double windowFloat = 1;

    public Exp() {}

    public Exp(double alpha) {
        _alpha = alpha;
    }

    @Override
    public void updateWindow() {
        windowFloat = windowFloat * _alpha;
        window = (int) windowFloat;
    }
}
