package com.ivzb.semaphore._base.data.config;

import com.ivzb.semaphore.utils.DateUtils;

import java.util.Date;

public class MockConfig {

    public static final String Id            = "mock_id";
    public static final String Title         = "mock_title";
    public static final String Description   = "mock_description";
    public static final String Url           = "http://mock_url/";
    public static final Date   Date          = DateUtils.create(2017, 3, 17, 5, 45, 37);
    public static final String Email         = "mail@ivzb.com";
    public static final String Password      = "123456";
    public static final String Token         = "mock_token";
    public static final int    Page          = 0;

    private MockConfig() {

    }
}