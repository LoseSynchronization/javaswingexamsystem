package db.service;

import db.dao.StudentDao;
import db.pojo.Student;

/**
 * @author 失去同步
 */
public class LoginService {

    private StudentDao studentDao = new StudentDao();

    public Integer login(Student student) {
        Student s = studentDao.getStudent(student);
        return s != null ? s.getStuId() : null;
    }
}
