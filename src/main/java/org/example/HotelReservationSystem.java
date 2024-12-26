package org.example;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private boolean isValidDate(String date) {
        String regex = "\\d{1,2}[A-Za-z]{3}\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }


    public void findCheapestBestRatedHotelUsingStream(String[] dateRange) {
        try {
            for (String date : dateRange) {
                if (!isValidDate(date)) {
                    throw new IllegalArgumentException("Invalid date format: " + date);
                }
            }

            Hotel bestRatedCheapestHotel = hotels.stream()
                    .map(hotel -> {
                        int totalCost = calculateTotalCostForRatedHotel(hotel, dateRange, "Regular");
                        return new HotelCost(hotel, totalCost);
                    })
                    .filter(hotelCost -> hotelCost.totalCost < Integer.MAX_VALUE)
                    .sorted((h1, h2) -> {
                        if (h1.totalCost != h2.totalCost) {
                            return Integer.compare(h1.totalCost, h2.totalCost);
                        }
                        return Integer.compare(h2.hotel.getRating(), h1.hotel.getRating());
                    })
                    .findFirst()
                    .map(hotelCost -> hotelCost.hotel)
                    .orElse(null);

            if (bestRatedCheapestHotel != null) {
                int totalCost = calculateTotalCostForRatedHotel(bestRatedCheapestHotel, dateRange,"Regular");
                System.out.println("Cheapest Best Rated Hotel: " + bestRatedCheapestHotel.getName() +
                        ", Rating: " + bestRatedCheapestHotel.getRating() +
                        ", Total Rates: $" + totalCost);
            } else {
                System.out.println("No suitable hotel found.");
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println(e.getMessage());
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

