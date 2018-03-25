package com.heima.tea.controller;

import com.heima.tea.common.R;
import com.heima.tea.common.Template;
import com.heima.tea.page.Page;
import com.heima.tea.common.LayUITable;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

import java.util.function.Function;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author 布谷鸟
 * @version V1.0
 */
public abstract class BaseController {
    //TODO:替换注释内容
    protected static String basePath(HttpServletRequest request) {
        String bathPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
//        log.info("当前域名：[{}]", bathPath);
        return bathPath;
//        return "http://wuwenbin.me/";
    }

    protected static <T> LayUITable<T> layuiTable(Page<T> page) {
        return new LayUITable<>(page.getTotalCount(), page.getTResult());
    }

    protected static <T> R validResponse(BindingResult result, Template<R> template, T t, Function<T, R> operation) {
        if (!result.hasErrors()) {
            return operation.apply(t);
        } else {
//            return check(template, result);
            return  null;
        }
    }

    protected boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(header);
    }

    protected boolean isJson(HttpServletRequest request) {
        String headerAccept = request.getHeader("Accept");
        return !isEmpty(headerAccept) && headerAccept.contains("application/json");
    }

    protected boolean isRouter(HttpServletRequest request) {
        String headerAccept = request.getHeader("Accept");
        return !isEmpty(headerAccept) && headerAccept.contains("text/html") && !isJson(request) && isAjax(request) && isGet(request);
    }

    protected boolean isGet(HttpServletRequest request) {
        String method = request.getMethod();
        return "GET".equalsIgnoreCase(method);
    }

}
