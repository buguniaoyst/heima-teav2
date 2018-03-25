package com.heima.tea.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_class")
public class ClassInfo  extends  BasePojo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer testId;
    private String className;
    private Integer classType;//班级类型  0-基础班  1-就业班
    private String assistant;//助教姓名
    private String masterName;//班主任姓名
    private Integer studentCount;
    private Date startDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "id=" + id +
                ", testId=" + testId +
                ", className='" + className + '\'' +
                ", classType=" + classType +
                ", assistant='" + assistant + '\'' +
                ", masterName='" + masterName + '\'' +
                ", studentCount=" + studentCount +
                ", startDate=" + startDate +
                '}';
    }
}
