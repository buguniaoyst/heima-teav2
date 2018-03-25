package com.heima.tea.page;

/**
 * @author 布谷鸟
 * @version V1.0
 */
public class PageOrder {
    protected String orderField;
    protected String orderDirection;

    public PageOrder() {
    }

    public String getOrderField() {
        return this.orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return this.orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }
}
