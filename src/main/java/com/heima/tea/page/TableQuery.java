package com.heima.tea.page;

import java.util.List;

public  abstract  class TableQuery {
    public TableQuery() {
    }

    public abstract int getPageNo();

    public abstract int getPageSize();

    public boolean isSupportServerSort() {
        return false;
    }

    public boolean isSupportMultiSort() {
        return false;
    }

    public boolean isCurrentMultiSort() {
        return false;
    }

    public abstract Sorting getSortingInfo();

    public abstract List<Sorting> getSortingInformation();
}
