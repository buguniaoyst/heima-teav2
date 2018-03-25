package com.heima.tea.common;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V1.0
 */
public class LayUITable<T> implements Table<T>{
    private int code;
    private String msg;
    private long count;
    private List<T> data;

    public LayUITable(int code, String msg, long count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public LayUITable(long count, List<T> data) {
        this.code = 0;
        this.msg = "获取数据成功！";
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public List<T> getCurrentPageData() {
        return this.data;
    }
}
