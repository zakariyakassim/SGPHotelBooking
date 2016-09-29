/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public enum RoomType {

    SINGLE(1),
    DOUBLE(1.5),
    TWIN(2),
    HONEYMOON(4);

    private final double incrementer;

    RoomType(double value) {
        incrementer = value;
    }

    private int daysBetween(Date firstDate, Date secondDate) {
        int millisecondsInOneDay = 864000000;
        return (int) (Math.abs(secondDate.getTime() - firstDate.getTime()) / millisecondsInOneDay);
    }

    public double getPrice(Date checkInDate, Date checkOutDate) {
        int noticePeriod = daysBetween(Calendar.getInstance().getTime(), checkInDate);
        double defaultPrice = (noticePeriod > 60 ? 15.50 : (noticePeriod > 40 ? 22.75 : (noticePeriod > 20 ? 28.90
                : (noticePeriod > 10 ? 35.60 : (noticePeriod > 5 ? 41.35 : 52.80)))));

        int numberOfNights = daysBetween(checkInDate, checkOutDate);
        double price = defaultPrice * incrementer * numberOfNights;

        return Math.round(price * 100) / 100.0;
    }

    public static RoomType parseString(String value) throws ParseException {
        if (value.equalsIgnoreCase("SINGLE")) {
            return SINGLE;
        } else if (value.equalsIgnoreCase("DOUBLE")) {
            return DOUBLE;
        } else if (value.equalsIgnoreCase("TWIN")) {
            return TWIN;
        } else if (value.equalsIgnoreCase("HONEYMOON")) {
            return HONEYMOON;
        } else {
            throw new ParseException("Unable to parse string: [" + value + "]", 0);
        }
    }
}
