package org.example;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HotelReservationSystem {
    public List<Hotel> hotels;

    public HotelReservationSystem() {
        hotels = new ArrayList<>();
    }

    public void addHotel(String name, int regularWeekdayRate, int regularWeekendRate, int rewardWeekdayRate, int rewardWeekendRate, int rating) {
        Hotel hotel = new Hotel(name, regularWeekdayRate, regularWeekendRate, rewardWeekdayRate, rewardWeekendRate, rating);
        hotels.add(hotel);
        System.out.println("Added Hotel: " + hotel);
    }

    public List<LocalDate> parseDates(String... dateStrings){
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("ddMMMyyyy");
        List<LocalDate> dates = new ArrayList<>();

        for(String date : dateStrings){
            dates.add(LocalDate.parse(date, formatter));
        }
        return dates;
    }

    public String findCheapestHotel( String... dateString){
        List<LocalDate> dates = parseDates(dateString);
        Hotel cheapestHotel = Collections.min(hotels, Comparator.comparingInt(hotels ->hotels.calculateTotalCost(dates)));
        int totalCost = cheapestHotel.calculateTotalCost(dates);
     return cheapestHotel.getName() + " , TotalRates $ = "+totalCost;
    }

    public Hotel findCheapestBestRatedHotel(String[] dateRange, String customerType) {
        int minCost = Integer.MAX_VALUE;
        Hotel cheapestBestRatedHotel = null;

        for (Hotel hotel : hotels) {
            int totalCost = calculateTotalCostForRatedHotel(hotel, dateRange, customerType);

            if (totalCost < minCost || (totalCost == minCost && (cheapestBestRatedHotel == null || hotel.getRating() > cheapestBestRatedHotel.getRating()))) {
                minCost = totalCost;
                cheapestBestRatedHotel = hotel;
            }
        }
        return cheapestBestRatedHotel;
    }

    public Hotel findBestRatedHotel(String[] dateRange, String customerType) {
        Hotel bestRatedHotel = null;
        int highestRating = 0;

        for (Hotel hotel : hotels) {
            if (hotel.getRating() > highestRating) {
                highestRating = hotel.getRating();
                bestRatedHotel = hotel;
            }
        }
        return bestRatedHotel;
    }

    int calculateTotalCostForRatedHotel(Hotel hotel, String[] dateRange, String customerType) {
        int totalCost = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        for (String date : dateRange) {
            LocalDate localDate = LocalDate.parse(date, formatter);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();

            if (customerType.equalsIgnoreCase("Regular")) {
                totalCost += (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
                        ? hotel.getRewardWeekendRate() : hotel.getRewardWeekdayRate();
            }
        }
        return totalCost;
    }

    public void displayResult(Hotel hotel, String[] dateRange, String customerType) {
        if (hotel != null) {
            int totalCost = calculateTotalCostForRatedHotel(hotel, dateRange, customerType);
            System.out.println("Cheapest Best Rated Hotel: " + hotel.getName() +
                    ", Rating: " + hotel.getRating() +
                    ", Total Rates: $" + totalCost);
        } else {
            System.out.println("No suitable hotel found.");
        }
    }

    public void displayHotelsWithLoyaltyRates() {
        System.out.println("Hotels with Reward Customer Rates:");
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getName() + " - Weekday Rate: $" + hotel.getRewardWeekdayRate() +
                    ", Weekend Rate: $" + hotel.getRewardWeekendRate());
        }
    }
    public void displayHotels() {
        if (hotels.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            System.out.println("List of Hotels:");
            for (Hotel hotel : hotels) {
                System.out.println(hotel);
            }
        }
    }
}

