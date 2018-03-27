package com.heima.tea.service;

import com.heima.tea.domain.CourseModule;
import com.heima.tea.page.Page;
import com.heima.tea.vo.CourseModuleQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class CourseModuleService extends  BaseService<CourseModule>{

    public Page<CourseModule> findPagination(Page<CourseModule> coursePage, Class<CourseModule> courseModuleClass, CourseModuleQueryVo courseModuleQueryVo) {
        List<CourseModule> courseModules = queryAll();
        coursePage.setTotalCount(courseModules.size());
        coursePage.setResult(courseModules);
        return  coursePage;
    }
}
