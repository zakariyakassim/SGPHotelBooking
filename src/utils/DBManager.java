package utils;

import hotel.Booking;
import hotel.RoomType;
import hotel.Room;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * A helper class to manage database creation and version management.
 */
public class DBManager {

    /**
     * Connection to the database.
     */
    private final Connection db;

    /**
     * SQL statement to query the database.
     */
    private PreparedStatement sql;

    /**
     * The result set of an SQL query.
     */
    private ResultSet results;

    /**
     * SQL statement to retrieve user details by username and password.
     */
    private final String SQL_SELECT_FROM_USER = "SELECT * FROM SGP_USER WHERE UserName = ? AND Password = ?";

    /**
     * SQL statement to insert new user.
     */
    private final String SQL_INSERT_INTO_USER = "INSERT INTO SGP_USER "
            + "(UserName, Password, Forename, Surname, DateOfBirth, Email) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * SQL statement to determine the next available customer ID.
     */
    private final String SQL_NEXT_CUSTOMER_ID = "SELECT MAX(CustomerId) + 1 AS CustomerId FROM SGP_CUSTOMER";

    /**
     * SQL statement to insert a new customer.
     */
    private final String SQL_INSERT_INTO_CUSTOMER = "INSERT INTO SGP_CUSTOMER "
            + "(CustomerId, Forename, Surname, PostCode, PhoneNo, BankCardNo) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * SQL statement to determine the next available booking ID.
     */
    private final String SQL_NEXT_BOOKING_ID = "SELECT MAX(BookingId) + 1 AS BookingId FROM SGP_BOOKING";

    /**
     * SQL statement to retrieve booking details by booking ID.
     */
    private final String SQL_SELECT_FROM_BOOKING_BY_ID = "SELECT * FROM SGP_BOOKING WHERE BookingId = ?";

    /**
     * SQL statement to retrieve booking details by customer name.
     */
    private final String SQL_SELECT_FROM_BOOKING_BY_CUSTOMER = "SELECT B.BookingId, B.CustomerId, B.CheckInDate, "
            + "B.CheckOutDate, B.SingleRoomCount, B.DoubleRoomCount, B.TwinRoomCount, B.HoneymoonRoomCount, B.Cost "
            + "FROM SGP_BOOKING B, SGP_CUSTOMER C WHERE B.CustomerId = C.CustomerId AND C.Forename = ? AND C.Surname = ?";

    /**
     * SQL statement to insert a new booking.
     */
    private final String SQL_INSERT_INTO_BOOKING = "INSERT INTO SGP_BOOKING "
            + "(BookingId, CustomerId, CheckInDate, CheckOutDate, SingleRoomCount, DoubleRoomCount, "
            + "TwinRoomCount, HoneymoonRoomCount, Cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * SQL statement to delete an existing booking.
     */
    private final String SQL_DELETE_FROM_BOOKING = "DELETE FROM SGP_BOOKING WHERE BookingId = ?";

    /**
     * SQL statement to update an existing booking.
     */
    private final String SQL_UPDATE_BOOKING = "UPDATE SGP_BOOKING "
            + "SET CheckInDate = ?, CheckOutDate = ?, SingleRoomCount = ?, DoubleRoomCount = ?, "
            + "TwinRoomCount = ?, HoneymoonRoomCount = ?, Cost = ? WHERE BookingId = ?";

    /**
     * SQL statement to retrieve all rooms.
     */
    private final String SQL_SELECT_ALL_FROM_ROOM = "SELECT * FROM SGP_ROOM";

    /**
     * SQL statement to retrieve room details by availability.
     */
    private final String SQL_SELECT_FROM_ROOM = "SELECT * FROM SGP_ROOM "
            + "WHERE AvailableFromDate IS NULL AND RoomType = ?";

    /**
     * SQL statement to determine the number of rooms by type.
     */
    private final String SQL_COUNT_FROM_ROOM = "SELECT COUNT(*) AS Total FROM SGP_ROOM WHERE RoomType = ?";

    /**
     * SQL statement to insert a new room.
     */
    private final String SQL_INSERT_INTO_ROOM = "INSERT INTO SGP_ROOM "
            + "(RoomNo, RoomType, Floor, Description) VALUES (?, ?, ?, ?)";

    /**
     * SQL statement to update the availability date of an existing room.
     */
    private final String SQL_UPDATE_ROOM_DATE = "UPDATE SGP_ROOM SET AvailableFromDate = ? WHERE RoomNo = ?";

    /**
     * SQL statement to update the description of existing room.
     */
    private final String SQL_UPDATE_ROOM_DESCRIPTION = "UPDATE SGP_ROOM SET Description = ? WHERE RoomNo = ?";

    /**
     * SQL statement to insert a new check-in record.
     */
    private final String SQL_INSERT_INTO_CHECKIN = "INSERT INTO SGP_CHECKIN (BookingId, RoomNo) VALUES (?, ?)";

    /**
     * Constructs a new instance of {@code DBManager}.
     *
     * @throws ClassNotFoundException if driver class is not found
     * @throws SQLException if connection to database fails
     */
    public DBManager() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        db = DriverManager.getConnection("jdbc:mysql://localhost/hotelgroupproject", "root", "");
    }

    /**
     * Verifies that username exists and that password is valid.
     *
     * @param userName - the username to check
     * @param password - the password to check
     * @return boolean indicator of whether or not username/password exists
     * @throws SQLException
     */
    public boolean loginUser(String userName, String password) throws SQLException {

        sql = db.prepareStatement(SQL_SELECT_FROM_USER);
        sql.setString(1, userName);
        sql.setString(2, password);
        results = sql.executeQuery();
        return results.next();
    }

    /**
     * Creates a new user.
     *
     * @param userName - the username
     * @param password - the password
     * @param forename - the user's forename
     * @param surname - the user's surname
     * @param dateOfBirth - the user's date of birth
     * @param email - the user's email
     * @return {@code false} if username already exists, {@code true} otherwise
     * @throws SQLException
     */
    public boolean createUser(String userName, String password, String forename,
            String surname, Date dateOfBirth, String email) throws SQLException {

        try {
            sql = db.prepareStatement(SQL_INSERT_INTO_USER);
            sql.setString(1, userName);
            sql.setString(2, password);
            sql.setString(3, forename);
            sql.setString(4, surname);
            sql.setDate(5, dateOfBirth);
            sql.setString(6, email);
            sql.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException ignore) {
            return false;
        }
    }

    /**
     * Returns all rooms.
     *
     * @return the rooms
     * @throws SQLException
     * @throws ParseException
     */
    public ArrayList<Room> getAllRooms() throws SQLException, ParseException {

        ArrayList<Room> rooms = new ArrayList<>();
        sql = db.prepareStatement(SQL_SELECT_ALL_FROM_ROOM);
        results = sql.executeQuery();

        while (results.next()) {
            int number = results.getInt("RoomNo");
            int floor = results.getInt("Floor");
            RoomType roomType = RoomType.parseString(results.getString("RoomType"));
            rooms.add(new Room(number, roomType, floor));
        }

        return rooms;
    }

    /**
     * Returns all rooms currently available for check-in of the given types.
     *
     * @param roomTypes - the room types to check against
     * @return the available rooms
     * @throws SQLException
     */
    public ArrayList<Room> getRoomsByAvailability(ArrayList<RoomType> roomTypes) throws SQLException {

        ArrayList<Room> rooms = new ArrayList<>();
        for (RoomType roomType : roomTypes) {
            sql = db.prepareStatement(SQL_SELECT_FROM_ROOM);
            sql.setString(1, roomType.toString());
            results = sql.executeQuery();

            while (results.next()) {
                int number = results.getInt("RoomNo");
                int floor = results.getInt("Floor");
                rooms.add(new Room(number, roomType, floor));
            }
        }
        return rooms;
    }

    /**
     * Returns the number of rooms of the specified types available between the
     * specified dates.
     *
     * @param checkInDate - the start date of the period to check
     * @param checkOutDate - the end date of the period to check
     * @param roomType - the room type to check against
     * @return the number of matching rooms
     * @throws SQLException
     */
    public int getRoomCountByAvailability(Date checkInDate, Date checkOutDate,
            RoomType roomType) throws SQLException {

        final String SQL_SUM_FROM_BOOKING = "SELECT SUM(" + roomType.toString() + "RoomCount) "
                + "AS Count FROM SGP_BOOKING B WHERE NOT (B.CheckOutDate < ? OR B.CheckInDate > ?)";

        sql = db.prepareStatement(SQL_COUNT_FROM_ROOM);
        sql.setString(1, roomType.toString());
        results = sql.executeQuery();
        int total = (results.next() ? results.getInt("Total") : 0);

        sql = db.prepareStatement(SQL_SUM_FROM_BOOKING);
        sql.setDate(1, checkInDate);
        sql.setDate(2, checkOutDate);
        results = sql.executeQuery();
        int count = (results.next() ? results.getInt("Count") : 0);

        return total - count;
    }

    /**
     * Creates a new customer with an associated room booking.
     *
     * @param forename - the customer's forename
     * @param surname - the customer's surname
     * @param postCode - the customer's post code
     * @param phoneNo - the customer's phone number
     * @param bankCardNo - the customer's bank card number
     * @param checkInDate - the check-in date
     * @param checkOutDate - the check-out date
     * @param singleRoomCount - the number of single rooms booked
     * @param doubleRoomCount - the number of double rooms booked
     * @param twinRoomCount - the number of twin rooms booked
     * @param honeymoonRoomCount - the number of honeymoon rooms booked
     * @param cost - the cost of the booking
     * @return the booking ID as confirmation
     * @throws SQLException
     */
    public int createCustomerBooking(String forename, String surname, String postCode, String phoneNo,
            String bankCardNo, Date checkInDate, Date checkOutDate, int singleRoomCount, int doubleRoomCount,
            int twinRoomCount, int honeymoonRoomCount, double cost) throws SQLException {

        sql = db.prepareStatement(SQL_NEXT_CUSTOMER_ID);
        results = sql.executeQuery();
        int customerId = (results.next() ? results.getInt("CustomerId") : 0);

        sql = db.prepareStatement(SQL_INSERT_INTO_CUSTOMER);
        sql.setInt(1, customerId);
        sql.setString(2, forename);
        sql.setString(3, surname);
        sql.setString(4, postCode);
        sql.setString(5, phoneNo);
        sql.setString(6, bankCardNo);
        sql.executeUpdate();

        sql = db.prepareStatement(SQL_NEXT_BOOKING_ID);
        results = sql.executeQuery();
        int bookingId = (results.next() ? results.getInt("BookingId") : 0);

        sql = db.prepareStatement(SQL_INSERT_INTO_BOOKING);
        sql.setInt(1, bookingId);
        sql.setInt(2, customerId);
        sql.setDate(3, checkInDate);
        sql.setDate(4, checkOutDate);
        sql.setInt(5, singleRoomCount);
        sql.setInt(6, doubleRoomCount);
        sql.setInt(7, twinRoomCount);
        sql.setInt(8, honeymoonRoomCount);
        sql.setDouble(9, cost);
        sql.executeUpdate();

        return bookingId;
    }

    /**
     * Returns the booking with the specified ID.
     *
     * @param bookingId - the ID to search for
     * @return the matching booking
     * @throws SQLException
     */
    public Booking getBookingById(int bookingId) throws SQLException {

        sql = db.prepareStatement(SQL_SELECT_FROM_BOOKING_BY_ID);
        sql.setInt(1, bookingId);
        results = sql.executeQuery();
        if (results.next()) {
            int customerId = results.getInt("CustomerId");
            Date checkInDate = results.getDate("CheckInDate");
            Date checkOutDate = results.getDate("CheckOutDate");
            int singleRoomCount = results.getInt("SingleRoomCount");
            int doubleRoomCount = results.getInt("DoubleRoomCount");
            int twinRoomCount = results.getInt("TwinRoomCount");
            int honeymoonRoomCount = results.getInt("HoneymoonRoomCount");
            int cost = results.getInt("Cost");
            return new Booking(bookingId, customerId, checkInDate, checkOutDate,
                    singleRoomCount, doubleRoomCount, twinRoomCount, honeymoonRoomCount, cost);
        } else {
            return null;
        }
    }

    /**
     * Returns all bookings associated with the specified customer.
     *
     * @param forename - the customer's forename
     * @param surname - the customer's surname
     * @return the matching bookings
     * @throws SQLException
     */
    public ArrayList<Booking> getBookingByCustomerName(String forename, String surname) throws SQLException {

        ArrayList<Booking> bookings = new ArrayList<>();
        sql = db.prepareStatement(SQL_SELECT_FROM_BOOKING_BY_CUSTOMER);
        sql.setString(1, forename);
        sql.setString(2, surname);
        results = sql.executeQuery();
        while (results.next()) {
            int bookingId = results.getInt("BookingId");
            int customerId = results.getInt("CustomerId");
            Date checkInDate = results.getDate("CheckInDate");
            Date checkOutDate = results.getDate("CheckOutDate");
            int singleRoomCount = results.getInt("SingleRoomCount");
            int doubleRoomCount = results.getInt("DoubleRoomCount");
            int twinRoomCount = results.getInt("TwinRoomCount");
            int honeymoonRoomCount = results.getInt("HoneymoonRoomCount");
            int cost = results.getInt("Cost");

            bookings.add(new Booking(bookingId, customerId, checkInDate, checkOutDate,
                    singleRoomCount, doubleRoomCount, twinRoomCount, honeymoonRoomCount, cost));
        }
        return bookings;
    }

    /**
     * Updates the specified booking with the given data.
     *
     * @param bookingId - the id of the booking to update
     * @param checkInDate - the new check-in date
     * @param checkOutDate - the new check-o date
     * @param singleRoomCount - the new number of single rooms booked
     * @param doubleRoomCount - the new number of double rooms booked
     * @param twinRoomCount - the new number of twin rooms booked
     * @param honeymoonRoomCount - the new number of honeymoon rooms booked
     * @param cost - the new cost
     * @throws SQLException
     */
    public void updateBooking(int bookingId, Date checkInDate, Date checkOutDate, int singleRoomCount,
            int doubleRoomCount, int twinRoomCount, int honeymoonRoomCount, double cost) throws SQLException {

        sql = db.prepareStatement(SQL_UPDATE_BOOKING);
        sql.setDate(1, checkInDate);
        sql.setDate(2, checkOutDate);
        sql.setInt(3, singleRoomCount);
        sql.setInt(4, doubleRoomCount);
        sql.setInt(5, twinRoomCount);
        sql.setInt(6, honeymoonRoomCount);
        sql.setDouble(7, cost);
        sql.setInt(8, bookingId);
        sql.executeUpdate();
    }

    /**
     * Cancels the specified booking.
     *
     * @param bookingId - the id of the booking to cancel
     * @throws SQLException
     */
    public void cancelBooking(int bookingId) throws SQLException {

        sql = db.prepareStatement(SQL_DELETE_FROM_BOOKING);
        sql.setInt(1, bookingId);
        sql.executeUpdate();
    }

    /**
     * Performs a check-in by creating new check-in records and updating the
     * room availability dates.
     *
     * @param bookingId - the ID of the associated booking
     * @param roomNos - array of room numbers being checked-in
     * @param checkOutDate - new availability date
     * @throws SQLException
     */
    public void checkIn(int bookingId, ArrayList<Integer> roomNos, Date checkOutDate) throws SQLException {

        for (Integer roomNo : roomNos) {
            sql = db.prepareStatement(SQL_INSERT_INTO_CHECKIN);
            sql.setInt(1, bookingId);
            sql.setInt(2, roomNo);
            sql.executeUpdate();

            sql = db.prepareStatement(SQL_UPDATE_ROOM_DATE);
            sql.setDate(1, checkOutDate);
            sql.setInt(2, roomNo);
            sql.executeUpdate();
        }
    }

    /**
     * Performs a check-out by updating the room availability dates.
     *
     * @param roomNos - array of room numbers being checked-out
     * @throws SQLException
     */
    public void checkOut(ArrayList<Integer> roomNos) throws SQLException {

        for (Integer roomNo : roomNos) {
            sql = db.prepareStatement(SQL_UPDATE_ROOM_DATE);
            sql.setNull(1, java.sql.Types.DATE);
            sql.setInt(2, roomNo);
            sql.executeUpdate();
        }
    }

    /**
     * Creates a new room.
     *
     * @param roomNo - the number of the new room
     * @param roomType - the type of the new room
     * @param floor - the floor of the new room
     * @param description - the description of the new room
     * @return {@code false} if room number already exists, {@code true}
     * otherwise
     * @throws SQLException
     */
    public boolean createRoom(int roomNo, RoomType roomType, int floor, String description) throws SQLException {
        try {
            sql = db.prepareStatement(SQL_INSERT_INTO_ROOM);

            sql.setInt(1, roomNo);
            sql.setString(2, roomType.toString());
            sql.setInt(3, floor);
            sql.setString(4, description);
            sql.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException ignore) {
            return false;
        }
    }

    /**
     * Updates the description of an existing room.
     *
     * @param roomNo - the number of the room to update
     * @param description - the new room description
     * @throws SQLException
     */
    public void updateRoomDescription(int roomNo, String description) throws SQLException {

        sql = db.prepareStatement(SQL_UPDATE_ROOM_DESCRIPTION);
        sql.setString(1, description);
        sql.setInt(2, roomNo);
        sql.executeUpdate();
    }
}
