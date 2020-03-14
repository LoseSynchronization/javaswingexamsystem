package frame;

import db.dao.AnswerDao;
import db.dao.ExamDao;
import db.dao.QuestionDao;
import db.pojo.Answer;
import db.pojo.Exam;
import db.pojo.Question;
import db.pojo.Student;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 * @author 失去同步
 */
public class MainFrame extends JFrame implements ActionListener {
    private Student student;
    private Exam currentExam;
    private ArrayList<Exam> exams;
    private Question[] questions;
    private Answer[] answers;
    private int currentQuestion;
    private boolean isExaming = false;
    private ExamDao examDao = new ExamDao();
    private QuestionDao questionDao = new QuestionDao();
    private AnswerDao answerDao = new AnswerDao();
    private Date currentDate;
    private String[] columnNames = {"ID", "名称", "开始时间", "结束时间", "题目数"};

    private JLabel leftTimeLabel = new JLabel("剩余时间：00:00:00");
    private JLabel currentTimeLabel = new JLabel();
    private JLabel questionLabel = new JLabel("当前题目: 0/0");
    private JTable examTable;
    private JTextArea questionArea = new MyTextArea();
    private JTextArea answerArea = new MyTextArea();
    private JButton preQuestionButton = new JButton("上一题");
    private JButton nextQuestionButton = new JButton("下一题");
    private JButton submitButton = new JButton("提交");
    private JButton beginButton = new JButton("开始考试");
    private JButton refreshButton = new JButton("刷新");
    private JButton quitExamButton = new JButton("退出考试");
    private JScrollPane examTableScrollPane;

    private Box leftVBox = Box.createVerticalBox();
    private Box rightVBox = Box.createVerticalBox();

    public MainFrame(Student student) {
        this.student = student;
        init();
    }

    private void setListener() {
        preQuestionButton.addActionListener(this);
        nextQuestionButton.addActionListener(this);
        submitButton.addActionListener(this);
        beginButton.addActionListener(this);
        refreshButton.addActionListener(this);
        quitExamButton.addActionListener(this);
    }

    private void setLeftVBox() {
        examTableScrollPane = new JScrollPane(examTable);
        refreshExams();
        examTableScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        examTableScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        examTableScrollPane.setPreferredSize(new Dimension(this.getWidth() / 5 * 2, this.getHeight()));

        Box hbox = Box.createHorizontalBox();
        JLabel examTableLabel = new JLabel("考试列表");
        examTableLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        hbox.add(examTableLabel);
        hbox.add(Box.createHorizontalStrut(10));
        hbox.add(refreshButton);
        hbox.add(Box.createHorizontalStrut(10));
        hbox.add(beginButton);
        hbox.add(Box.createHorizontalStrut(10));
        quitExamButton.setEnabled(false);
        hbox.add(quitExamButton);

        leftVBox.add(Box.createVerticalStrut(10));
        leftVBox.add(hbox);
        leftVBox.add(Box.createGlue());
        leftVBox.add(examTableScrollPane);
        leftVBox.add(Box.createVerticalStrut(10));
        leftVBox.setPreferredSize(new Dimension(this.getWidth() / 5 * 2, this.getHeight()));
    }

    private void setRightVBox() {
        Font font = new Font(Font.DIALOG, Font.BOLD, 20);

        Box infoBox = Box.createHorizontalBox();
        JLabel schoolIdLabel = new JLabel("学号: " + student.getSchoolId());
        JLabel nameLabel = new JLabel("姓名: " + student.getStuName());
        currentTimeLabel.setFont(font);
        schoolIdLabel.setFont(font);
        nameLabel.setFont(font);
        infoBox.add(Box.createGlue());
        infoBox.add(schoolIdLabel);
        infoBox.add(Box.createGlue());
        infoBox.add(nameLabel);
        infoBox.add(Box.createGlue());
        infoBox.add(currentTimeLabel);
        infoBox.add(Box.createGlue());


        questionArea.setEditable(false);
        questionArea.setFont(font);
        Box questionBox = Box.createHorizontalBox();
        JLabel questionLabel = new JLabel("题目:");
        questionLabel.setFont(font);
        questionBox.add(questionLabel);
        questionBox.add(Box.createHorizontalStrut(10));
        questionBox.add(new JScrollPane(questionArea));
        questionBox.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight()));

        preQuestionButton.setFont(font);
        nextQuestionButton.setFont(font);
        submitButton.setFont(font);
        this.questionLabel.setFont(font);
        leftTimeLabel.setFont(font);
        Box operationBox = Box.createHorizontalBox();
        operationBox.add(Box.createGlue());
        operationBox.add(preQuestionButton);
        operationBox.add(Box.createVerticalStrut(10));
        operationBox.add(nextQuestionButton);
        operationBox.add(Box.createVerticalStrut(10));
        operationBox.add(submitButton);
        operationBox.add(Box.createVerticalStrut(10));
        operationBox.add(this.questionLabel);
        operationBox.add(Box.createVerticalStrut(10));
        operationBox.add(leftTimeLabel);
        operationBox.add(Box.createGlue());

        answerArea.setEditable(false);
        Box answerBox = Box.createHorizontalBox();
        JLabel answerLabel = new JLabel("答题:");
        answerLabel.setFont(font);
        answerBox.add(answerLabel);
        answerBox.add(Box.createHorizontalStrut(10));
        answerBox.add(new JScrollPane(answerArea));
        answerBox.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight()));

        rightVBox.add(Box.createVerticalStrut(10));
        rightVBox.add(infoBox);
        rightVBox.add(Box.createGlue());
        rightVBox.add(questionBox);
        rightVBox.add(Box.createVerticalStrut(10));
        rightVBox.add(operationBox);
        rightVBox.add(Box.createVerticalStrut(10));
        rightVBox.add(answerBox);
        rightVBox.add(Box.createGlue());
        rightVBox.add(Box.createVerticalStrut(10));
        rightVBox.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight()));
    }

    private void init() {
        this.setTitle("考试界面");
        this.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 600,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 300, 1200, 600);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        preQuestionButton.setEnabled(false);
        nextQuestionButton.setEnabled(false);
        submitButton.setEnabled(false);

        setListener();
        setLeftVBox();
        setRightVBox();

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalStrut(10));
        horizontalBox.add(leftVBox);
        horizontalBox.add(Box.createGlue());
        horizontalBox.add(rightVBox);
        horizontalBox.add(Box.createHorizontalStrut(10));

        this.add(horizontalBox);

        new TimeThread().start();

        this.setVisible(true);
    }

    private void refreshExams() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        exams = examDao.getExams();
        Exam[] array = this.exams.toArray(new Exam[0]);
        String[][] rowData = new String[this.exams.size()][5];

        for (int i = 0; i < rowData.length; i++) {
            rowData[i][0] = String.valueOf(array[i].getExamId());
            rowData[i][1] = array[i].getExamName();
            rowData[i][2] = format.format(array[i].getExamStartTime());
            rowData[i][3] = format.format(array[i].getExamFinishTime());
            rowData[i][4] = String.valueOf(array[i].getExamQuestions().length);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        examTable = new JTable(defaultTableModel);

        JTableHeader header = examTable.getTableHeader();
        //设置表头大小与字体
        header.setFont(new Font("等线", Font.BOLD, 18));
        //设置表格数据字体
        examTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        examTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        examTable.setSelectionBackground(Color.gray);
        examTable.setRowHeight(25);
        // 设置table内容居中
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        examTable.setDefaultRenderer(Object.class, tcr);
        examTableScrollPane.setViewportView(examTable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.equals(refreshButton)){
            refreshExams();
        }

        if (button.equals(beginButton)){
            int selectedRow = examTable.getSelectedRow();
            if (selectedRow >= 0){
                long currentTime = Objects.requireNonNull(Util.getInternetTime()).getTime();
                Exam exam = exams.get(selectedRow);
                long start = exam.getExamStartTime().getTime();
                long finish = exam.getExamFinishTime().getTime();
                if (currentTime - start < 0){
                    JOptionPane.showMessageDialog(this, "考试还未开始");
                    return;
                }
                if (currentTime - finish > 0){
                    JOptionPane.showMessageDialog(this, "考试已结束");
                    return;
                }
                beginExam(exam);
            } else {
                JOptionPane.showMessageDialog(this, "请选择一个考试");
            }
        }

        answers[currentQuestion - 1].setAnsText(answerArea.getText());

        if (button.equals(preQuestionButton)){
            currentQuestion--;
            if (currentQuestion <= 1){
                preQuestionButton.setEnabled(false);
            }
            nextQuestionButton.setEnabled(true);
        }

        if (button.equals(nextQuestionButton)){
            currentQuestion++;
            if (currentQuestion >= questions.length){
                nextQuestionButton.setEnabled(false);
            }
            preQuestionButton.setEnabled(true);

        }

        answerArea.setText(answers[currentQuestion - 1].getAnsText());

        questionLabel.setText(String.format("当前题目: %d/%d", currentQuestion, questions.length));
        questionArea.setText(questions[currentQuestion - 1].getQText()
                + " (" + questions[currentQuestion - 1].getQSocre() + "分)");

        if (button.equals(quitExamButton)){
            int i = JOptionPane.showConfirmDialog(this, "确认退出吗？你的答案将不会被保存！");
            if (i == 0){
                isExaming = false;
                leftTimeLabel.setText("剩余时间：00:00:00");
                questionLabel.setText("当前题目: 0/0");

                questionArea.setText("");
                answerArea.setText("");

                preQuestionButton.setEnabled(false);
                nextQuestionButton.setEnabled(false);
                submitButton.setEnabled(false);

                refreshButton.setEnabled(true);
                beginButton.setEnabled(true);
                quitExamButton.setEnabled(false);

            }
        }

        if (button.equals(submitButton)){
            long currentDateTime = currentDate.getTime();
            long time = currentExam.getExamFinishTime().getTime();
            if (currentDateTime > time && isExaming && answerArea.isEditable()){
                JOptionPane.showMessageDialog(this, "已超时！无法提交！");
                return;
            }
            String submitMsg = "";
            for (Answer answer : answers) {
                if ("".equals(answer.getAnsText().trim())){
                    submitMsg += "你还有题目未作答，";
                    break;
                }
            }
            submitMsg += "是否确认提交？";
            int i = JOptionPane.showConfirmDialog(this, submitMsg, "提示", JOptionPane.YES_NO_OPTION);
            if (i == 0){
                submit();
            }
        }
    }

    private void beginExam(Exam exam) {
        questions = questionDao.getQuestionsByIds(exam.getExamQuestions());
        List<Question> questionList = Arrays.asList(this.questions);
        Collections.shuffle(questionList);
        questions = questionList.toArray(new Question[0]);
        currentQuestion = 1;
        questionLabel.setText(String.format("当前题目: %d/%d", currentQuestion, this.questions.length));
        answers = new Answer[this.questions.length];

        Answer answer;
        for (int i = 0; i < this.questions.length; i++) {
            answer = new Answer();
            answer.setAnsText("");
            answer.setQId(this.questions[i].getQId());
            answer.setStuId(student.getStuId());
            answers[i] = answer;
        }

        currentExam = exam;

        isExaming = true;

        preQuestionButton.setEnabled(false);
        nextQuestionButton.setEnabled(true);
        answerArea.setEditable(true);
        submitButton.setEnabled(true);
        quitExamButton.setEnabled(true);
        examTableScrollPane.setEnabled(false);
        if (this.questions.length == 1){
            nextQuestionButton.setEnabled(false);
        }

        refreshButton.setEnabled(false);
        beginButton.setEnabled(false);
    }

    private void submit() {
        try {
            int i = answerDao.insert(answers);
            isExaming = false;
            leftTimeLabel.setText("剩余时间：00:00:00");
            questionLabel.setText("当前题目: 0/0");

            questionArea.setText("");
            answerArea.setText("");

            preQuestionButton.setEnabled(false);
            nextQuestionButton.setEnabled(false);
            submitButton.setEnabled(false);

            refreshButton.setEnabled(true);
            beginButton.setEnabled(true);
            quitExamButton.setEnabled(false);

            JOptionPane.showMessageDialog(this, "提交成功！已提交" + i + "道题！");
        } catch (SQLException e) {
            e.printStackTrace();
            Util.exceptionDialog(e, this);
        }
    }

    private void timeOver() {
        isExaming = false;
        JOptionPane.showMessageDialog(this, "考试已结束！请停止作答！");
        answerArea.setEditable(false);
    }

    private class TimeThread extends Thread {
        @Override
        public void run() {
            SimpleDateFormat format = new SimpleDateFormat("当前时间: yyyy-MM-dd HH:mm:ss");
            String labelStr;
            while (true) {
                currentDate = Util.getInternetTime();
                if (currentDate != null){
                    currentTimeLabel.setText(format.format(currentDate));
                    if (isExaming){
                        long currentDateTime = currentDate.getTime();
                        long finishTime = currentExam.getExamFinishTime().getTime();
                        long leftTime = finishTime - currentDateTime;
                        if (leftTime < 1000 * 60 * 10){
                            leftTimeLabel.setForeground(Color.red);
                        } else {
                            leftTimeLabel.setForeground(Color.black);
                        }
                        labelStr = String.format("剩余时间: %02d:%02d:%02d",
                                leftTime / 1000 / 60 / 60, leftTime / 1000 / 60 % 60, leftTime / 1000 % 60);
                        leftTimeLabel.setText(labelStr);
                        if (leftTime <= 0){
                            leftTime = 0;
                            labelStr = String.format("剩余时间: %02d:%02d:%02d",
                                    leftTime / 1000 / 60 / 60, leftTime / 1000 / 60 % 60, leftTime / 1000 % 60);
                            leftTimeLabel.setText(labelStr);
                            timeOver();
                            leftTimeLabel.setForeground(Color.black);
                        }
                    }
                }
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Util.exceptionDialog(e, MainFrame.this);
                }
            }
        }
    }

    private class MyTextArea extends JTextArea {
        @Override
        public void paste() {
        }
    }
}
