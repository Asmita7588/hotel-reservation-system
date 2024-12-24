package org.example;
public class Main
{
    public static void main( String[] args ) {
        System.out.println( "Welcome to Hotel Reservation System");

        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        reservationSystem.addHotel("Lakewood", 110, 90, 3);
        reservationSystem.addHotel("Bridgewood", 150, 50, 4);
        reservationSystem.addHotel("Ridgewood", 220, 150, 5);

        //to display
      //  reservationSystem.displayHotels();

       // String result = reservationSystem.findCheapestHotel("11Sep2020", "12Sep2020");
      //  System.out.println("Cheapest Hotel: " + result);


        String[] dateRange = {"11Sep2020", "12Sep2020"};
        String customerType = "Regular";

        Hotel bestHotel = reservationSystem.findCheapestBestRatedHotel(dateRange, customerType);
        if (bestHotel != null) {
            System.out.println("Cheapest Best Rated Hotel: " + bestHotel.getName() +
                    ", Rating: " + bestHotel.getRating() +
                    ", Total Rates: $" + reservationSystem.calculateTotalCostForRatedHotel(bestHotel, dateRange, customerType));
        } else {
            System.out.println("No suitable hotel found.");
        }

        //reservationSystem.displayResult(bestHotel, dateRange,customerType);
    }
}
