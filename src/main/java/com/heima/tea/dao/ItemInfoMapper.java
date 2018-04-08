package com.heima.tea.dao;

import com.github.abel533.mapper.Mapper;
import com.heima.tea.domain.ItemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
public interface ItemInfoMapper extends Mapper<ItemInfo> {
    List<ItemInfo> getItemsByItemIds(@Param("ids") String[] ids);
}
