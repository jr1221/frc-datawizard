import javax.swing.*;

public class startErr extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel infoLabel;

    public startErr(String error) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        infoLabel.setText(error);
        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String error) {
        startErr dialog = new startErr(error);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
}
