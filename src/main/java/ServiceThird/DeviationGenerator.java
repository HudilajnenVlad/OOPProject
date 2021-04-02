package ServiceThird;

import ServiceFirst.Ship.Ship;

import java.util.Random;
import java.util.TreeSet;

public class DeviationGenerator {
    private Random random;

    public DeviationGenerator() {
        this.random = new Random();
    }

    public void generateDeviation(TreeSet<Ship> schedule) {
        for (Ship i : schedule) {
            int MAXDEVIATIONOFDAY = 7;
            int MINDEVIATIONOFDAY = -7;
            i.setDay(i.getDay() + random.nextInt(MAXDEVIATIONOFDAY - MINDEVIATIONOFDAY + 1) - 7);
            int MAXDEVIATIONOFUPLOADINGTIME = 1440;
            i.setTime(i.getTime() + random.nextInt(MAXDEVIATIONOFUPLOADINGTIME));
        }
    }
}
