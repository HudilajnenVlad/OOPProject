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

    public String printShip() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\t");
        sb.append((day < 10) ? '0' : "").append(day).append(':');
        sb.append((hours < 10) ? '0' : "").append(hours).append(':');
        sb.append((minutes < 10) ? '0' : "").append(minutes).append('\t');
        sb.append(type).append("\t").append((type.equals(TypeOfShip.CONTAINER) ? "" : "\t"));
        sb.append(weight).append("\t").append((weight < 1000) ? "\t" : "");
        sb.append(time);
        return sb.toString();
    }

}
