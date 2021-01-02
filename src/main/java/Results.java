
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Results {

    static void UI_ReturnData(String[][] allData, int[] allInfo, int index, String modifiedLast) {
        System.out.println("Generating GUI Table...");
        Object[][] tableData = new Object[10000][2];
        int i = 0;
        int i3 = 0;
        while (i3 < index) {
            allData[i3][1] = allData[i3][1].replace("\"", "");
            if (!allData[i3][1].contains("null") && !allData[i3][1].equals("")) {
                switch (allInfo[i3]) {
                    case 1:
                        if (i3 == 0) {
                            tableData[i][0] = allData[i3][0];
                            tableData[i][1] = allData[i3][1];
                        } else {
                            tableData[i][0] = "";
                            i++;
                            tableData[i][0] = allData[i3][0];
                            tableData[i][1] = allData[i3][1];
                            i++;
                            tableData[i][0] = "";
                        }
                        i++;
                        break;
                    case 2:
                        if (i3 > 1 && (allInfo[i3 - 1] == 3 || allInfo[i3 - 1] == 4)) {
                            tableData[i][0] = "";
                            i++;
                        }
                        tableData[i][0] = allData[i3][0];
                        tableData[i][1] = allData[i3][1];
                        i++;
                        break;
                    case 3:
                        if (!(allInfo[i3 - 1] == 1)) {
                            tableData[i][0] = "";
                            i++;
                        }
                        tableData[i][0] = allData[i3][0];
                        tableData[i][1] = allData[i3][1];
                        i++;
                        break;
                    case 5:
                        tableData[i][0] = "";
                        i++;
                        tableData[i][0] = allData[i3][0];
                        tableData[i][1] = allData[i3][1];
                        i++;
                        break;
                    default:
                        tableData[i][0] = allData[i3][0];
                        tableData[i][1] = allData[i3][1];
                        i++;
                }
            }
            i3++;
        }
        Object[][] tableData2 = new Object[i][2];
        for (i3 = 0; i3 < i; i3++) {
            tableData2[i3][0] = tableData[i3][0];
            tableData2[i3][1] = tableData[i3][1];
        }
        Object[] colnames = {"Key", "Value"};
        JScrollPane jsp;
        JFrame j = new JFrame();
        TableModel model;
        JTable table;
        TableRowSorter sorter;
        JTextField jtf;
        JTextField lab2 = new JTextField();

        jtf = new HintJObject("Type Search Term Here");
        jtf.setColumns(15);

        model = new DefaultTableModel(tableData2, colnames);
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        table.setFont(new Font("", Font.PLAIN, 26));
        table.setGridColor(new Color(187, 187, 187));
        table.setForeground(new Color(187, 187, 187));
        table.setBackground(new Color(43, 43, 43));
        table.setRowHeight(54);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(900, 1000));

        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBackground(new Color(43, 43, 43));
        jsp.setForeground(new Color(43, 43, 43));

        j.add(jtf, BorderLayout.NORTH);
        j.add(jsp, BorderLayout.CENTER);
        lab2.setFont(new Font("", Font.PLAIN, 20));
        lab2.setText("This data was last modified on " + modifiedLast);
        j.add(lab2, BorderLayout.PAGE_END);
        j.setTitle("Data Viewer for FRC-Datawizard");
        j.getContentPane().setBackground(new Color(43, 43, 43));
        j.getContentPane().setForeground(new Color(43, 43, 43));
        j.setPreferredSize(new Dimension(925, 1000));
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        j.setLocationRelativeTo(null);
        j.pack();
        j.setVisible(true);
        lab2.grabFocus();
        lab2.setEditable(false);

        jtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jtf.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jtf.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jtf.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }

    static void TERM_ReturnData(boolean debug, String[][] allData, int index, String modifiedLast) {
        if (!debug) {
            System.out.println("And here is your data: \n");
        } else {
            System.out.println("The parsed JSON: If something is missing between the JSON and the parsed results please open a github issue.");
        }
        for (int i2 = 0; i2 < index; i2++) {
            System.out.printf("   %-35s %35s %n", allData[i2][0], allData[i2][1]);
        }
        System.out.println("Data Last Modified on:" + modifiedLast);

    }

    static void renderImage(String baseImg) {
        byte[] imageBytes = Base64.getDecoder().decode(baseImg);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(275, 450, Image.SCALE_DEFAULT)));
        JFrame frame = new JFrame();
        frame.add(label);
        frame.setTitle("Avatar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(450, 500);
        System.out.println("Check for another window displaying the avatar.");
    }
}
