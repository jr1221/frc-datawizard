package com.jr12221.frcdatawizard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

public class PreferenceReadWrite {

    Scanner scanStr = new Scanner(System.in);
    Properties propK = new Properties();

    int AddKeyNoInt(String user, String keyZ, boolean ftc) {
        try {
            try {
                try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                    propK.load(is);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found. Creating it.");
                FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
                fos.close();
            }
            try (FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties")) {
                if (ftc) {
                    propK.setProperty("usernameT", user);
                    propK.setProperty("keyT", keyZ);
                } else {
                    propK.setProperty("usernameR", user);
                    propK.setProperty("keyR", keyZ);
                }
                propK.store(fos, "");
            }
            return 0;
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n" + e.getMessage());
            return -2;
        }
    }

    void Clear() {
        try {
            FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
            fos.close();
            System.out.println("API Key cleared.");
        } catch (IOException e) {
            System.out.println("I/O Error, maybe you haven't added your key with prefmgr -s? \n" + e.getMessage());
        }
    }

    void ListKey() {
        try {
            try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                propK.load(is);
                propK.forEach((k, v) -> System.out.println(k + "       " + v));
            }
        } catch (IOException e) {
            System.out.println("I/O Error, maybe you haven't added your key with prefmgr -s? \n" + e.getMessage());
        }
    }

    String encodedKey(String base3) {
        if (base3.equals(Main.FRC_BASE)) {
            try {
                String encodedKey;
                try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                    propK.load(is);
                }
                if (!propK.containsKey("usernameR") || !propK.containsKey("keyR")) {
                    System.out.println("Please enter your API key to use the program.");
                    return null;
                }
                String Username = propK.getProperty("usernameR");
                String key = propK.getProperty("keyR");
                encodedKey = Base64.getEncoder().encodeToString((Username + ":" + key).getBytes());
                return encodedKey;
            } catch (IOException e) {
                System.out.println("Please enter your API key to use the program.");
                return null;
            }
        } else {
            try {
                String encodedKey;
                try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                    propK.load(is);
                }
                if (!propK.containsKey("usernameT") || !propK.containsKey("keyT")) {
                    System.out.println("Please enter your API key to use the program.");
                    return null;
                }
                String Username = propK.getProperty("usernameT");
                String key = propK.getProperty("keyT");
                encodedKey = Base64.getEncoder().encodeToString((Username + ":" + key).getBytes());
                return encodedKey;
            } catch (IOException e) {
                System.out.println("Please enter your API key to use the program.");
                return null;
            }
        }
    }

    void setYear(int year) {
        try {
            try {
                try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                    propK.load(is);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found. Creating it.");
                FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
                fos.close();
            }
            propK.setProperty("default_year", String.valueOf(year));
            try (FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties")) {
                propK.store(fos, "");
            }
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n" + e.getMessage());
            System.exit(1);
        }
    }

    boolean ifYear() {
        try {
            try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                propK.load(is);
            }
            return propK.containsKey("default_year");
        } catch (IOException e) {
            return false;
        }

    }

    int getYear() {
        try {
            try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                propK.load(is);
            }
            return Integer.parseInt(propK.getProperty("default_year"));
        } catch (IOException e) {
            System.out.println("I/O Error.  This shouldn't happen.  Check for write permissions in $HOME. Exiting... \n" + e.getMessage());
            System.exit(1);
            return 0;
        }
    }

    int getKeyCode() {
        try {
            try (FileInputStream is = new FileInputStream(".frc-datawizard.properties")) {
                propK.load(is);
            }
        } catch (IOException e) {
            return -1;
        }
        if (propK.containsKey("usernameR") && propK.containsKey("usernameT")) {
            return 2;
        }
        if (propK.containsKey("usernameT")) {
            return 1;
        }
        if (propK.containsKey("usernameR")) {
            return 0;
        }
        return -1;
    }
}
