package com.db.dbhackathonapi;

import com.db.dbhackathonapi.Tables.GreenActivity;

import java.util.Comparator;
public class CompareByTimeStamp implements Comparator<GreenActivity> {

    @Override
    public int compare(GreenActivity o1, GreenActivity o2) {
        long t1 = o1.getTimestamp() != null ? o1.getTimestamp().getTime() : 0L;
        long t2 = o2.getTimestamp() != null ? o2.getTimestamp().getTime() : 0L;
        if (t2 > t1)
            return 1;
        else if (t1 > t2)
            return -1;
        else
            return 0;
    }
}
