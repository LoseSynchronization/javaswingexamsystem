package frame;

import db.pojo.Student;
import db.service.RegisterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 失去同步
 */
public class RegisterFrame extends JFrame implements ActionListener {
    private Student student = new Student();
    private RegisterService registerService = new RegisterService();

    private JTextField idField = new JTextField(20);
    private JTextField nameField = new JTextField(20);
    private JTextField passwordField = new JTextField();

    private JButton loginButton = new JButton("去登录");
    private JButton registerButton = new JButton("注册");


    public RegisterFrame() throws HeadlessException {
        init();
    }

    private void init() {

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        JLabel jLabel = new JLabel("学号:  ");
        JLabel jLabel1 = new JLabel("姓名:  ");
        JLabel jLabel2 = new JLabel("密码:  ");

        Box hbox1 = Box.createHorizontalBox();
        Box hbox2 = Box.createHorizontalBox();
        Box hbox3 = Box.createHorizontalBox();
        Box hbox4 = Box.createHorizontalBox();

        hbox1.add(Box.createHorizontalStrut(20));
        hbox1.add(jLabel);
        hbox1.add(idField);
        hbox1.add(Box.createHorizontalStrut(20));
        hbox2.add(Box.createHorizontalStrut(20));
        hbox2.add(jLabel1);
        hbox2.add(nameField);
        hbox2.add(Box.createHorizontalStrut(20));
        hbox3.add(Box.createHorizontalStrut(20));
        hbox3.add(jLabel2);
        hbox3.add(passwordField);
        hbox3.add(Box.createHorizontalStrut(20));
        hbox4.add(loginButton);
        hbox4.add(Box.createHorizontalStrut(20));
        hbox4.add(registerButton);

        Box vbox = Box.createVerticalBox();
        vbox.add(Box.createVerticalStrut(10));
        vbox.add(new JLabel("注册"));
        vbox.add(Box.createVerticalStrut(15));
        vbox.add(hbox1);
        vbox.add(Box.createVerticalStrut(10));
        vbox.add(hbox2);
        vbox.add(Box.createVerticalStrut(10));
        vbox.add(hbox3);
        vbox.add(Box.createVerticalStrut(20));
        vbox.add(hbox4);
        vbox.add(Box.createVerticalStrut(30));

        this.add(vbox);

        this.setTitle("注册");
        this.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 125, 300, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.equals(registerButton)){
            student.setSchoolId(idField.getText());
            student.setStuName(nameField.getText());
            student.setPassword(passwordField.getText());
            if (student.hasEmptyProperty()){
                JOptionPane.showMessageDialog(this, "请输入完整信息");
                return;
            }
            String message = registerService.register(student);
            if (message != null){
            } else {
                JOptionPane.showMessageDialog(this, "注册成功！");
            }
        } else {
            this.dispose();
            new LoginFrame(student);
        }
    }
}
