package com.heima.tea.page;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 布谷鸟
 * @version V1.0
 */
public class Page<T> extends PageOrder implements Serializable {
    private int pageNo = 1;
    private int pageSize = 30;
    private List result = null;
    private int totalCount = -1;
    private boolean autoCount = true;

    public Page() {
    }

    public Page(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page(List<T> data) {
        this.result = data;
    }

    public Page(int pageSize, String orderDirection, String orderField) {
        this.pageSize = pageSize;
        super.orderDirection = orderDirection;
        super.orderField = orderField;
    }

    public boolean isAutoCount() {
        return this.autoCount;
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPageSizeSetted() {
        return this.pageSize >= 0;
    }

    public int getPageNo() {
        return this.getTotalPages() != -1 && this.getTotalPages() <= this.pageNo ? this.getTotalPages() : this.pageNo;
    }

    public int getCurrentPageNo() {
        return this.getPageNo();
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getFirst() {
        return this.getPageNo() >= 1 && this.getPageSize() >= 1 ? (this.getPageNo() - 1) * this.getPageSize() : -1;
    }

    public boolean isFirstSetted() {
        return this.getPageNo() > 0 && this.getPageSize() > 0;
    }

    public List<?> getResult() {
        return (List)(this.result == null ? new ArrayList() : this.result);
    }

    public List<T> getTResult() {
        return (List)(this.result == null ? new ArrayList() : this.result);
    }

    public List getRawResult() {
        return (List)(this.result == null ? new ArrayList() : this.result);
    }

    public void setTResult(List<T> result) {
        this.result = result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }

    public void setRawResult(List result) {
        this.result = result;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        if (this.totalCount == -1) {
            return -1;
        } else {
            int count = this.totalCount / this.pageSize;
            if (this.totalCount % this.pageSize > 0) {
                ++count;
            }

            return count;
        }
    }

    public boolean isHasNext() {
        return this.pageNo + 1 <= this.getTotalPages();
    }

    public int getNextPage() {
        return this.isHasNext() ? this.pageNo + 1 : this.pageNo;
    }

    public boolean isHasPre() {
        return this.pageNo - 1 >= 1;
    }

    public int getPrePage() {
        return this.isHasPre() ? this.pageNo - 1 : this.pageNo;
    }
}
