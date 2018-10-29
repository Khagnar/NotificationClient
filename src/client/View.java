package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JTextField yearField = new JTextField("1999");
    private JTextField monthField = new JTextField("1");
    private JTextField dayField = new JTextField("1");
    private JTextField hourField = new JTextField("1");
    private JTextField minutesField = new JTextField("1");

    private JLabel headLabel = new JLabel("Введите дату отправки оповещения");
    private JLabel admonitionLabel = new JLabel("<html>Для ввода данных используйте только числа и только корректные значения</html>");
    private JLabel yearLabel = new JLabel("Год");
    private JLabel monthLabel = new JLabel("Месяц");
    private JLabel dayLabel = new JLabel("День");
    private JLabel hourLabel = new JLabel("Час");
    private JLabel minutesLabel = new JLabel("Минута");

    private JButton jButton = new JButton("Отправить");

    private Time time;

    public Time getTime() {
        return time;
    }

    public View(){
        super("Notification");
    }

    public void createView() {
        this.setSize(300, 300);
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        yearField.setBounds(120, 70, 100, 20);
        this.add(yearField);
        monthField.setBounds(120, 100, 100, 20);
        this.add(monthField);
        dayField.setBounds(120, 130, 100, 20);
        this.add(dayField);
        hourField.setBounds(120, 160, 100, 20);
        this.add(hourField);
        minutesField.setBounds(120, 190, 100, 20);
        this.add(minutesField);

        headLabel.setBounds(35, 15, 250, 20);
        this.add(headLabel);
        admonitionLabel.setBounds(35, 30, 250, 40);
        this.add(admonitionLabel);
        yearLabel.setBounds(50, 70, 50, 20);
        this.add(yearLabel);
        monthLabel.setBounds(50, 100, 50, 20);
        this.add(monthLabel);
        dayLabel.setBounds(50, 130, 50, 20);
        this.add(dayLabel);
        hourLabel.setBounds(50, 160, 50, 20);
        this.add(hourLabel);
        minutesLabel.setBounds(50, 190, 50, 20);
        this.add(minutesLabel);

        jButton.setBounds(90, 230, 120, 30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time = new Time();
                time.setYear(Integer.parseInt(yearField.getText()));
                time.setMonth(Integer.parseInt(monthField.getText()));
                time.setDay(Integer.parseInt(dayField.getText()));
                time.setHour(Integer.parseInt(hourField.getText()));
                time.setMinutes(Integer.parseInt(minutesField.getText()));
                SendService sendService = new SendService(9999);
                sendService.send(time);
            }
        });
        this.add(jButton);
    }
}
