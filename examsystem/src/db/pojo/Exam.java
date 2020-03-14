package db.pojo;

import java.sql.Timestamp;

public class Exam {
    /**
     *
     */
    private Integer examId;

    /**
     *
     */
    private String examName;

    /**
     *
     */
    private Timestamp examStartTime;

    /**
     *
     */
    private Timestamp examFinishTime;

    /**
     *
     */
    private String[] examQuestions;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Timestamp getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(Timestamp examStartTime) {
        this.examStartTime = examStartTime;
    }

    public Timestamp getExamFinishTime() {
        return examFinishTime;
    }

    public void setExamFinishTime(Timestamp examFinishTime) {
        this.examFinishTime = examFinishTime;
    }

    public String[] getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(String[] examQuestions) {
        this.examQuestions = examQuestions;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", examStartTime=" + examStartTime +
                ", examFinishTime=" + examFinishTime +
                ", examQuestions='" + examQuestions + '\'' +
                '}';
    }
}

