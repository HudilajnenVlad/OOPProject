package ServiceSecond;

import entity.Schedule;
import entity.Ship.Ship;
import entity.Ship.ShipGenerator;
import entity.Ship.TypeOfShip;
import lombok.Setter;

import java.util.Scanner;

public class ServiceSecond {
    @Setter
    Schedule serviceFirst;
    Scanner scanner = new Scanner(System.in);

    public ServiceSecond() {
        serviceFirst = new Schedule();
        serviceFirst.generateSchedule();
        serviceFirst.printSchedule();

        System.out.println("Do you want to add new ship? y/n");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to add new ship? y/n");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")) {
            serviceFirst.getSchedule().add(addShip());
        }
        serviceFirst.printSchedule();
    }

    private Ship addShip() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter arrival in [0; 30]: ");
        int day = scanner.nextInt();
        while (day > 30 || day < 0) {
            System.out.println("Enter correct arrival day in [0; 30]: ");
            day = scanner.nextInt();
        }
        System.out.println("Enter arrival hours in [0;24) ");
        int hours = scanner.nextInt();
        while (hours > 24 || hours < 0) {
            System.out.println("Enter correct arrival hours in [0;24) ");
            hours = scanner.nextInt();
        }
        System.out.println("Enter arrival minutes in [0;60) ");
        int minutes = scanner.nextInt();
        while (minutes > 59 || minutes < 0) {
            System.out.println("Enter correct arrival minutes in [0;60) ");
            minutes = scanner.nextInt();
        }
        System.out.println("Enter type of Ship (c for CONTAINER/ b for BULK/ l for LIQUID) ");
        String type = scanner.nextLine();
        TypeOfShip typeOfShip;
        while (true)
        {
            if (type.equals("c"))
            {
                typeOfShip = TypeOfShip.CONTAINER;
                break;
            }
            else if (type.equals("b"))
            {
                typeOfShip = TypeOfShip.BULK;
                break;
            } else if (type.equals("l")) {
                typeOfShip = TypeOfShip.LIQUID;
                break;
            }
            else {
                System.out.println("Enter correct type of Ship (c for CONTAINER/ b for BULK/ l for LIQUID) ");
                type = scanner.nextLine();
            }
        }
        System.out.println("Enter weight >0");
        int weight = scanner.nextInt();
        while (weight<0)
        {
            System.out.println("Enter correct weight >0");
            weight = scanner.nextInt();
        }
        return new ShipGenerator().generateShipWithParametrs(name, day, hours, minutes, typeOfShip, weight);
    }

}
