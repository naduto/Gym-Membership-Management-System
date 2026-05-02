package FrontEnd;

import BackEnd.TrainerRole;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class RegisterMemberforClassWindow extends JFrame {

    private JTextField memberIDText;
    private JTextField classIDText;
    private JSpinner dateSpinner;
    private JButton addBtn;
    private JButton backBtn;

    public RegisterMemberforClassWindow() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel memberLabel = new JLabel("Member ID");
        memberLabel.setBounds(30, 30, 100, 30);

        memberIDText = new JTextField();
        memberIDText.setBounds(150, 30, 150, 30);

        JLabel classLabel = new JLabel("Class ID");
        classLabel.setBounds(30, 80, 100, 30);

        classIDText = new JTextField();
        classIDText.setBounds(150, 80, 150, 30);

        JLabel dateLabel = new JLabel("Registration Date");
        dateLabel.setBounds(30, 130, 120, 30);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy");
        dateSpinner.setEditor(editor);
        dateSpinner.setBounds(150, 130, 150, 30);

        backBtn = new JButton("Back");
        backBtn.setBounds(50, 200, 100, 40);

        addBtn = new JButton("Add");
        addBtn.setBounds(180, 200, 100, 40);

        panel.add(memberLabel);
        panel.add(memberIDText);
        panel.add(classLabel);
        panel.add(classIDText);
        panel.add(dateLabel);
        panel.add(dateSpinner);
        panel.add(backBtn);
        panel.add(addBtn);

        add(panel);

        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Register Member for Class");

        backBtn.addActionListener(e -> backBtnActionPerformed());
        addBtn.addActionListener(e -> addBtnActionPerformed());
    }

    private void backBtnActionPerformed() {
        new TrainerRoleWindow();
        setVisible(false);
    }

    private void addBtnActionPerformed() {

        if (memberIDText.getText().isEmpty() || classIDText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Some Fields are Empty!");
            return;
        }

        try {
            TrainerRole trainer = new TrainerRole();

            Date selectedDate = (Date) dateSpinner.getValue();
            LocalDate localDate = selectedDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            trainer.registerMemberForClass(
                    memberIDText.getText(),
                    classIDText.getText(),
                    localDate);

            trainer.logout();

            new TrainerRoleWindow();
            setVisible(false);

        } catch (IOException ex) {
            Logger.getLogger(RegisterMemberforClassWindow.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterMemberforClassWindow::new);
    }
}