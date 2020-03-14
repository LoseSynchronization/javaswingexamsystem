package db.pojo;

import java.io.Serializable;

/**
 * @author author
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1584083361923L;


    /**
     * 主键
     * 考生id
     * isNullAble:0
     */
    private Integer stuId;

    /**
     * 考生姓名
     * isNullAble:0
     */
    private String stuName;

    /**
     * 考生分数
     * isNullAble:0,defaultVal:0
     */
    private Float stuGrade;

    /**
     * 登录密码
     * isNullAble:0
     */
    private String password;

    /**
     * 学号
     * isNullAble:0
     */
    private String schoolId;

    public Integer getStuId() {
        return this.stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Float getStuGrade() {
        return this.stuGrade;
    }

    public void setStuGrade(Float stuGrade) {
        this.stuGrade = stuGrade;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                "stuName='" + stuName + '\'' +
                "stuGrade='" + stuGrade + '\'' +
                "PASSWORD='" + password + '\'' +
                "schoolId='" + schoolId + '\'' +
                '}';
    }

    public boolean hasEmptyProperty() {
        return ("".equals(stuName) ||
                "".equals(schoolId) ||
                "".equals(password));
    }
}
