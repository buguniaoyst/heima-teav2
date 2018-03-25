package com.heima.tea.service;

import com.github.abel533.entity.Example;
import com.heima.tea.dao.UserMapper;
import com.heima.tea.domain.User;
import com.heima.tea.page.Page;
import com.heima.tea.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V1.0
 */
@Service
@Transactional
public class UserService extends BaseService<User>{
    @Autowired
    private UserMapper userMapper;

    public Page<User> findPagination(Page<User> userPage, Class<User> userClass, UserQueryVo userQueryVo) {
        int pageNo = userQueryVo.getPageNo();
        int pageSize = userQueryVo.getLimit();
        int from = (pageNo-1)*pageSize;
        int to = pageNo*pageSize;

        List<User> limitUser = getUserLimit(from, to);
        userPage.setResult(limitUser);
        userPage.setTotalCount(getCountFromTable());

        return  userPage;
    }

    public List<User> userListQuery() {
        return this.queryAll();
    }


    public List<User> getUserLimit(int from, int to) {
        return userMapper.queryUserLimit(from, to);
    }


    public int getCountFromTable(){
        Example example = new Example(User.class);
        return getMapper().selectCountByExample(example);
    }



}
