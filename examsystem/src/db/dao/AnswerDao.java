package db.dao;

import db.pojo.Answer;
import util.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 失去同步
 */
public class AnswerDao {

    public int insert(Answer[] answers) throws SQLException {
        Connection connection = MysqlUtil.getConnection();
        StringBuilder sql = new StringBuilder("insert into answer(stu_id,q_id,ans_text) values ");
        for (Answer answer : answers) {
            sql.append("(").append(answer.getStuId()).append(",")
                    .append(answer.getQId()).append(",").
                    append("\"answer:").append(answer.getAnsText()).append("\"),");
        }
        sql.delete(sql.length() - 1, sql.length());
        System.out.println(sql.toString());
        PreparedStatement statement;
        statement = connection.prepareStatement(sql.toString());
        return statement.executeUpdate();
    }
}
