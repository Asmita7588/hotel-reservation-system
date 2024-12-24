package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Hotel {
    private String name;
    private int regularWeekdayRate;
    private int regularWeekendRate;
    private  int rewardWeekdayRate;
    private  int rewardWeekendRate;
    private int rating;

    public Hotel(String name, int regularWeekdayRate, int regularWeekendRate, int rewardWeekdayRate,int rewardWeekendRate, int rating) {
        this.name = name;
        this.regularWeekdayRate = regularWeekdayRate;
        this.regularWeekendRate = regularWeekendRate;
        this.rewardWeekdayRate = rewardWeekdayRate;
        this.rewardWeekendRate = rewardWeekendRate;
        this.rating = rating;
    }

    public int getRewardWeekendRate() {
        return rewardWeekendRate;
    }

    public int getRewardWeekdayRate() {
        return rewardWeekdayRate;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", regularWeekdayRate=" + regularWeekdayRate +
                ", regularWeekendRate=" + regularWeekendRate +
                ", rating=" + rating +
                '}';
    }

    public int getRegularWeekdayRate() {
        return regularWeekdayRate;
    }

    public int getRegularWeekendRate() {
        return regularWeekendRate;
    }

    public int getRating() {
        return rating;
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

}

