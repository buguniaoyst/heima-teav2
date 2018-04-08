package com.heima.tea.service;

import com.github.abel533.entity.Example;
import com.heima.tea.dao.ItemInfoMapper;
import com.heima.tea.domain.ItemInfo;
import com.heima.tea.page.Page;
import com.heima.tea.vo.ItemQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class ItemService extends  BaseService<ItemInfo> {

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    public Page<ItemInfo> findPagination(Page<ItemInfo> itemInfoPage, Class<ItemInfo> itemInfoClass, ItemQueryVo itemQueryVo, Integer itemType) {
        Example example = new Example(ItemInfo.class);
        example.createCriteria().andEqualTo("itemType", itemType);
        List<ItemInfo> itemInfos = getMapper().selectByExample(example);
        itemInfoPage.setTotalCount(itemInfos.size());
        itemInfoPage.setResult(itemInfos);
        return  itemInfoPage;
    }

    public Map<String,List<ItemInfo>> getItemsByItemIds(String[] ids) {

        List<ItemInfo> itemInfos = itemInfoMapper.getItemsByItemIds(ids);
        if (null != itemInfos && itemInfos.size() > 0) {

            //创建三种题型的集合
            Map<String, List<ItemInfo>> itemInfosMap = new HashMap<>();
            //单选题
            List<ItemInfo> singleItemInfo = new ArrayList<>();
            //多选题
            List<ItemInfo> multiItemInfo = new ArrayList<>();
            //代码提
            List<ItemInfo> codeItemInfo = new ArrayList<>();

            for (ItemInfo i : itemInfos) {
                if(0 == i.getItemType()){
                    codeItemInfo.add(i);
                } else if (1 == i.getItemType()) {
                    singleItemInfo.add(i);
                } else if (2 == i.getItemType()) {
                    multiItemInfo.add(i);
                }
            }

            //将三种题型封装到Map中
            itemInfosMap.put("singleItems", singleItemInfo);
            itemInfosMap.put("multiItems", multiItemInfo);
            itemInfosMap.put("codeItems", codeItemInfo);
            return  itemInfosMap;
        }

        return  null;
    }
}
