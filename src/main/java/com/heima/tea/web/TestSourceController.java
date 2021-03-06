package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.domain.TestSource;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.service.TestSourceServie;
import com.heima.tea.vo.ClassQueryVo;
import com.heima.tea.vo.TestSourceQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Controller
@RequestMapping("test_source")
public class TestSourceController extends BaseController{

    @Autowired
    private TestSourceServie testSourceServie;
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<TestSource> list(Page<TestSource> testSourcePage, Integer testType,TestSourceQueryVo testSourceQueryVo) {
        testSourcePage = testSourceServie.findPagination(testSourcePage,testType, TestSource.class, testSourceQueryVo);
        return layuiTable(testSourcePage);
    }

    @RequestMapping(value="addOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> addUser(TestSource testSource){
        try {
            Integer number=0;
            if(testSource.getId()==null){
                number= this.testSourceServie.save(testSource);
            }else {
                number= this.testSourceServie.updateSelectiveById(testSource);
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


}
