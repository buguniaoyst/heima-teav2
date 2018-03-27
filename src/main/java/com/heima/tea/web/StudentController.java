package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.domain.StudentInfo;
import com.heima.tea.page.Page;
import com.heima.tea.service.ClassService;
import com.heima.tea.service.StudentService;
import com.heima.tea.vo.ClassQueryVo;
import com.heima.tea.vo.StudentQueryVo;
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
@RequestMapping("student")
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;



    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<StudentInfo> list(Page<StudentInfo> studentInfoPage, StudentQueryVo studentQueryVo) {
        studentInfoPage = studentService.findPagination(studentInfoPage, ClassInfo.class, studentQueryVo);
        return layuiTable(studentInfoPage);
    }
}
