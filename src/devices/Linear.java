package devices;

public class Linear extends Device {
    private int c = 1;

    public Linear() {}

    public Linear(int c) {
        this.c = c;
    }

    @Override
    public void updateWindow() {
        window = window + c;
    }
}
