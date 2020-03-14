package db.pojo;

import java.io.Serializable;

/**
 * @author author
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1584083345923L;


    /**
     * 主键
     * 老师id
     * isNullAble:0
     */
    private Integer tchId;

    /**
     * 老师姓名
     * isNullAble:0
     */
    private String tchName;

    /**
     * 老师密码
     * isNullAble:0
     */
    private String password;

    public Integer getTchId() {
        return this.tchId;
    }

    public void setTchId(Integer tchId) {
        this.tchId = tchId;
    }

    public String getTchName() {
        return this.tchName;
    }

    public void setTchName(String tchName) {
        this.tchName = tchName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tchId='" + tchId + '\'' +
                "tchName='" + tchName + '\'' +
                "PASSWORD='" + password + '\'' +
                '}';
    }
}
