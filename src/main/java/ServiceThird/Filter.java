package ServiceThird;

import ServiceFirst.Schedule;
import ServiceFirst.Ship.Ship;

import java.util.TreeSet;

public class Filter {
    public void filterOut(Schedule schedule, int day) {
        int lastDay = schedule.getSchedule().first().getDay() + day;
        schedule.getSchedule().removeIf(i -> i.getDay() > lastDay);
    }
}
