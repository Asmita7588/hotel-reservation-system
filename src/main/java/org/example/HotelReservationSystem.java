package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HotelReservationSystem {
    public List<Hotel> hotels;

    public HotelReservationSystem() {
        hotels = new ArrayList<>();
    }

    public void addHotel(String name, int regularWeekdayRate, int regularWeekendRate) {
        Hotel hotel = new Hotel(name, regularWeekdayRate, regularWeekendRate);
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

