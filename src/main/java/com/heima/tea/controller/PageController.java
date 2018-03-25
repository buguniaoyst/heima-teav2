package com.heima.tea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 布谷鸟
 * @version V1.0
 */
@Controller
public class PageController {

    @RequestMapping("{pageName}")
    public String getPage(@PathVariable("pageName") String pageName) {
        System.out.println(pageName);
        return pageName;
    }

    @RequestMapping("{pageName}/{moduleName}")
    public String getModulePage(@PathVariable("pageName") String pageName,@PathVariable("moduleName") String moduleName) {
        System.out.println(pageName);
        return pageName+"/"+moduleName;
    }

}
