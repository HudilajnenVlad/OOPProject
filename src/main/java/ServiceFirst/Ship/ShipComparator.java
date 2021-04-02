package ServiceFirst.Ship;

import java.util.Comparator;

public class ShipComparator implements Comparator<Ship> {
    public int compare(Ship o1, Ship o2) {
        int time1 = o1.getDay() * 24 * 60 + o1.getHours() * 60 + o1.getMinutes();
        int time2 = o2.getDay() * 24 * 60 + o2.getHours() * 60 + o2.getMinutes();
        return Integer.compare(time1, time2);
    }
}
