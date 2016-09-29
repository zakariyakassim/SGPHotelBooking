/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.*;

/**
 *
 * @author Amelia
 */
public class Booking {

    int bookingId;
    int customerId;
    Date checkInDate;
    Date checkOutDate;
    int singleRoomCount;
    int doubleRoomCount;
    int twinRoomCount;
    int honeymoonRoomCount;
    double cost;

    public Booking(int bookingId, int customerId, Date checkInDate, Date checkOutDate, int bookedSingleRoomCount,
            int bookedDoubleRoomCount, int bookedTwinRoomCount, int bookedHoneymoonRoomCount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.singleRoomCount = bookedSingleRoomCount;
        this.doubleRoomCount = bookedDoubleRoomCount;
        this.twinRoomCount = bookedTwinRoomCount;
        this.honeymoonRoomCount = bookedHoneymoonRoomCount;
        this.cost = singleRoomCount * RoomType.SINGLE.getPrice(checkInDate, checkOutDate)
                + doubleRoomCount * RoomType.DOUBLE.getPrice(checkInDate, checkOutDate)
                + twinRoomCount * RoomType.TWIN.getPrice(checkInDate, checkOutDate)
                + honeymoonRoomCount * RoomType.HONEYMOON.getPrice(checkInDate, checkOutDate);
    }

    public Booking(int bookingId, int customerId, Date checkInDate, Date checkOutDate, int bookedSingleRoomCount,
            int bookedDoubleRoomCount, int bookedTwinRoomCount, int bookedHoneymoonRoomCount, double cost) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.singleRoomCount = bookedSingleRoomCount;
        this.doubleRoomCount = bookedDoubleRoomCount;
        this.twinRoomCount = bookedTwinRoomCount;
        this.honeymoonRoomCount = bookedHoneymoonRoomCount;
        this.cost = cost;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public int getSingleRoomCount() {
        return singleRoomCount;
    }

    public int getDoubleRoomCount() {
        return doubleRoomCount;
    }

    public int getTwinRoomCount() {
        return twinRoomCount;
    }

    public int getHoneymoonRoomCount() {
        return honeymoonRoomCount;
    }

    public double getCost() {
        return cost;
    }
}
