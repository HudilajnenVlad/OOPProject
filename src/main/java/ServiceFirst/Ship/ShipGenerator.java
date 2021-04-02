package ServiceFirst.Ship;


import java.util.Random;

public class ShipGenerator {
    private final int BULKPERFOMANCE = 200;
    private final int LIQUIDKPERFOMANCE = 200;
    private final int CONTAINERPERFOMANCE = 60;
    private final int MAXWEIGHT = 228000;
    private final int MINWEIGHT = 1;
    private final int MINCONTAINER = 1;
    private final int MAXCONTAINER = 4000;
    private final int INTERMIDATETIME = 60;
    private final int COUNTOFDAY = 30;
    private final int COUNTOFHOURS = 24;
    private final int COUNTOFMINUTES = 60;
    private Random random;


    public ShipGenerator() {
        random = new Random();
    }

    public Ship generateShip() {
        //Имя корабля состоит из 5 символов - 2 заглавные буквы латинского алфавита и 3 десятичные цифры
        String name = generateName();
        int day = random.nextInt(COUNTOFDAY) + 1;
        int hours = random.nextInt(COUNTOFHOURS) + 1;
        int minutes = random.nextInt(COUNTOFMINUTES) + 1;
        TypeOfShip type = generateType();
        int weight = generateWeight(type);
        double time = generateTimeOfUnloading(weight, type);
        return new Ship(name, day, hours, minutes, type, weight, time);
    }

    private String generateName() {
        int min = 65;
        int diff = 26;
        StringBuilder sb = new StringBuilder();
        sb.append((char) (min + random.nextInt(diff)));
        sb.append((char) (min + random.nextInt(diff)));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        return sb.toString();
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
            return random.nextInt(MAXWEIGHT - MINWEIGHT) + MINWEIGHT;
        } else if (type.equals(TypeOfShip.CONTAINER)) {
            return random.nextInt(MAXCONTAINER - MINCONTAINER) + MINCONTAINER;
        }
        return 0;
    }

    private double generateTimeOfUnloading(int weight, TypeOfShip type) {
        if (type.equals(TypeOfShip.BULK)) {
            return ((double) weight / BULKPERFOMANCE) * COUNTOFMINUTES + INTERMIDATETIME;
        } else if (type.equals(TypeOfShip.LIQUID)) {
            return ((double)weight / LIQUIDKPERFOMANCE) * COUNTOFMINUTES + INTERMIDATETIME;
        } else if (type.equals(TypeOfShip.CONTAINER)) {
            return ((double)weight / CONTAINERPERFOMANCE) * COUNTOFMINUTES + INTERMIDATETIME;
        }
        return 0;
    }
}
