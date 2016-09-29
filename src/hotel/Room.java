/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.Date;

/**
 *
 * @author Amelia
 */
public class Room {

    int roomNumber;
    RoomType roomType;
    int floor;
    Date availableFromDate;
    String description;

    public Room(int roomNumber, RoomType roomType, int floor) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.floor = floor;
        this.availableFromDate = null;
        this.description = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getRoomFloor() {
        return floor;
    }
    
    public void setAvailableFromDate(Date availableFromDate) {
        this.availableFromDate = availableFromDate;
    }

    public Date getAvailableFromDate() {
        return availableFromDate;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
