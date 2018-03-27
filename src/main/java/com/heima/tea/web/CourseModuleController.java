package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.CourseModule;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.service.CourseModuleService;
import com.heima.tea.vo.CourseModuleQueryVo;
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
@RequestMapping("course_module")
public class CourseModuleController extends BaseController {
    @Autowired
    private CourseModuleService courseModuleService;



    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<CourseModule> list(Page<CourseModule> coursePage, CourseModuleQueryVo courseModuleQueryVo) {
        //查找非管理员
        coursePage = courseModuleService.findPagination(coursePage, CourseModule.class, courseModuleQueryVo);
        return layuiTable(coursePage);
    }
}
