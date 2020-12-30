import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private JButton promptButton;
    private JRadioButton radioButtonFRC;
    private JRadioButton radioButtonFTC;

    private final String ttYear = "year";
    private final String ttTeam = "team #";
    private final String ttEvent = "event code";
    private final String ttChoose0 = "Code #1";
    private final String ttChoose1 = "Code #2";
    private final String ttChoose2 = "Code #3";
    private final String ttChoose3 = "Code #4";
    private final String ttChoose4 = "Code #5";


    public start () {
        goButton.addActionListener(actionEvent -> {
            String base1;
            if (radioButtonFTC.isSelected())
                base1 = mainStarter.ftc_base;
            else
                base1 = mainStarter.frc_base;
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
            if (eventField.getText().equals(""))
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
            String urlstr = cli1.urlselect(teamB, eventB, year, event, team, choose0Int, choose1.getText(), choose2.getText(), choose3.getText(), choose4.getText(), !cliVal.isSelected(), base1);
            call call1 = new call();
            call1.caller(urlstr, debugVal.isSelected(), base1);
            if (!cliVal.isSelected())
                results.UI_ReturnData(call1.allKey, call1.allVal, call1.allInfo, call1.index);
            else
                results.TERM_ReturnData(debugVal.isSelected(), call1.allKey, call1.allVal, call1.index);
        });
        debugVal.addActionListener(actionEvent -> {
            if (debugVal.isSelected())
                debugVal.setForeground(Color.RED);
            else
                debugVal.setForeground(Color.BLACK);
        });
        cliVal.addActionListener(actionEvent -> {
            if (cliVal.isSelected())
                cliVal.setForeground(Color.RED);
            else
                cliVal.setForeground(Color.BLACK);
        });
        promptButton.addActionListener(actionEvent -> {
            String base1;
            if (radioButtonFTC.isSelected())
                base1 = mainStarter.ftc_base;
            else
                base1 = mainStarter.frc_base;
            int year = cleanHints();
            if (year==-1)
                return;
            boolean teamB = true;
            int team = 0;
            if (teamField.getText().equals(""))
                teamB = false;
            else
                team = Integer.parseInt(teamField.getText());
            boolean eventB = true;
            String event = null;
            if (eventField.getText().equals(""))
                eventB = false;
            else
                event = eventField.getText();
            selector s2 = new selector();
            String urlstr = s2.urlselect(teamB, eventB, year, event, team, base1);
            call call1 = new call();
            call1.caller(urlstr, debugVal.isSelected(), base1);
            if (!cliVal.isSelected())
                results.UI_ReturnData(call1.allKey, call1.allVal, call1.allInfo, call1.index);
            else
                results.TERM_ReturnData(debugVal.isSelected(), call1.allKey, call1.allVal, call1.index);
        });
        radioButtonFRC.addActionListener(actionEvent -> {
            if (radioButtonFRC.isSelected()) {
                radioButtonFRC.setForeground(Color.RED);
                radioButtonFTC.setForeground(Color.BLACK);
                radioButtonFTC.setSelected(false);
            } else {
                radioButtonFRC.setForeground(Color.BLACK);
                radioButtonFTC.setSelected(true);
                radioButtonFTC.setForeground(Color.RED);
            }

        });
        radioButtonFTC.addActionListener(actionEvent -> {
            if (radioButtonFTC.isSelected()) {
                radioButtonFTC.setForeground(Color.RED);
                radioButtonFRC.setForeground(Color.BLACK);
                radioButtonFRC.setSelected(false);
            } else {
                radioButtonFTC.setForeground(Color.BLACK);
                radioButtonFRC.setSelected(true);
                radioButtonFRC.setForeground(Color.RED);
            }

        });
    }

    public int cleanHints() {
        int year;
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
