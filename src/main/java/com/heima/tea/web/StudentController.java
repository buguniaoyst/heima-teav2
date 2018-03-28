package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.domain.StudentInfo;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.service.StudentService;
import com.heima.tea.utils.ExcelUtils;
import com.heima.tea.vo.StudentQueryVo;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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


    @RequestMapping(value = "importExcel",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity importExcel(MultipartFile excelFile,Integer classId){
        //判断文件是否有内容
        if(excelFile.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("文件上传有误");
        }
        //根据上传的文件获取输入流
        InputStream in;
        try {
            in = excelFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件解析有误");
        }
        //解析上传的文件
        List<List<Object>> list = null;
        try {
            list = ExcelUtils.getDataFromExcel(in,excelFile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("解析Excel文件有误");
        }
        //去除第一行无效数据
        list.remove(0);
        System.out.println(list);
        if(list.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("没有数据");
        }
        //调用Service访问完成学生信息的保存
        studentService.importStudent(list,classId);
        return ResponseEntity.ok(200);
    }



}
