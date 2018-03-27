package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.page.Page;
import com.heima.tea.service.ClassService;
import com.heima.tea.vo.ClassQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Controller
@RequestMapping("class")
public class ClassController extends BaseController{


    @Autowired
    private ClassService classService;


    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<ClassInfo> list(Page<ClassInfo> classPage, ClassQueryVo classQueryVo) {
        classPage = classService.findPagination(classPage, ClassInfo.class, classQueryVo);
        return layuiTable(classPage);
    }

    @RequestMapping(value = "classAddOrUpdate",method = RequestMethod.POST)
    public String classAddOrUpdate(ClassInfo classInfo){
        Integer save = classService.save(classInfo);
        return "redirect:/rest/class_info/class_info";
    }



}
