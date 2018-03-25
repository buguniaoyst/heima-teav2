package com.heima.tea.dao;

import com.github.abel533.mapper.Mapper;
import com.heima.tea.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author 布谷鸟
 * @version V1.0
 */
public interface UserMapper extends Mapper<User> {
    /**
     * 分页查询
     * @param from 起始
     * @param to   结束
     * @return
     */
    List<User> queryUserLimit(@Param("from") int from, @Param("to") int to);
}
