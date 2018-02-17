package devices;

public class Backon extends Device {

    private int k = 1;

    @Override
    public void updateWindow() {
        if (window > 1) {
            window = window / 2;
        } else {
            window = (int) Math.pow(2, k);
            k++;
        }
    }
}
