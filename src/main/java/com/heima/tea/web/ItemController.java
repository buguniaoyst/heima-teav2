package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ItemInfo;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.service.ItemService;
import com.heima.tea.vo.ItemQueryVo;
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
@RequestMapping("item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public LayUITable<ItemInfo> list(Page<ItemInfo> itemInfoPage, ItemQueryVo itemQueryVo,Integer itemType) {
        //查找非管理员
        itemInfoPage = itemService.findPagination(itemInfoPage, ItemInfo.class, itemQueryVo,itemType);
        return layuiTable(itemInfoPage);
    }



}
