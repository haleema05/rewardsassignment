package com.management.rewards;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
    public static void main(String[] args) {
        for (int i=0; i<1000; i++) {
            Random rand = new Random();
            int customerId = rand.nextInt(100);
            int amount = rand.nextInt(1000);

            LocalDate startDate = LocalDate.of(2023, 2, 1);
            LocalDate endDate = LocalDate.of(2024, 1, 31);
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            long randomNumberOfDays = ThreadLocalRandom.current().nextLong(daysBetween + 1);
            LocalDate randomDate = startDate.plusDays(randomNumberOfDays);
   System.out.println(i+","+customerId+"," + amount+","+randomDate.getYear() +"-"+randomDate.getMonthValue()+"-"+randomDate.getDayOfMonth());
        }
    }
}
