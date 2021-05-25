package entity.Ship;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Setter
public class Ship {
    String name;
    private int day;
    private int hours;
    private int minutes;
    private TypeOfShip type;
    private int weight;
    private AtomicInteger unloadingTime;
    private int delay;

    public Ship(String name, int day, int hours, int minutes, TypeOfShip type, int weight, AtomicInteger time) {
        this.name = name;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.type = type;
        this.weight = weight;
        this.unloadingTime = time;
    }

    public Ship(Ship ship) {
        this.name = ship.getName();
        this.day = ship.getDay();
        this.hours = ship.getHours();
        this.minutes = ship.getMinutes();
        this.type = ship.getType();
        this.weight = ship.getWeight();
        this.unloadingTime = new AtomicInteger(ship.getUnloadingTime().get());
        this.delay = getDelay();
    }

    public String printShip() {
        return name + "\t" +
                ((day < 10) ? '0' : "") + day + ':' +
                ((hours < 10) ? '0' : "") + hours + ':' +
                ((minutes < 10) ? '0' : "") + minutes + '\t' +
                type + "\t" + (type.equals(TypeOfShip.CONTAINER) ? "" : "\t") +
                weight + "\t" + ((weight < 1000) ? "\t" : "") +
                unloadingTime;
    }


    public void decrementTime() {
        unloadingTime.decrementAndGet();
    }

    public int getTime() {
        return day * 24 * 60 + hours * 60 + minutes;
    }
}
