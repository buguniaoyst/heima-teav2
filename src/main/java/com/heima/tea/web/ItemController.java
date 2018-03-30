package com.heima.tea.web;

import com.heima.tea.common.LayUITable;
import com.heima.tea.controller.BaseController;
import com.heima.tea.domain.ItemInfo;
import com.heima.tea.page.Page;
import com.heima.tea.service.ItemService;
import com.heima.tea.vo.ItemQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "itemAddOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity itemAddOrUpdate(ItemInfo itemInfo){
        System.out.println("itemInfo = " + itemInfo);
        int rows = 0;
        if(itemInfo.getId()==null){
            rows = this.itemService.save(itemInfo);
        }else{
            rows = this.itemService.updateSelectiveById(itemInfo);
        }
        if(rows==0){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加或者更新失败");
        }
        return ResponseEntity.status(HttpStatus.OK).body("添加或更新题目成功");
    }

    @RequestMapping(value = "deleteItem",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteItem(Integer id){
        System.out.println("id = " + id);
        if(id==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("请提供删除的id");
        }
        Integer rows = this.itemService.deleteById(id);
        if(rows==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("没有内容可以删除");
        }
        return ResponseEntity.ok("成功");
    }

}
