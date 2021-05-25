package ServiceThird;

import entity.Ship.Ship;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

public class Crane extends Thread {

    private Ship ship;
    @Setter
    public Worker worker;

    @Getter
    private AtomicInteger time = new AtomicInteger(0);

    @Override
    public void run() {
        while (!isInterrupted()) {
            work();
        }
    }

    private void work() {
        if (ship != null) {
            if (ship.getUnloadingTime().get() > 0){
                time.incrementAndGet();
                ship.decrementTime();
            }else {
                ship = null;
            }
        }
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        reset();
        this.ship = ship;
    }

    public void reset() {
        this.time.set(0);
    }
}
