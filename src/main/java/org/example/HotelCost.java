package org.example;

public class HotelCost {
    Hotel hotel;
    int totalCost;

    HotelCost(Hotel hotel, int totalCost) {
        this.hotel = hotel;
        this.totalCost = totalCost;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getTotalCost() {
        return totalCost;
    }
}

