package ServiceFirst.entity.Ship;


import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ShipGenerator {
    private final int BULK_PERFOMANCE = 200;
    private final int LIQUID_PERFOMANCE = 200;
    private final int CONTAINER_PERFOMANCE = 60;
    private final int MAX_WEIGHT = 228000;
    private final int MIN_WEIGHT = 1;
    private final int MIN_CONTAINER = 1;
    private final int MAX_CONTAINER = 4000;
    private final int COUNT_OF_DAY = 30;
    private final int COUNT_OF_HOURS = 24;
    private final int COUNT_OF_MINUTES = 60;
    private Random random;


    public ShipGenerator() {
        random = new Random();
    }

    public Ship generateShip() {
        //Имя корабля состоит из 5 символов - 2 заглавные буквы латинского алфавита и 3 десятичные цифры
        String name = generateName();
        int day = random.nextInt(COUNT_OF_DAY) + 1;
        int hours = random.nextInt(COUNT_OF_HOURS) + 1;
        int minutes = random.nextInt(COUNT_OF_MINUTES) + 1;
        TypeOfShip type = generateType();
        int weight = generateWeight(type);
        AtomicInteger time = new AtomicInteger(generateTimeOfUnloading(weight, type));
        return new Ship(name, day, hours, minutes, type, weight, time);
    }

    private String generateName() {
        int min = 65;
        int diff = 26;
        return String.valueOf((char) (min + random.nextInt(diff))) +
                (char) (min + random.nextInt(diff)) +
                random.nextInt(10) +
                random.nextInt(10) +
                random.nextInt(10);
    }

    /*public Calendar generateDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, random.nextInt(30) + 1);
        calendar.add(Calendar.HOUR, random.nextInt(24) + 1);
        calendar.add(Calendar.MINUTE, random.nextInt(60) + 1);
        return calendar;
    }
    */


    private TypeOfShip generateType() {
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                return TypeOfShip.BULK;
            case 1:
                return TypeOfShip.LIQUID;
            case 2:
                return TypeOfShip.CONTAINER;
        }
        return null;
    }

    private int generateWeight(TypeOfShip type) {
        if (type.equals(TypeOfShip.BULK) || type.equals(TypeOfShip.LIQUID)) {
            return random.nextInt(MAX_WEIGHT - MIN_WEIGHT) + MIN_WEIGHT;
        } else if (type.equals(TypeOfShip.CONTAINER)) {
            return random.nextInt(MAX_CONTAINER - MIN_CONTAINER) + MIN_CONTAINER;
        }
        return 0;
    }

    private int generateTimeOfUnloading(int weight, TypeOfShip type) {
        if (type.equals(TypeOfShip.BULK)) {
            return (weight / BULK_PERFOMANCE) * COUNT_OF_MINUTES;
        } else if (type.equals(TypeOfShip.LIQUID)) {
            return (weight / LIQUID_PERFOMANCE) * COUNT_OF_MINUTES;
        } else if (type.equals(TypeOfShip.CONTAINER)) {
            return (weight / CONTAINER_PERFOMANCE) * COUNT_OF_MINUTES;
        }
        return 0;
    }

    public Ship generateShipWithParametrs(String name, int day, int hours, int minutes, TypeOfShip type, int weight)
    {
        return new Ship(name, day, hours, minutes, type, weight, new AtomicInteger(generateTimeOfUnloading(weight, type)));
    }
}
