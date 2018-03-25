package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.service.UserService;
import com.heima.tea.vo.UserQueryVo;
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
@RequestMapping("user")
public class UserController extends BaseController {


    @Autowired
    private UserService userService;



    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<User> list(Page<User> userPage, UserQueryVo userQueryVo) {
        //查找非管理员
        userPage = userService.findPagination(userPage, User.class, userQueryVo);
        return layuiTable(userPage);
    }

}
