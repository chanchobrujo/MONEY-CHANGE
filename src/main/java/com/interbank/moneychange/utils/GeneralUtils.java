package com.interbank.moneychange.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class GeneralUtils {
    public GeneralUtils(){

    }

    public static ZonedDateTime zoneSystemDefault() {
        return ((new Date()).toInstant()).atZone(ZoneId.systemDefault());
    }
}
