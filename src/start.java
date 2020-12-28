import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class start extends JTextField{

    private JButton goButton;
    private JPanel mainPanel;
    private JTextField yearField;
    private JTextField teamField;
    private JTextField eventField;
    private JTextField choose2;
    private JTextField choose3;
    private JTextField choose4;
    private JTextField choose0;
    private JTextField choose1;
    private JCheckBox debugVal;
    private JCheckBox cliVal;
    private JLabel versionLabel;

    public start () {
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cli_selector cli1 = new cli_selector();
                int year = 0;
                if (yearField.getText().equals("")) {
                    System.out.println("Year required.");
                    System.exit(2);
                } else
                    year = Integer.parseInt(yearField.getText());
                boolean teamB = true;
                int team = 0;
                if (teamField.getText().equals(""))
                    teamB = false;
                else
                    team = Integer.parseInt(teamField.getText());
                boolean eventB = true;
                String event = null;
                if (teamField.getText() == null)
                    eventB = false;
                else
                    event = eventField.getText();
                int choose0Int = 0;
                if (choose0.getText().matches("[0-9]+"))
                    choose0Int = Integer.parseInt(choose0.getText());
                else {
                    System.out.println("The First Quick Code must be an integer!");
                    System.exit(1);
                }
                String urlstr = cli1.urlselect(teamB, eventB, year, event, team, choose0Int, choose1.getText(), choose2.getText(), choose3.getText(), choose4.getText());
                call call1 = new call();
                call1.caller(urlstr, debugVal.isSelected());
                if (!cliVal.isSelected())
                    results.UI_ReturnData(debugVal.isSelected(), urlstr, call1.allKey, call1.allVal, call1.allInfo, call1.index);
                else
                    results.TERM_ReturnData(debugVal.isSelected(), call1.allKey, call1.allVal, call1.index);
            }
        });
        debugVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (debugVal.isSelected())
                debugVal.setForeground(Color.RED);
                else
                    debugVal.setForeground(Color.BLACK);
            }
        });
        cliVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (cliVal.isSelected())
                    cliVal.setForeground(Color.RED);
                else
                    cliVal.setForeground(Color.BLACK);
            }
        });
    }
    public static void main() {
        JFrame frame = new JFrame("start");
        frame.setContentPane(new start().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        yearField = new JTextField();
        prefHelper pref1 = new prefHelper();
        if (pref1.ifYear())
            yearField.setText(String.valueOf(pref1.getYear()));
        else
            yearField = new hinter("year");
        eventField = new hinter("event code");
        teamField = new hinter("team #");
        choose0 = new hinter("Code 0");
        choose1 = new hinter("Code 1");
        choose2 = new hinter("Code 2");
        choose3 = new hinter("Code 3");
        choose4 = new hinter("Code 4");
    }
}
