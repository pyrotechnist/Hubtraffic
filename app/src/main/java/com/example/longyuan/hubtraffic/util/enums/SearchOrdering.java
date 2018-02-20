package com.example.longyuan.hubtraffic.util.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by LONGYUAN on 2018/2/16.
 */

public class SearchOrdering {

    public static final int FEATURED = 0;
    public static final int NEWEST = 1;
    public static final int MOSTVIEWED = 2;
    public static final int RATING = 3;

    public SearchOrdering(@Ordering int season) {
        currentSearchOrdering = season;
    }

    @Ordering int currentSearchOrdering;

    @IntDef({FEATURED, NEWEST, MOSTVIEWED, RATING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Ordering {
    }

    public void setOrdering(@Ordering int ordering) {
        this.currentSearchOrdering = ordering;
    }

    public int getOrdering() {
        return currentSearchOrdering;
    }


}
