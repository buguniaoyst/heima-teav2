package com.heima.tea.service;

import com.heima.tea.domain.ClassInfo;
import com.heima.tea.page.Page;
import com.heima.tea.vo.ClassQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class ClassService extends  BaseService<ClassInfo>{
    public Page<ClassInfo> findPagination(Page<ClassInfo> classPage, Class<ClassInfo> classInfoClass, ClassQueryVo classQueryVo) {
        List<ClassInfo> classInfos = queryAll();
        classPage.setRawResult(classInfos);
        classPage.setTotalCount(classInfos.size());
        return  classPage;
    }


}
