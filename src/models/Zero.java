package models;

import java.util.LinkedList;
import java.util.List;

public class Zero implements Model {

    public List<Integer> generateTime(int n){
        LinkedList<Integer> times = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            times.add(0);
        }
        return times;
    }
}
