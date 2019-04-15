package com.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
    public static String getTodayString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
