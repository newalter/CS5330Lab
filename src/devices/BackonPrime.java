package devices;

public class BackonPrime extends Device {

    private int k = 1;
    private int t = 1;
    @Override
    public void updateWindow() {
        if (t < 1.77 * (int) log2(k) && window > 1) {
            window = window / 2;
            t++;
        } else {
            window = (int) Math.pow(2, k);
            k++;
            t = 1;
        }
    }
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}
