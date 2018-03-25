package com.heima.tea.common;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V1.0
 */
public interface Table<T> {
    List<T> getCurrentPageData();
}
