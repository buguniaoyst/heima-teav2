package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.service.UserService;
import com.heima.tea.vo.UserQueryVo;
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

    @RequestMapping(value="addOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> addUser(User user){
        try {
            Integer number=0;
            if(user.getId()==null){
                number= this.userService.save(user);
            }else {
                number= this.userService.updateSelectiveById(user);
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

    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItem(@RequestParam("ids") List<Object> ids){
        try {
            this.userService.deleteByIds(User.class,ids);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
