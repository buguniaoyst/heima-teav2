package com.heima.tea.page;

import java.io.Serializable;
import java.util.List;

public interface Query extends Serializable{
    int getPageNo();

    int getPageSize();

    boolean isSupportServerSort();

    boolean isSupportMultiSort();

    boolean isCurrentMultiSort();

    Sorting getSortingInfo();

    List<Sorting> getSortingInformation();
}
