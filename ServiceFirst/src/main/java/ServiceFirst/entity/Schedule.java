package ServiceFirst.entity;


import ServiceFirst.entity.Ship.Ship;
import ServiceFirst.entity.Ship.ShipComparator;
import ServiceFirst.entity.Ship.ShipGenerator;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeSet;

public class Schedule implements Serializable {
    TreeSet<Ship> schedule;

    public Schedule(int n) {
        generateSchedule(n);
    }

    public Schedule() {
        this.schedule = new TreeSet<>(new ShipComparator());
    }
    public Schedule(TreeSet<Ship> schedule) {
        this.schedule = schedule;
    }
    public Schedule(Ship[] schedule) {
        this.schedule = new TreeSet<Ship>(new ShipComparator());
        this.schedule.addAll(Arrays.asList(schedule));
    }

    public void printSchedule() {
        System.out.println("Name\tTime\t\tType\t\tMass\tTimeInPort");
        for (Ship i : schedule) {
            System.out.println(i.printShip());
        }
    }

    public String stringSchedule() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name\tTime\t\tType\t\tMass\tTimeInPort");
        for (Ship i : schedule) {
            sb.append(i.printShip());
        }
        return sb.toString();
    }

    public void setSchedule(TreeSet<Ship> schedule) {
        this.schedule = schedule;
    }

    public TreeSet<Ship> getSchedule() {

        return schedule;
    }

    public void generateSchedule(int countOfShips)
    {
        this.schedule = new TreeSet<>(new ShipComparator());
        ShipGenerator generator = new ShipGenerator();
        for (int i = 0; i < countOfShips; i++) {
            schedule.add(generator.generateShip());
        }
    }

    public void generateSchedule()
    {
        final int MAX_COUNT_SHIPS = 100;
        generateSchedule(MAX_COUNT_SHIPS);
    }

    public Schedule getCopy()
    {
        Schedule schedule = new Schedule();
        for (Ship s: this.schedule) {
            schedule.schedule.add(new Ship(s));
        }
        return schedule;
    }
    public String toJSON()
    {
        Gson gson = new Gson();
        return gson.toJson(schedule);
    }
}
