package db.pojo;

import java.io.Serializable;

/**
 * @author author
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = 1584083371944L;


    /**
     * 主键
     * 回答id
     * isNullAble:0
     */
    private Integer ansId;

    /**
     * 学生id
     * isNullAble:0
     */
    private Integer stuId;

    /**
     * 题目id
     * isNullAble:0
     */
    private Integer qId;

    /**
     * 作答内容
     * isNullAble:0,defaultVal:
     */
    private String ansText;

    /**
     * 得分
     * isNullAble:1
     */
    private String ansScore;

    public Integer getAnsId() {
        return this.ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
    }

    public Integer getStuId() {
        return this.stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getQId() {
        return this.qId;
    }

    public void setQId(Integer qId) {
        this.qId = qId;
    }

    public String getAnsText() {
        return this.ansText;
    }

    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }

    public String getAnsScore() {
        return this.ansScore;
    }

    public void setAnsScore(String ansScore) {
        this.ansScore = ansScore;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "ansId='" + ansId + '\'' +
                "stuId='" + stuId + '\'' +
                "qId='" + qId + '\'' +
                "ansText='" + ansText + '\'' +
                "ansScore='" + ansScore + '\'' +
                '}';
    }
}
