import javax.swing.*;
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

    private final String ttYear = "year";
    private final String ttTeam = "team #";
    private final String ttEvent = "event code";
    private final String ttChoose0 = "Code #1";
    private final String ttChoose1 = "Code #2";
    private final String ttChoose2 = "Code #3";
    private final String ttChoose3 = "Code #4";
    private final String ttChoose4 = "Code #5";


    public start () {
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int year = cleanHints();
                if (year==-1)
                    return;
                cli_selector cli1 = new cli_selector();
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
                    startErr.main("The First Quick Code Must be an Integer!");
                    System.exit(1);
                }
                String urlstr = cli1.urlselect(teamB, eventB, year, event, team, choose0Int, choose1.getText(), choose2.getText(), choose3.getText(), choose4.getText(), true);
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

    public int cleanHints() {
        int year = 0;
        if (yearField.getText().equals("")|| yearField.getText().equals(ttYear)) {
            startErr.main("Year is required!");
            return -1;
        } else
            year = Integer.parseInt(yearField.getText());
        if (eventField.getText().equals(ttEvent))
            eventField.setText("");
        if (teamField.getText().equals(ttTeam))
            teamField.setText("");
        if (choose0.getText().equals(ttChoose0))
            choose0.setText("");
        if (choose1.getText().equals(ttChoose1))
            choose1.setText("");
        if (choose2.getText().equals(ttChoose2))
            choose2.setText("");
        if (choose3.getText().equals(ttChoose3))
            choose3.setText("");
        if (choose4.getText().equals(ttChoose4))
            choose4.setText("");
        return year;
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
            yearField = new hinter(ttYear);

        eventField = new hinter(ttEvent);
        teamField = new hinter(ttTeam);
        choose0 = new hinter(ttChoose0);
        choose1 = new hinter(ttChoose1);
        choose2 = new hinter(ttChoose2);
        choose3 = new hinter(ttChoose3);
        choose4 = new hinter(ttChoose4);
    }
}
