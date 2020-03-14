package db.service;

import db.dao.StudentDao;
import db.pojo.Student;

/**
 * @author 失去同步
 */
public class RegisterService {

    private StudentDao studentDao = new StudentDao();

    public String register(Student student) {
        String password = student.getPassword();
        student.setPassword(null);
        Student s = studentDao.getStudent(student);
        if (s == null){
            student.setPassword(password);
            return studentDao.insert(student) ? null : "注册失败";
        }
        return "用户已存在";
    }
}
