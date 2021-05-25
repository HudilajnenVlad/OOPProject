package ServiceThird;

import entity.Schedule;
import entity.Ship.Ship;
import entity.Ship.ShipComparator;

import java.util.Random;
import java.util.TreeSet;

public class DeviationGenerator {
    private final Random random;

    Schedule deviationSchedule;

    public Schedule getDeviationSchedule() {
        return deviationSchedule;
    }

    public DeviationGenerator() {
        this.random = new Random();
        this.deviationSchedule = new Schedule();
    }

    public void generateDeviation(Schedule schedule) {
        TreeSet<Ship> newSchedule = new TreeSet<>(new ShipComparator());
        for (Ship i : schedule.getSchedule()) {
            Ship ship = new Ship(i);
            int maxDeviationOfDay = 7;
            int minDeviationOfDay = -7;
            ship.setDay(i.getDay() + random.nextInt(maxDeviationOfDay - minDeviationOfDay + 1) - 7);
            int maxDeviationOnUploadingTime = 1440;
            ship.setDelay(random.nextInt(maxDeviationOnUploadingTime));
            newSchedule.add(ship);
        }
        deviationSchedule.setSchedule(newSchedule);
        int lastDay = schedule.getSchedule().first().getDay() + 30;
        schedule.getSchedule().removeIf(i -> i.getDay() > lastDay);
    }
}
