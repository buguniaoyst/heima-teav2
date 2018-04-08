package com.heima.tea.service;

import com.github.abel533.entity.Example;
import com.heima.tea.domain.TestSource;
import com.heima.tea.page.Page;
import com.heima.tea.vo.TestSourceQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class TestSourceServie extends  BaseService<TestSource>{

    public Page<TestSource> findPagination(Page<TestSource> testSourcePage, Integer testType, Class<TestSource> testSourceClass, TestSourceQueryVo testSourceQueryVo) {
        Example example = new Example(TestSource.class);
        //获取testType
        if( null!= testType){
            example.createCriteria().andEqualTo("testType", testType);
        }
        List<TestSource> testSources = getMapper().selectByExample(example);
        testSourcePage.setTotalCount(testSources.size());
        testSourcePage.setResult(testSources);
        return  testSourcePage;
    }
}
