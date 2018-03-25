package com.heima.tea.page;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class LayUITableQuery {
    private int page;
    private int limit;
    private String order;
    private String sort;

    public LayUITableQuery() {
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPageNo() {
        int pageNo = this.getPage();
        return pageNo > 0 ? pageNo : 1;
    }

    public int getPageSize() {
        int size = this.getLimit();
        return size > 0 ? size : 10;
    }

    public boolean isSupportServerSort() {
        return true;
    }

    public Sorting getSortingInfo() {
        if (StringUtils.isNotEmpty(this.getSort()) && StringUtils.isNotEmpty(this.getOrder())) {
            Sorting sort = new Sorting();
            sort.setSortName(this.getSort());
            sort.setSortDirection(Direction.getDirectionByString(this.getOrder()));
            return sort;
        } else {
            return null;
        }
    }

    public List<Sorting> getSortingInformation() {
        return null;
    }
}
