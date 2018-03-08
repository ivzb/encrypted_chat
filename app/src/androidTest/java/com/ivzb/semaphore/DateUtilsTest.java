package com.ivzb.semaphore;

import android.support.test.runner.AndroidJUnit4;

import com.ivzb.semaphore.utils.DateUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DateUtilsTest {

    @Test
    public void friendlyFormat_0_minutes_ago() {
        friendlyFormatTest(0, "0 minutes ago");
    }

    @Test
    public void friendlyFormat_15_seconds_ago() {
        friendlyFormatTest(15 * 1000, "0 minutes ago");
    }

    @Test
    public void friendlyFormat_30_seconds_ago() {
        friendlyFormatTest(30 * 1000, "0 minutes ago");
    }

    @Test
    public void friendlyFormat_1_minute_ago() {
        friendlyFormatTest(60 * 1000, "1 minute ago");
    }

    @Test
    public void friendlyFormat_2_minutes_ago() {
        friendlyFormatTest(2 * 60 * 1000, "2 minutes ago");
    }

    @Test
    public void friendlyFormat_3_minutes_ago() {
        friendlyFormatTest(3 * 60 * 1000, "3 minutes ago");
    }

    @Test
    public void friendlyFormat_5_minutes_ago() {
        friendlyFormatTest(5 * 60 * 1000, "5 minutes ago");
    }

    @Test
    public void friendlyFormat_15_minutes_ago() {
        friendlyFormatTest(15 * 60 * 1000, "15 minutes ago");
    }

    @Test
    public void friendlyFormat_30_minutes_ago() {
        friendlyFormatTest(30 * 60 * 1000, "30 minutes ago");
    }

    @Test
    public void friendlyFormat_59_minutes_ago() {
        friendlyFormatTest((60 * 60 * 1000) - 1000, "59 minutes ago");
    }

    @Test
    public void friendlyFormat_1_hour_ago() {
        friendlyFormatTest(60 * 60 * 1000, "1 hour ago");
    }

    @Test
    public void friendlyFormat_1_hour_59_minutes_ago() {
        friendlyFormatTest((2 * 60 * 60 * 1000) - 1, "1 hour ago");
    }

    @Test
    public void friendlyFormat_2_hours_ago() {
        friendlyFormatTest(2 * 60 * 60 * 1000, "2 hours ago");
    }

    @Test
    public void friendlyFormat_5_hours_ago() {
        friendlyFormatTest(5 * 60 * 60 * 1000, "5 hours ago");
    }

    @Test
    public void friendlyFormat_12_hours_ago() {
        friendlyFormatTest(12 * 60 * 60 * 1000, "12 hours ago");
    }

    @Test
    public void friendlyFormat_23_hours_59_minutes_ago() {
        friendlyFormatTest((24 * 60 * 60 * 1000) - 1, "23 hours ago");
    }

    @Test
    public void friendlyFormat_1_day_ago() {
        friendlyFormatTest(24 * 60 * 60 * 1000, "Yesterday");
    }

    @Test
    public void friendlyFormat_1_day_3_hours_ago() {
        friendlyFormatTest((24 * 60 * 60 * 1000) + (3 * 60 * 60 * 1000), "Yesterday");
    }

    @Test
    public void friendlyFormat_2_days_ago() {
        friendlyFormatTest(2 * 24 * 60 * 60 * 1000, "2 days ago");
    }

    private void friendlyFormatTest(long resolution, String expected) {
        Date date = new Date();
        date.setTime(date.getTime() - resolution);

        Date now = new Date();

        String actual = DateUtils.friendlyFormat(date, now);

        assertEquals(actual, expected);
    }
}
