package client;

import client.util.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;


public class View extends JFrame {

    private JLabel label = new JLabel("Write notification");

    private JTextArea subjectArea = new JTextArea("Subject");

    private JTextArea messageArea = new JTextArea("Write your message");

    private JLabel dateLabel = new JLabel("<html>Choose date of notification</html>");

    private JLabel emailLabel = new JLabel("<html>Write your E-mail</html>");

    private JTextArea emailArea = new JTextArea();

    private JButton jButton = new JButton("Send");

    View(){
        super("Notification");
    }

    private JDatePickerImpl createDate() {
        UtilDateModel model = new UtilDateModel();
        Properties pr = new Properties();
        pr.put("text.today", "Today");
        pr.put("text.month", "Month");
        pr.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, pr);
        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    void createView() {
        this.setSize(500, 400);
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        label.setBounds(35, 10, 250, 20);
        this.add(label);

        subjectArea.setBounds(25, 35, 250,20);
        this.add(subjectArea);

        messageArea.setBounds(25, 65, 430, 100);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        this.add(messageArea);

        dateLabel.setBounds(35, 180, 200, 20);
        this.add(dateLabel);

        JDatePickerImpl date = createDate();
        date.setBounds(25, 205, 200, 30);
        this.add(date);

        emailLabel.setBounds( 250, 180, 200, 20);
        this.add(emailLabel);

        emailArea.setBounds(250, 205, 200, 20);
        this.add(emailArea);

        jButton.setBounds(175, 270, 150, 40);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year =  date.getModel().getYear();
                int month =  date.getModel().getMonth();
                int day =  date.getModel().getDay();
                String subject = subjectArea.getText();
                String message = messageArea.getText();
                String email = emailArea.getText();

                SendService sendService = new SendService();
                sendService.send(subject, message, email, year, month, day);
            }
        });
        this.add(jButton);
    }
}
