package org.example;
public class Main
{
    public static void main( String[] args ) {
        System.out.println( "Welcome to Hotel Reservation System");

        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        reservationSystem.addHotel("Lakewood", 110, 90);
        reservationSystem.addHotel("Bridgewood", 160, 60);
        reservationSystem.addHotel("Ridgewood", 220, 150);

        //to display
        reservationSystem.displayHotels();
    }
}
