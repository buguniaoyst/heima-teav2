package com.heima.tea.service;

import com.heima.tea.domain.ItemInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class ItemService extends  BaseService<ItemInfo> {
}
