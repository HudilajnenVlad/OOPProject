package ServiceFirst;

import ServiceFirst.Ship.Ship;
import ServiceFirst.Ship.ShipComparator;
import ServiceFirst.Ship.ShipGenerator;

import java.util.Comparator;
import java.util.TreeSet;

public class ScheduleGenerator {
    final int MAXCOUNTSHIPS = 100;


    public ScheduleGenerator() {
    }

    public TreeSet<Ship> generateSchedule() {
        return generateSchedule(MAXCOUNTSHIPS);
    }

    public TreeSet<Ship> generateSchedule(int countOfShips)
    {
        TreeSet<Ship> schedule;
        schedule = new TreeSet<>(new ShipComparator());
        ShipGenerator generator = new ShipGenerator();
        for (int i = 0; i < countOfShips; i++) {
            schedule.add(generator.generateShip());
        }
        return schedule;
    }
}

