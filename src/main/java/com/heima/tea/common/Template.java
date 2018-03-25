package com.heima.tea.common;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * @author 布谷鸟
 * @version V1.0
 */
public class Template<T> {
    private MessageSource messageSource;
    private Locale currentLocale;
    private Function<List<FieldError>, T> convert;

    public Template(MessageSource messageSource, Locale currentLocale, Function<List<FieldError>, T> convert) {
        this.messageSource = messageSource;
        this.currentLocale = currentLocale;
        this.convert = convert;
    }

    public Template(MessageSource messageSource, Function<List<FieldError>, T> convert) {
        this.messageSource = messageSource;
        this.currentLocale = LocaleContextHolder.getLocale();
        this.convert = convert;
    }

    public MessageSource getMessageSource() {
        return this.messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Function<List<FieldError>, T> getConvert() {
        return this.convert;
    }

    public void setConvert(Function<List<FieldError>, T> convert) {
        this.convert = convert;
    }
}
