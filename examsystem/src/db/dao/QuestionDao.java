package db.dao;

import db.pojo.Question;
import util.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 失去同步
 */
public class QuestionDao {

    public Question[] getQuestionsByIds(String[] examQuestions) {
        Connection connection = MysqlUtil.getConnection();
        String array = Arrays.toString(examQuestions).replaceAll("\\[", "(").
                replaceAll("]", ")");
        String sql = "select q_id,q_text,q_socre,q_option_num,q_options,q_answer from question " +
                "where q_id in " + array;
        ArrayList<Question> questions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setQId(resultSet.getInt(1));
                question.setQText(resultSet.getString(2));
                question.setQSocre(resultSet.getString(3));
                question.setQOptionNum(resultSet.getInt(4));
                question.setQOptions(resultSet.getString(5));
                question.setQAnswer(resultSet.getInt(6));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions.toArray(new Question[0]);
    }
}
