package ServiceFirst;

import ServiceFirst.Ship.Ship;

import java.io.Serializable;
import java.util.TreeSet;

public class Schedule implements Serializable {
    public Schedule() {
        ScheduleGenerator generator = new ScheduleGenerator();
        generator.generateSchedule();
        schedule = generator.getSchedule();
    }

    public void printSchedule() {
        System.out.println("Name\tTime\t\tType\t\tMass\tTimeInPort");
        for (Ship i : schedule) {
            System.out.println(i.printShip());
        }
    }

    public TreeSet<Ship> getSchedule() {
        return schedule;
    }

    TreeSet<Ship> schedule;
}
