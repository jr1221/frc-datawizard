package com.jr12221.frcdatawizard;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
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

    static void UI_ReturnData(ArrayList<String> allKey, ArrayList<String> allVal, ArrayList<Integer> allInfo, int index, String modifiedLast) {
        System.out.println("Generating GUI Table...");
        for (int t = 0; t < allVal.size(); t++) {
            allKey.set(t, allKey.get(t).replace("\"", ""));
            allVal.set(t, allVal.get(t).replace("\"", ""));
        }

        while (allVal.contains("null")) {
            allKey.remove(allVal.indexOf("null"));
            allInfo.remove(allVal.indexOf("null"));
            allVal.remove(allVal.indexOf("null"));
        }
        int z = -1;
        for (int j = 0; j < allVal.size(); j++) {
            z++;
            switch (allInfo.get(z)) {
                case 1:
                    if (!(j == 0)) {
                        allKey.add(j, " ");
                        allVal.add(j, " ");
                        j += 2;
                        allKey.add(j, " ");
                        allVal.add(j, " ");
                        j++;
                    } else {
                        allKey.add(j + 1, " ");
                        allVal.add(j + 1, " ");
                        j += 2;
                    }
                    break;
                case 3:
                    if (!(allInfo.get(z - 1) == 1)) {
                        allKey.add(j-1, " ");
                        allVal.add(j-1, " ");
                        j++;
                    }
                    break;
                case 2:
                    if (j > 1 && (allInfo.get(z - 1) == 3 || allInfo.get(z - 1) == 4)) {
                        allKey.add(j-1 + 1, " ");
                        allVal.add(j-1 + 1, " ");
                        j += 2;
                    }
                    break;
                case 5:
                    allKey.add(j-1, " ");
                    allVal.add(j-1, " ");
                    j++;
                    break;
            }
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

        jtf.setColumns(
                15);
        String[][] tableData3 = new String[allKey.size()][2];
        for (int b = 0;
                b < allKey.size();
                b++) {
            tableData3[b][0] = allKey.get(b);
            tableData3[b][1] = allVal.get(b);
        }
        model = new DefaultTableModel(tableData3, colnames);
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);

        table.setRowSorter(sorter);

        table.setFont(
                new Font("", Font.PLAIN, 26));
        table.setGridColor(
                new Color(187, 187, 187));
        table.setForeground(
                new Color(187, 187, 187));
        table.setBackground(
                new Color(43, 43, 43));
        table.setRowHeight(
                54);
        table.setEnabled(
                false);
        table.setPreferredScrollableViewportSize(
                new Dimension(900, 1000));

        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jsp.setBackground(
                new Color(43, 43, 43));
        jsp.setForeground(
                new Color(43, 43, 43));

        j.add(jtf, BorderLayout.NORTH);

        j.add(jsp, BorderLayout.CENTER);

        lab2.setFont(
                new Font("", Font.PLAIN, 20));
        lab2.setText(
                "This data was last modified on " + modifiedLast);
        j.add(lab2, BorderLayout.PAGE_END);

        j.setTitle(
                "Data Viewer for FRC-Datawizard");
        j.getContentPane()
                .setBackground(new Color(43, 43, 43));
        j.getContentPane()
                .setForeground(new Color(43, 43, 43));
        j.setPreferredSize(
                new Dimension(925, 1000));
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        j.setLocationRelativeTo(
                null);
        j.pack();

        j.setVisible(
                true);
        lab2.grabFocus();

        lab2.setEditable(
                false);

        jtf.getDocument()
                .addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e
                    ) {
                        search(jtf.getText());
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e
                    ) {
                        search(jtf.getText());
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e
                    ) {
                        search(jtf.getText());
                    }

                    public void search(String str) {
                        if (str.length() == 0) {
                            sorter.setRowFilter(null);
                        } else {
                            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                        }
                    }
                }
                );
    }

    static void TERM_ReturnData(boolean debug, ArrayList<String> allKey, ArrayList<String> allVal, int index, String modifiedLast) {
        if (!debug) {
            System.out.println("And here is your data: \n");
        } else {
            System.out.println("The parsed JSON: If something is missing between the JSON and the parsed results please open a github issue.");
        }
        while (allVal.contains("null")) {
            allKey.remove(allVal.indexOf("null"));
            allVal.remove(allVal.indexOf("null"));
        }
        Iterator<String> allValIter = allVal.iterator();
        Iterator<String> allKeyIter = allKey.iterator();
        while (allValIter.hasNext()) {
            System.out.printf("   %-35s %35s %n", allKeyIter.next(), allValIter.next());
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
