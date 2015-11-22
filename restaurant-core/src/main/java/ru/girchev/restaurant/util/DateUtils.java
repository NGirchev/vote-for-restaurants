package ru.girchev.restaurant.util;

import java.util.Date;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class DateUtils {

    public static Date today() {
        Date today = new java.util.Date();
        today = org.apache.commons.lang3.time.DateUtils.setHours(today, 0);
        today = org.apache.commons.lang3.time.DateUtils.setMinutes(today, 0);
        today = org.apache.commons.lang3.time.DateUtils.setSeconds(today, 0);
        today = org.apache.commons.lang3.time.DateUtils.setMilliseconds(today, 0);
        return today;
    }

    public static boolean isToday(Date dateToCompare) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(new Date(), dateToCompare);
    }
}
