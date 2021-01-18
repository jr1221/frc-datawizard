package com.jr12221.frcdatawizard;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class Results {

    static void UI_ReturnData(ArrayList<String> allKey, ArrayList<String> allVal, ArrayList<Integer> allInfo, int index, String modifiedLast) {
        System.out.println("Generating GUI Table...");
        for (int t = 0; t < allVal.size(); t++) {
            allKey.set(t, allKey.get(t).replace("\"", ""));
            allVal.set(t, allVal.get(t).replace("\"", ""));

        }
        if (allKey.contains("     +-encodedAvatar")) {
            return;
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
                case 1 -> {
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
                }
                case 3 -> {
                    if (!(allInfo.get(z - 1) == 1)) {
                        allKey.add(j - 1, " ");
                        allVal.add(j - 1, " ");
                        j++;
                    }
                }
                case 2 -> {
                    if (j > 1 && (allInfo.get(z - 1) == 3 || allInfo.get(z - 1) == 4)) {
                        allKey.add(j - 1 + 1, " ");
                        allVal.add(j - 1 + 1, " ");
                        j += 2;
                    }
                }
                case 5 -> {
                    allKey.add(j - 1, " ");
                    allVal.add(j - 1, " ");
                    j++;
                }
            }
        }

        String[] colnames = {"Key", "Value"};
        DefaultTableModel modelTab = new DefaultTableModel();
        modelTab.setColumnIdentifiers(colnames);
        for (int b = 0; b < allKey.size(); b++) {
            modelTab.addRow((new Object[]{allKey.get(b), allVal.get(b)}));
        }
        ResultsUI resultPane = new ResultsUI(modifiedLast, modelTab);
        resultPane.setVisible(true);
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
        frame.setLocation(700, 500);
        frame.pack();
        frame.setVisible(true);
        System.out.println("Check for another window displaying the avatar.");
    }
}
