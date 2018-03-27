package com.heima.tea.service;

import com.heima.tea.domain.ClassInfo;
import com.heima.tea.domain.StudentInfo;
import com.heima.tea.page.Page;
import com.heima.tea.vo.StudentQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class StudentService extends  BaseService<StudentInfo> {
    public Page<StudentInfo> findPagination(Page<StudentInfo> studentInfoPage, Class<ClassInfo> classInfoClass, StudentQueryVo studentQueryVo) {
        List<StudentInfo> studentInfos = queryAll();
        studentInfoPage.setTotalCount(studentInfos.size());
        studentInfoPage.setResult(studentInfos);
        return studentInfoPage;
    }
}
