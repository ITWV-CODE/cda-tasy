package br.com.itwv.cdatasy.base.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Date utilities class.
 *
 * @author prsferreira
 * @version $Revision$
 */
public final class ExtendedDateUtils {

    /**
     * Application default timezone.
     */
    public static final String DEFAULT_TIMEZONE = "UTC";

    /**
     * Application default iso8601 date time format.
     */
    public static final String ISO_8601_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static final String CDA_DATE_TIME_FORMAT = "yyyyMMddHHmmssZ";

    public static final String BAR_DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Private constructor.
     */
    private ExtendedDateUtils() {
    }

    /**
     * Get the current UTC time in milliseconds.
     *
     * @return UTC time in milliseconds
     */
    public static long getCurrentUTCTime() {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        return calendar.getTimeInMillis();
    }

    /**
     * Adds years the date.
     *
     * @param timeInMilliseconds time in UTC milliseconds
     * @param numberOfYears      the number of years
     * @return the calculated date
     */
    public static long addYearsToTime(final long timeInMilliseconds, final int numberOfYears) {

        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        calendar.setTimeInMillis(timeInMilliseconds);
        calendar.add(Calendar.YEAR, numberOfYears);

        return calendar.getTimeInMillis();
    }

    /**
     * Get the calendar from a specific time in milliseconds.
     *
     * @param timeZone           the output <code>timeZone</code> for the calendar
     * @param timeInMilliseconds time in UTC milliseconds
     * @return Date with provided <code>timeZone</code>
     */
    private static Calendar getCalendar(final Long timeInMilliseconds, final TimeZone timeZone) {

        final Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(timeInMilliseconds);

        return calendar;
    }

    /**
     * Extract the years between two dates.
     *
     * @param startTimeInMilliseconds the start given date and time in milliseconds
     * @param endTimeInMilliseconds   the end given date and time in milliseconds
     * @return the years between
     */
    public static int getYearsBetween(final long startTimeInMilliseconds, final long endTimeInMilliseconds) {

        Calendar start = getCalendar(startTimeInMilliseconds, TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        Calendar end = getCalendar(endTimeInMilliseconds, TimeZone.getTimeZone(DEFAULT_TIMEZONE));

        int years = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);

        start.set(Calendar.YEAR, end.get(Calendar.YEAR)); // set the same year
        // to check the
        // current year

        if (end.getTime().before(start.getTime())) {
            years--;
        }
        return years;
    }

    /**
     * Extract the months left between two dates. the months related to years
     * are not evaluated
     *
     * @param startTimeInMilliseconds the start given date and time in milliseconds
     * @param endTimeInMilliseconds   the end given date and time in milliseconds
     * @return the months left between
     */
    public static int getMonthsLeftBetween(final long startTimeInMilliseconds, final long endTimeInMilliseconds) {

        Calendar start = getCalendar(startTimeInMilliseconds, TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        Calendar end = getCalendar(endTimeInMilliseconds, TimeZone.getTimeZone(DEFAULT_TIMEZONE));

        int months = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

        if (months < 0) {
            months = 12 - Math.abs(months);
        } else if (months == 0) {
            months = 1;
        }
        return months;
    }

    /**
     * Return the data in ISO8601 format.
     *
     * @param dateInMilliseconds the date in milliseconds
     * @return the formatted date
     */
    public static String getDateInISO8601(final Long dateInMilliseconds) {

        Calendar calendar = getCalendar(dateInMilliseconds, TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        return DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(calendar);
    }

    /**
     * Return the date in ISO format.
     *
     * @param dateInMilliseconds the date in milliseconds
     * @return the formatted date
     */
    public static String getDateInISO(final Long dateInMilliseconds) {

        Calendar calendar = getCalendar(dateInMilliseconds, TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        return DateFormatUtils.ISO_DATE_FORMAT.format(calendar);
    }

    /**
     * Return the date in the specified format.
     *
     * @param dateInMilliseconds the date in milliseconds
     * @param format             the format
     * @return the date formatted
     */
    public static String getDateFormatted(final long dateInMilliseconds, final String format) {

        Calendar calendar = getCalendar(dateInMilliseconds, TimeZone.getTimeZone(DEFAULT_TIMEZONE));

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(calendar.getTime());

    }

    /**
     * Return the date in the specified format.
     *
     * @param format the format
     * @return the date formatted
     */
    public static String getUTCDateFormatted(final String format) {

        Calendar calendar = getCalendar(getCurrentUTCTime(), TimeZone.getTimeZone(DEFAULT_TIMEZONE));

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(calendar.getTime());

    }

    /**
     * Adds days the date.
     *
     * @param timeInMilliseconds time in UTC milliseconds
     * @param numberOfDays       the number of days
     * @return the calculated date
     */
    public static long addDaysToTime(final long timeInMilliseconds, final int numberOfDays) {

        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        calendar.setTimeInMillis(timeInMilliseconds);
        calendar.add(Calendar.DATE, numberOfDays);

        return calendar.getTimeInMillis();
    }

    /**
     * Return the date in milliseconds.
     *
     * @param date   the string date value
     * @param format the format
     * @return the date milliseconds value
     */
    public static Long getDateInMillis(final String date, final String format) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        try {
            final DateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
            calendar.setTime(dateFormat.parse(date));
        } catch (final ParseException e) {
            calendar = null;
        }
        return (calendar != null) ? calendar.getTimeInMillis() : null;
    }
}
