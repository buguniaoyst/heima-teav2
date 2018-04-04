package com.heima.tea.domain;

import javax.persistence.*;

/**
 * @author 布谷鸟
 * @version V2.0
 */
@Entity
@Table(name = "t_test_source")
public class TestSource extends BasePojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    /**
     * 测试试卷名
     */
    private  String  testName;
    /**
     * 测试卷类型：1-课后作业卷   2-课后测试卷
     */
    private  Integer  testType;
    /**
     * 试卷状态：1-正常使用  2-禁用
     */
    private  Integer testStatus  = 1;
    /**
     * 题目id
     */
    private  String testItemIds;
    /**
     * 关联的课程模块
     */
    private  String testSourcesId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public Integer getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(Integer testStatus) {
        this.testStatus = testStatus;
    }

    public String getTestItemIds() {
        return testItemIds;
    }

    public void setTestItemIds(String testItemIds) {
        this.testItemIds = testItemIds;
    }

    public String getTestSourcesId() {
        return testSourcesId;
    }

    public void setTestSourcesId(String testSourcesId) {
        this.testSourcesId = testSourcesId;
    }


    @Override
    public String toString() {
        return "TestSource{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", testType=" + testType +
                ", testStatus=" + testStatus +
                ", testItemIds='" + testItemIds + '\'' +
                ", testSourcesId='" + testSourcesId + '\'' +
                '}';
    }
}
