package org.example;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HotelReservationSystemTest {
    @Test
    public void testAddHotel() {
        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        //add hotel
        reservationSystem.addHotel("Lakewood", 110, 90);
        reservationSystem.addHotel("Bridgewood", 160, 60);

        // to check hotel count added
        assertEquals(2, reservationSystem.hotels.size());
        assertEquals("Lakewood", reservationSystem.hotels.get(0).getName());
        assertEquals(110, reservationSystem.hotels.get(0).getRegularWeekdayRate());
    }
}
