package com.heima.tea.service;

import com.github.abel533.entity.Example;
import com.heima.tea.domain.ItemInfo;
import com.heima.tea.page.Page;
import com.heima.tea.vo.ItemQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class ItemService extends  BaseService<ItemInfo> {
    public Page<ItemInfo> findPagination(Page<ItemInfo> itemInfoPage, Class<ItemInfo> itemInfoClass, ItemQueryVo itemQueryVo, Integer itemType) {
        Example example = new Example(ItemInfo.class);
        example.createCriteria().andEqualTo("itemType", itemType);
        List<ItemInfo> itemInfos = getMapper().selectByExample(example);
        itemInfoPage.setTotalCount(itemInfos.size());
        itemInfoPage.setResult(itemInfos);
        return  itemInfoPage;
    }
}
