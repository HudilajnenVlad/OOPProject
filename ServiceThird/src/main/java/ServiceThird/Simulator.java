package ServiceThird;


import entity.Schedule;
import entity.Ship.Ship;
import entity.Ship.TypeOfShip;
import lombok.SneakyThrows;


import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;


public class Simulator {

    private boolean isArrived(Ship ship, int time) {
        return ship.getTime() <= time;
    }

    @SneakyThrows
    public void simulate(Schedule schedule, int numberOfCranes, TypeOfShip type, Report report) {
        int resultWaiting = 0;
        ConcurrentLinkedDeque<Ship> realSchedule = new ConcurrentLinkedDeque<>();
        ConcurrentLinkedDeque<Ship> inPort = new ConcurrentLinkedDeque<>();
        ConcurrentLinkedDeque<Crane> freeCranes = new ConcurrentLinkedDeque<>();
        ConcurrentLinkedDeque<Worker> workers = new ConcurrentLinkedDeque<>();

        for (Ship i :
                schedule.getSchedule()) {
            if (i.getType().equals(type)) {
                realSchedule.add(i);
            }
        }


        for (int i = 0; i < numberOfCranes; i++) {
            Crane newCrane = new Crane();
            freeCranes.add(newCrane);
            newCrane.setDaemon(true);
            newCrane.start();
        }

        for (int time = 0; time < 24 * 60 * 30; time++) {

            while (!realSchedule.isEmpty() && isArrived(realSchedule.peek(), time))
                inPort.add(realSchedule.remove());

            if (inPort.size() > freeCranes.size()) {
                while (!freeCranes.isEmpty() && !inPort.isEmpty()) {
                    Worker worker = new Worker(inPort.remove(), time);
                    worker.addCrane(freeCranes.remove());
                    workers.add(worker);
                }
            } else {
                int count = freeCranes.size() - inPort.size();
                while (!inPort.isEmpty() && !freeCranes.isEmpty()) {
                    Worker worker = new Worker(inPort.remove(), time);
                    if (count > 0) {
                        worker.addCrane(freeCranes.remove(), freeCranes.remove());
                        count--;
                    } else {
                        worker.addCrane(freeCranes.remove());
                    }
                    workers.add(worker);
                }
            }

            for (Worker w : workers) {
                if (w.checkWorkEnded(time)) {
                    for (Crane c : w.getCranes()) {
                        if (c != null) {
                            freeCranes.add(c);
                            c.setWorker(null);
                            c.reset();
                        }
                    }
                    report.addNode(w.getShip(), w.getStartTime(), time);
                    workers.remove(w);
                }
            }
            resultWaiting += inPort.size();
        }
        /*
        for (Worker w : workers)
            for (Crane c : w.getCranes())
                if(c != null)
                    c.sleep(1000);
*/

        for (Worker w : workers)
            for (Crane c : w.getCranes())
                if (c != null)
                    c.interrupt();

        for (Crane c : freeCranes)
            if (c != null)
                c.interrupt();

        report.addDebt(resultWaiting, type);
    }


    public Report serviceThird(String json) throws IOException {
        Schedule schedule = new Schedule(new Convertor().fromJSON(json));
        new DeviationGenerator().generateDeviation(schedule);
        int bulkCranes = 1;
        int liquidCranes = 1;
        int containerCranes = 1;
        Report report = new Report();
        do {
            Schedule scheduleCopy = schedule.getCopy();

            if (report.getBulkDebt() > 30000) {
                bulkCranes += report.getBulkDebt() / 30000;
            }
            if (report.getLiquidDebt() > 30000) {
                liquidCranes += report.getLiquidDebt() / 30000;
            }
            if (report.getContainerDebt() > 30000) {
                containerCranes += report.getContainerDebt() / 30000;
            }
            report = new Report();

            simulate(scheduleCopy, bulkCranes, TypeOfShip.BULK, report);
            simulate(scheduleCopy, liquidCranes, TypeOfShip.LIQUID, report);
            simulate(scheduleCopy, containerCranes, TypeOfShip.CONTAINER, report);
        } while (report.getBulkDebt() > 30000 || report.getLiquidDebt() > 30000 || report.getContainerDebt() > 30000);
        report.setCranes(bulkCranes, liquidCranes, containerCranes);
        report.print();
        return report;
    }
}
