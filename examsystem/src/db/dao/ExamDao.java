package db.dao;

import db.pojo.Exam;
import util.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 失去同步
 */
public class ExamDao {


    public ArrayList<Exam> getExams() {
        Connection connection = MysqlUtil.getConnection();
        String sql = "select exam_id,exam_name,exam_start_time,exam_finish_time,exam_questions from exam";
        PreparedStatement preparedStatement;
        ArrayList<Exam> exams = new ArrayList<>();
        Exam exam;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                exam = new Exam();
                exam.setExamId(resultSet.getInt(1));
                exam.setExamName(resultSet.getString(2));
                exam.setExamStartTime(resultSet.getTimestamp(3));
                exam.setExamFinishTime(resultSet.getTimestamp(4));
                String[] ids = resultSet.getString(5).split(",");
                exam.setExamQuestions(ids);
                exams.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }
}
