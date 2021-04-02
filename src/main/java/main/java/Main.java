package main.java;

import ServiceFirst.Schedule;
import ServiceSecond.Convertor;
import ServiceThird.DeviationGenerator;

import java.io.IOException;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        int k = 0;
        Random random = new Random();
        for (int i = 0; i < 43200; i++) {
            random.nextInt();
            random.nextDouble();
            random.nextFloat();
            random.nextGaussian();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
