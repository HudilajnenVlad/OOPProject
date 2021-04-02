package main.java;

import ServiceFirst.Schedule;
import ServiceFirst.ScheduleGenerator;
import ServiceThird.DeviationGenerator;
import ServiceThird.Filter;


public class Main {
    public static void main(String[] args) {

        DeviationGenerator deviationGenerator = new DeviationGenerator();

        Schedule schedule = new Schedule(new ScheduleGenerator().generateSchedule(5));

        schedule.printSchedule();
        deviationGenerator.generateDeviation(schedule);
        deviationGenerator.getDeviationSchedule().printSchedule();
        Filter filter = new Filter();
        filter.filterOut(deviationGenerator.getDeviationSchedule(), 30);
        deviationGenerator.getDeviationSchedule().printSchedule();
        schedule.printSchedule();
    }
}
