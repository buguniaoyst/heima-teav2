package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.CourseSchedule;
import com.heima.tea.page.Page;
import com.heima.tea.service.CourseScheduleService;
import com.heima.tea.vo.CourseScheduleQueryVo;
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
@RequestMapping("course_schedule")
public class CourseScheduleController extends BaseController {

    @Autowired
    private CourseScheduleService courseScheduleService;


    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<CourseSchedule> list(Page<CourseSchedule> courseSchedulePage, CourseScheduleQueryVo courseScheduleQueryVo) {
        //查找非管理员
        courseSchedulePage = courseScheduleService.findPagination(courseSchedulePage, CourseSchedule.class, courseScheduleQueryVo);
        return layuiTable(courseSchedulePage);
    }



}
