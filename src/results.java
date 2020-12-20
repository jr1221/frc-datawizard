import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Base64;

public class results {
    static void UI_ReturnData(String[] allKey, String[] allVal, int[] allInfo, int index, boolean debug) {
        System.out.println("Generating GUI Table...");
        Object[][] tableData = new Object[10000][2];
        int i = 0;
        int i3 = 0;
        while (i3 < index) {
            allVal[i3] = allVal[i3].replace("\"","");
            if (!allVal[i3].contains("null")&&!allKey[i3].equals("")) {
                switch (allInfo[i3]) {
                    case 1:
                        if (i3==0) {
                            tableData[i][0] = allKey[i3];
                            tableData[i][1] = allVal[i3];
                            i++;
                        } else {
                            tableData[i][0] = "";
                            i++;
                            tableData[i][0] = allKey[i3];
                            tableData[i][1] = allVal[i3];
                            i++;
                            tableData[i][0] = "";
                            i++;
                        }
                        break;
                    case 2:
                        if (i3>1&&(allInfo[i3-1]==3||allInfo[i3-1]==4)) {
                            tableData[i][0] = "";
                            i++;
                        }
                        tableData[i][0] = allKey[i3];
                        tableData[i][1] = allVal[i3];
                        i++;
                        break;
                    case 3:
                        if (!(allInfo[i3-1]==1)) {
                            tableData[i][0] = "";
                            i++;
                        }
                        tableData[i][0] = allKey[i3];
                        tableData[i][1] = allVal[i3];
                        i++;
                        break;
                    case 5:
                        tableData[i][0] = "";
                        i++;
                        tableData[i][0] = allKey[i3];
                        tableData[i][1] = allVal[i3];
                        i++;
                        break;
                    default:
                        tableData[i][0] = allKey[i3];
                        tableData[i][1] = allVal[i3];
                        i++;
                }
            }
            i3++;
        }
        Object[][] tableData2 = new Object[i][2];
        for (i3 = 0; i3<i; i3++) {
            tableData2[i3][0] = tableData[i3][0];
            tableData2[i3][1] = tableData[i3][1];
        }
        Object[] colnames = {"Key", "Value"};
        JScrollPane jsp;
        JFrame j = new JFrame();
        TableModel model;
        JTable table;
        TableRowSorter sorter;

        model = new DefaultTableModel(tableData2, colnames);
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        table.setFont(new Font("", Font.PLAIN, 26));
        table.setGridColor(new Color(187,187,187));
        table.setForeground(new Color(187,187,187));
        table.setBackground(new Color(43,43,43));
        table.setRowHeight(54);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(900,1000));

        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBackground(new Color(43,43,43));
        jsp.setForeground(new Color(43,43,43));

        j.add(jsp, BorderLayout.CENTER);

        j.setTitle("Data Viewer for FRC-Datawizard");
        j.getContentPane().setBackground(new Color(43,43,43));
        j.getContentPane().setForeground(new Color(43,43,43));
        j.setPreferredSize(new Dimension(925,1000));
        j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
        j.setLocationRelativeTo(null);
        j.pack();
        j.setVisible(true);

        JFrame s = new JFrame();
        JTextField jtf;
        JLabel searchLbl;


        searchLbl = new JLabel("Search:");
        searchLbl.setFont(new Font("", Font.PLAIN, 26));
        searchLbl.setForeground(new Color(187,187,187));


        jtf = new JTextField(15);
        jtf.setFont(new Font("", Font.PLAIN, 24));
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
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" +str));
                }
            }
        });

        s.add(searchLbl);
        s.add(jtf);

        s.setTitle("Search");
        s.setLayout(new FlowLayout(FlowLayout.CENTER));
        s.setSize(400, 150);
        s.getContentPane().setBackground(new Color(43,43,43));
        s.setDefaultCloseOperation(s.EXIT_ON_CLOSE);
        s.setLocation(j.getX()-500,j.getY());
        s.setResizable(false);
        s.setVisible(true);
        System.out.println("Two windows should appear, one with your data and one with a search bar.");
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
