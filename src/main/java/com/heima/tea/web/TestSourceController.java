package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.domain.TestSource;
import com.heima.tea.page.Page;
import com.heima.tea.service.TestSourceServie;
import com.heima.tea.vo.ClassQueryVo;
import com.heima.tea.vo.TestSourceQueryVo;
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
@RequestMapping("test_source")
public class TestSourceController extends BaseController{

    @Autowired
    private TestSourceServie testSourceServie;
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<TestSource> list(Page<TestSource> testSourcePage, TestSourceQueryVo testSourceQueryVo) {
        testSourcePage = testSourceServie.findPagination(testSourcePage, TestSource.class, testSourceQueryVo);
        return layuiTable(testSourcePage);
    }

}
