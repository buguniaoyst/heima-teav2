package com.heima.tea.service;

import com.heima.tea.domain.CourseSchedule;
import com.heima.tea.page.Page;
import com.heima.tea.vo.CourseScheduleQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Service
@Transactional
public class CourseScheduleService extends  BaseService<CourseSchedule> {
    public Page<CourseSchedule> findPagination(Page<CourseSchedule> courseSchedulePage, Class<CourseSchedule> courseScheduleClass, CourseScheduleQueryVo courseScheduleQueryVo) {
        List<CourseSchedule> courseSchedules = queryAll();
        courseSchedulePage.setTotalCount(courseSchedules.size());
        courseSchedulePage.setResult(courseSchedules);
        return  courseSchedulePage;
    }
}
