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
    private  String  testName;
    private  String  testType;
    private  String testStatus = "1";
    private  String testItemIds;
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

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
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
                ", testType='" + testType + '\'' +
                ", testStatus='" + testStatus + '\'' +
                ", testItemIds='" + testItemIds + '\'' +
                ", testSourcesId='" + testSourcesId + '\'' +
                '}';
    }
}
