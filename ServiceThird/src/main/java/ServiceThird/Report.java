package ServiceThird;

import entity.Ship.Ship;
import entity.Ship.TypeOfShip;
import lombok.Getter;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Report {
    ConcurrentLinkedDeque<Node> report = new ConcurrentLinkedDeque<>();
    @Getter
    private int bulkDebt;
    @Getter
    private int liquidDebt;
    @Getter
    private int containerDebt;
    private int maxDelay;
    private int bulkCranes;
    private int liquidCranes;
    private int containerCranes;


    public void addNode(Ship ship, int startUnloading, int endUnloading) {
        Node node = new Node(ship, startUnloading, endUnloading);
        this.report.add(node);
        if (maxDelay < node.waitingTime) {
            maxDelay = node.waitingTime;
        }
    }

    public synchronized void print() {
        int averageDelay = 0;
        for (Node n : report) {
            System.out.println(n.toString());
            System.out.println();
            averageDelay += n.waitingTime;
        }
        averageDelay /= report.size();
        System.out.println("Full Debt = " + (bulkDebt + liquidDebt + containerDebt) + " Max Delay = "
                + maxDelay + " Average Delay = " + averageDelay + " Bulk Cranes = " + bulkCranes + " Liquid Cranes = "
                + liquidCranes + " Container Cranes = " + containerCranes);
    }

    class Node {
        private Ship ship;
        private int waitingTime;
        private int startUnloading;
        private int durationUnloading;

        public Node(Ship ship, int startUnloading, int endUnloading) {
            this.ship = ship;
            this.waitingTime = startUnloading - ship.getTime();
            this.startUnloading = startUnloading;
            this.durationUnloading = endUnloading - startUnloading;
        }

        @Override
        public String toString() {
            return "Name = " + ship.getName() + " Cumming Time: " + ship.getDay() + ":" + ship.getHours() + ":" + ship.getMinutes()
                    + " \nWaiting time: " + toDate(waitingTime) + "\nUnloading Start Time: " + toDate(startUnloading)
                    + "\nUnloading Duration: " + toDate(durationUnloading);
        }

        public String toDate(int minutes) {
            int days = minutes / 24 / 60;
            int hours = minutes / 60 - days * 24;
            int min = minutes - hours * 60 - days * 24 * 60;
            return days + ":" + hours + ":" + min;
        }

        public int getDebt() {
            return waitingTime / 60 * 100;
        }
    }

    public void addDebt(int minutes, TypeOfShip type) {
        switch (type) {
            case BULK:
                bulkDebt = minutes / 60 * 100;
                break;
            case LIQUID:
                liquidDebt = minutes / 60 * 100;
                break;
            case CONTAINER:
                containerDebt = minutes / 60 * 100;
                break;
        }
    }

    public void setCranes(int bulkCranes, int liquidCranes, int containerCranes) {
        this.bulkCranes = bulkCranes;
        this.liquidCranes = liquidCranes;
        this.containerCranes = containerCranes;
    }
}
