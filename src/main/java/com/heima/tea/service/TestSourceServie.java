package com.heima.tea.service;

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

    public Page<TestSource> findPagination(Page<TestSource> testSourcePage, Class<TestSource> testSourceClass, TestSourceQueryVo testSourceQueryVo) {
        List<TestSource> testSources = queryAll();
        testSourcePage.setTotalCount(testSources.size());
        testSourcePage.setResult(testSources);
        return  testSourcePage;
    }
}
