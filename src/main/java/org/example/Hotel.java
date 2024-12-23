package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Hotel {
    private String name;
    private int regularWeekdayRate;
    private int regularWeekendRate;

    public Hotel(String name, int regularWeekdayRate, int regularWeekendRate) {
        this.name = name;
        this.regularWeekdayRate = regularWeekdayRate;
        this.regularWeekendRate = regularWeekendRate;
    }

    public String getName() {
        return name;
    }

    public int getRegularWeekdayRate() {
        return regularWeekdayRate;
    }

    public int getRegularWeekendRate() {
        return regularWeekendRate;
    }

    public int calculateTotalCost(List<LocalDate> dates){
        int totalCost = 0;
        for(LocalDate date :dates) {
            DayOfWeek day = date.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                totalCost += regularWeekendRate;
            } else {
                totalCost += regularWeekdayRate;
            }
        }
     return totalCost;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                '}';
    }
}

