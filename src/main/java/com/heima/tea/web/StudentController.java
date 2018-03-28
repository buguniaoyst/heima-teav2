package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.domain.StudentInfo;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.service.ClassService;
import com.heima.tea.service.StudentService;
import com.heima.tea.vo.ClassQueryVo;
import com.heima.tea.vo.StudentQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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


    /**
     * 新增学生
     * @param studentInfo
     * @return
     */
    @RequestMapping(value="addOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> addUser(StudentInfo studentInfo){
        try {
            Integer number=0;
            if(studentInfo.getId()==null){
                studentInfo.setPassword("123456");
                number= this.studentService.save(studentInfo);
            }else {
                number= this.studentService.updateSelectiveById(studentInfo);
            }

            if(number==0){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据id删除学生信息
     * @param ids 学生id
     * @return
     */
    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItem(@RequestParam("ids") List<Object> ids){
        try {
            this.studentService.deleteByIds(User.class,ids);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
