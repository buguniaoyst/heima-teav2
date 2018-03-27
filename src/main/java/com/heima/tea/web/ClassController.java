package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.page.Page;
import com.heima.tea.service.ClassService;
import com.heima.tea.vo.ClassQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @ResponseBody
    public ResponseEntity classAddOrUpdate(ClassInfo classInfo) throws Exception{
        int count = 0 ;
        if(classInfo.getId()==null){
            count = classService.save(classInfo);
        }else{
            count = classService.updateSelectiveById(classInfo);
        }
        if(count==0){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加或者更新失败");
        }
        return ResponseEntity.ok(200);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteClass(Integer id){
        System.out.println("id = " + id);
        Integer count = classService.deleteById(id);
        if(count==0){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败");
        }
        return ResponseEntity.ok(200);
    }



}
