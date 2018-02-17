package devices;

public class Polynomial extends Device {

    private int _alpha = 2;
    private int k = 1;

    public Polynomial() {}
    public Polynomial(int alpha) {
        _alpha = alpha;
    }

    @Override
    public void updateWindow() {
        window = (int) Math.pow(tries + 1, _alpha);
    }
}
