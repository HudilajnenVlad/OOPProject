package ServiceThird;

import ServiceFirst.Ship.Ship;

import java.util.TreeSet;

public class Filter {
    public void filterOut(TreeSet<Ship> schedule, int day) {
        int lastDay = schedule.first().getDay() + day;
        schedule.removeIf(i -> i.getDay() > lastDay);
    }
}
