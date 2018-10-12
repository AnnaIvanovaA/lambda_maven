package com.jet.rendererTypeCheck;

import java.util.Calendar;

public class Duration {
     long durationInMillis;

    public Duration(Calendar eventStart, Calendar eventEnd) {
        this.durationInMillis =  eventEnd.getTimeInMillis() - eventStart.getTimeInMillis();
    }

    public long getDurationDays() {
        return durationInMillis;
    }

    public void setDurationDays(long durationDays) {
        this.durationInMillis = durationDays;
    }
}