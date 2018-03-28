package com.heima.tea.service;

import com.github.abel533.entity.Example;
import com.heima.tea.domain.ClassInfo;
import com.heima.tea.domain.StudentInfo;
import com.heima.tea.page.Page;
import com.heima.tea.vo.StudentQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentService extends  BaseService<StudentInfo> {
    public Page<StudentInfo> findPagination(Page<StudentInfo> studentInfoPage, Class<ClassInfo> classInfoClass, StudentQueryVo studentQueryVo) {
        List<StudentInfo> studentInfos = queryAll();
        studentInfoPage.setTotalCount(studentInfos.size());
        studentInfoPage.setResult(studentInfos);
        return studentInfoPage;
    }

    public void importStudent(List<List<Object>> list, Integer classId) {
        //先清除之前的信息在进行导入
        Example example = new Example(StudentInfo.class);
        example.createCriteria().andEqualTo("classId",classId);
        this.getMapper().deleteByExample(example);
        //对原来的数据进行封装
        for(int i=0;i<list.size();i++){
            List<Object> value = list.get(i);
            StudentInfo student = new StudentInfo();
            student.setClassId(classId);
            student.setTestId(0);
            if (null != value.get(0)) {
                student.setStudentName(String.valueOf(value.get(0)));
            }
            if (null != value.get(1)) {
                //性别
                String sexStr = String.valueOf(value.get(1));
                if ("男".equals(sexStr)) {
                    student.setSex(1);
                }else {
                    student.setSex(2);
                }
            }
            //学号
            if (null != value.get(2)) {
                student.setStudentNo(String.valueOf(value.get(2)));
            }
            //学科
            if (null != value.get(3)) {
                String subjectStr = String.valueOf(value.get(3));
                if ("Java".equals(subjectStr)) {
                    student.setSubjectNo(1);
                }else {
                    student.setSubjectNo(2);
                }
            }
            student.setPassword("123456");
            this.save(student);
        }
    }
}
