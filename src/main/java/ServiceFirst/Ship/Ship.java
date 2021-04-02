package ServiceFirst.Ship;

import lombok.Data;

import java.io.Serializable;


@Data
public class Ship implements Serializable {


    String name;
    //Calendar date;
    private int day;
    private int hours;
    private int minutes;
    private TypeOfShip type;
    private int weight;
    private double time;

    public Ship(String name, int day, int hours, int minutes, TypeOfShip type, int weight, double time) {
        this.name = name;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.type = type;
        this.weight = weight;
        this.time = time;
    }

    public Ship(Ship ship) {
        this.name = ship.getName();
        this.day = ship.getDay();
        this.hours = ship.getHours();
        this.minutes = ship.getMinutes();
        this.type = ship.getType();
        this.weight = ship.getWeight();
        this.time = ship.getTime();
    }

    public String printShip() {
        return name + "\t" +
                ((day < 10) ? '0' : "") + day + ':' +
                ((hours < 10) ? '0' : "") + hours + ':' +
                ((minutes < 10) ? '0' : "") + minutes + '\t' +
                type + "\t" + (type.equals(TypeOfShip.CONTAINER) ? "" : "\t") +
                weight + "\t" + ((weight < 1000) ? "\t" : "") +
                time;
    }

}
