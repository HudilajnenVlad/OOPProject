package ServiceFirst;

import ServiceFirst.Ship.Ship;

import java.io.Serializable;
import java.util.TreeSet;

public class Schedule implements Serializable {
    TreeSet<Ship> schedule;

    public Schedule() {
    }

    public Schedule(TreeSet<Ship> schedule) {
        this.schedule = schedule;
    }

    public void printSchedule() {
        System.out.println("Name\tTime\t\tType\t\tMass\tTimeInPort");
        for (Ship i : schedule) {
            System.out.println(i.printShip());
        }
    }

    public void setSchedule(TreeSet<Ship> schedule) {
        this.schedule = schedule;
    }

    public TreeSet<Ship> getSchedule() {

        return schedule;
    }
}
