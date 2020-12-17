import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Base64;

public class results {
    static void UI_ReturnData(String[] allKey, String[] allVal, int index, boolean debug) {
        JFrame j = new JFrame();
        JPanel p = new JPanel();
        j.setTitle("Data Viewer for FRC-Datawizard");
        Object[][] tableData = new Object[index][2];
        int i = 0;
        int i3 = 0;
        while (i3 < index) {
            if (!allVal[i3].contains("null")) {
                tableData[i][0] = allKey[i3];
                tableData[i][1] = allVal[i3];
                i++;
            }
            i3++;
        }
        Object[] colnames = {"", ""};
        JTable table = new JTable(tableData, colnames);
        table.setFont(new Font("", Font.PLAIN, 28));
        table.setGridColor(Color.WHITE);
        table.setForeground(Color.WHITE);
        table.setBackground(Color.DARK_GRAY);
        table.setRowHeight(60);
        table.setEnabled(false);
        JScrollPane pa = new JScrollPane(table);
        pa.setPreferredSize(new Dimension(1200, 1400));
        p.add(pa);
        j.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        j.setContentPane(p);
        j.setLocationRelativeTo(null);
        j.pack();
        j.setVisible(true);
    }
    static void TERM_ReturnData(String[] allKey, String[] allVal, int index, boolean debug) {
        if (!debug)
            System.out.println("And here is your data: \n");
        else
            System.out.println("The parsed JSON: If something is missing between the JSON and the parsed results please open a github issue.");
        for (int i2 = 0; i2<index; i2++)
            System.out.printf("   %-35s %35s %n", allKey[i2], allVal[i2]);

    }
    static void renderImage (String baseImg) {
        byte[] imageBytes = Base64.getDecoder().decode(baseImg);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(275, 450, Image.SCALE_DEFAULT)));
        JFrame frame = new JFrame();
        frame.add(label);
        frame.setTitle("Avatar");
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        System.out.println("Check for another window displaying the avatar.");
    }
}
