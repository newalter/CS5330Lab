package devices;

public class BinaryExp extends Device {
    @Override
    public void updateWindow() {
        window = window * 2;
    }
}
