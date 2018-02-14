public class Result {
    private int duration;
    private int totalTries;

    public Result(int duration, int totalTries) {
        this.duration = duration;
        this.totalTries = totalTries;
    }

    public int getDuration() {
        return duration;
    }

    public int getTotalTries() {
        return totalTries;
    }
}
