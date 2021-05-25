package ServiceThird;

import entity.Ship.Ship;
import lombok.Getter;

public class Worker {
    @Getter
    private Ship ship;

    @Getter
    private Crane[] cranes = new Crane[2];
    private int countOfCranes = 0;
    @Getter
    private int startTime;

    public Worker(Ship ship, int startTime) {
        this.ship = ship;
        this.startTime = startTime;
    }

    public void addCrane(Crane crane) {
        cranes[0] = crane;
        cranes[0].setShip(ship);
        countOfCranes = 1;
    }

    public void addCrane(Crane crane1, Crane crane2) {
        cranes[0] = crane1;
        cranes[1] = crane2;
        cranes[0].setShip(ship);
        cranes[1].setShip(ship);

        countOfCranes = 2;
    }

    public synchronized boolean checkWorkEnded(int time) {
        switch (countOfCranes) {
            case 1:
                if (cranes[0].getShip() == null && cranes[0].getTime().get() + startTime <= time) {
                    return true;
                }
                break;
            case 2:
                if (cranes[0].getShip() == null && cranes[1].getShip() == null
                        && (cranes[0].getTime().get()+cranes[1].getTime().get())/2 + startTime <= time) {
                    return true;
                }
                break;
        }
        return false;
    }

}

