package org.chase.utilities.commmonUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	/**
	 * Gets the current date/time represented as local time.
	 *
	 * @return The current date/time.
	 */

	public static Date getCurrentDateTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Gets the current date/time represented as local time.
	 *
	 * @return The current date/time in the given string format.
	 */
	public static String getCurrentDateTimeFormatted(String format) {
		return formatDate(getCurrentDateTime(), format);
	}

	/**
	 * Gets a date that is the specified number of days in the future.
	 *
	 * @return Returns the newly generated date.
	 */
	public static Date getFutureDate(int numberOfDays) {
		return addDays(getCurrentDateTime(), numberOfDays);
	}

	/**
	 * Gets a date in that is the specified number of days in the future.
	 *
	 * @return Returns the newly generated date in the given string format.
	 */
	public static String getFutureDateFormatted(int numberOfDays, String format) {
		Date futureDate = getFutureDate(numberOfDays);
		return formatDate(futureDate, format);
	}

	/**
	 * Adds the specified number of days to a date.
	 *
	 * @param initialDate  The date to which days will be added.
	 * @param numberOfDays The number of days to add. Pass a negative value to
	 *                     subtract days.
	 * @return Returns the newly generated date.
	 */
	public static Date addDays(Date initialDate, int numberOfDays) {
		if (initialDate == null)
			throw new IllegalArgumentException("You must specify 'initialDate'.");

		// Initialize a calendar to the given time
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(initialDate);

		// Adjust the days
		calendar.add(Calendar.DATE, numberOfDays);

		// Return the new date
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * Formats a date in the given format.
	 *
	 * @param date   The date to be formatted.
	 * @param format The format to apply to the date.
	 * @return Returns the formatted date.
	 */
	public static String formatDate(Date date, String format) {
		return formatDate(date, format, true);
	}

	/**
	 * Formats a date in the given format.
	 *
	 * @param date         The date to be formatted.
	 * @param format       The format to apply to the date.
	 * @param useLocalTime When true, the local system time is used. Otherwise GMT
	 *                     time is used.
	 * @return Returns the formatted date.
	 */
	public static String formatDate(Date date, String format, Boolean useLocalTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (!useLocalTime)
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dateText = formatter.format(date);
		return dateText;
	}
}
