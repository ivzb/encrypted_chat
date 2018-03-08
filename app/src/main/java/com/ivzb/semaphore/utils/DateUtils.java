package com.ivzb.semaphore.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.text.format.DateUtils.FORMAT_24HOUR;
import static android.text.format.DateUtils.MINUTE_IN_MILLIS;
import static android.text.format.DateUtils.WEEK_IN_MILLIS;

public class DateUtils {

    public static final String PATTERN = "EEEE, MMMM d, yyyy  HH:mm";
    public static final String PATTERN_TIME = "HH:mm";
    public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String format(Date date) {
        return format(date, PATTERN);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);

    }

    public static String friendlyFormat(Date date, Date now) {
        return android.text.format.DateUtils.getRelativeTimeSpanString(date.getTime(), now.getTime(), MINUTE_IN_MILLIS).toString();
    }

    public static Date create(
            int year,
            int month,
            int day,
            int hour,
            int minute,
            int second) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        return calendar.getTime();
    }
}