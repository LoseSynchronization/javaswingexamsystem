package db.pojo;

import java.io.Serializable;

/**
 * @author author
 */
public class Question implements Serializable {

    private static final long serialVersionUID = 1584083367258L;


    /**
     * 主键
     * 题目id
     * isNullAble:0
     */
    private Integer qId;

    /**
     * 题目内容
     * isNullAble:0
     */
    private String qText;

    /**
     * 题目满分
     * isNullAble:0
     */
    private String qSocre;

    /**
     * 题目选项数量，选择题此项不为0
     * isNullAble:0,defaultVal:0
     */
    private Integer qOptionNum;

    /**
     * 题目的所有选项，以分号';'隔开,只有选择题有此项
     * isNullAble:1
     */
    private String qOptions;

    /**
     * 题目第几项是答案，只有有选择题有此项
     * isNullAble:1
     */
    private Integer qAnswer;

    public Integer getQId() {
        return this.qId;
    }

    public void setQId(Integer qId) {
        this.qId = qId;
    }

    public String getQText() {
        return this.qText;
    }

    public void setQText(String qText) {
        this.qText = qText;
    }

    public String getQSocre() {
        return this.qSocre;
    }

    public void setQSocre(String qSocre) {
        this.qSocre = qSocre;
    }

    public Integer getQOptionNum() {
        return this.qOptionNum;
    }

    public void setQOptionNum(Integer qOptionNum) {
        this.qOptionNum = qOptionNum;
    }

    public String getQOptions() {
        return this.qOptions;
    }

    public void setQOptions(String qOptions) {
        this.qOptions = qOptions;
    }

    public Integer getQAnswer() {
        return this.qAnswer;
    }

    public void setQAnswer(Integer qAnswer) {
        this.qAnswer = qAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qId='" + qId + '\'' +
                "qText='" + qText + '\'' +
                "qSocre='" + qSocre + '\'' +
                "qOptionNum='" + qOptionNum + '\'' +
                "qOptions='" + qOptions + '\'' +
                "qAnswer='" + qAnswer + '\'' +
                '}';
    }
}
