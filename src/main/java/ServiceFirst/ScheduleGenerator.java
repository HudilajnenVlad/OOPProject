package ServiceFirst;

import ServiceFirst.Ship.Ship;
import ServiceFirst.Ship.ShipGenerator;

import java.util.Comparator;
import java.util.TreeSet;

public class ScheduleGenerator {
    final int MAXCOUNTSHIPS = 100;

    public ScheduleGenerator() {
        schedule = new TreeSet<Ship>(new Comparator<Ship>() {
            @Override
            public int compare(Ship o1, Ship o2) {
                int time1 = o1.getDay() * 24 * 60 + o1.getHours() * 60 + o1.getMinutes();
                int time2 = o2.getDay() * 24 * 60 + o2.getHours() * 60 + o2.getMinutes();
                return Integer.compare(time1, time2);
            }
        });
    }

    public void generateSchedule() {
        ShipGenerator generator = new ShipGenerator();
        for (int i = 0; i < MAXCOUNTSHIPS; i++) {
            schedule.add(generator.generateShip());
        }
    }

    public TreeSet<Ship> getSchedule() {
        return schedule;
    }

    TreeSet<Ship> schedule;
}

