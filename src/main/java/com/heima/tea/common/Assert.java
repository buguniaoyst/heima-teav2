package com.heima.tea.common;

import org.springframework.validation.BindingResult;

/**
 * @author 布谷鸟
 * @version V1.0
 */
public class Assert {
    public Assert() {
    }

    public static <T> T check(Template<T> messageTemplate, BindingResult result) {
        return messageTemplate.getConvert().apply(result.getFieldErrors());
    }
}
