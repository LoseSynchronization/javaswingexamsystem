package db.dao;

import db.pojo.Student;
import util.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 失去同步
 */
public class StudentDao {


    public Student getStudent(Student student) {
        Connection connection = MysqlUtil.getConnection();
        String sql = "select stu_id,stu_name,school_id from student " +
                "where stu_name = ? " +
                "and school_id = ? ";
        if (student.getPassword() != null){
            sql += "and password = PASSWORD(?)";
        }
        Student s = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getStuName());
            statement.setString(2, student.getSchoolId());
            if (student.getPassword() != null){
                statement.setString(3, student.getPassword());
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                s = new Student();
                s.setStuId(resultSet.getInt(1));
                s.setStuName(resultSet.getString(2));
                s.setSchoolId(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean insert(Student student) {
        Connection connection = MysqlUtil.getConnection();
        String sql = "insert into student(stu_name, password, school_id) " +
                "values (?,PASSWORD(?),?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getStuName());
            statement.setString(2, student.getPassword());
            statement.setString(3, student.getSchoolId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
