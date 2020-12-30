
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

public class prefHelper {
    Scanner scanStr = new Scanner(System.in);
    Properties propK = new Properties();
    void AddFRCKey () {
        try {
            try {
                FileInputStream is = new FileInputStream(".frc-datawizard.properties");
                propK.load(is);
                is.close();
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found. Creating it.");
                FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
                fos.close();
            }
            if (propK.containsKey("usernameR")) {
                System.out.println("API Key already found, overwrite? Answer yes or no.");
                String answer = scanStr.nextLine();
                if (answer.equalsIgnoreCase("no")) {
                    System.exit(0);
                }

            }
            FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
            System.out.println("This will assist you in adding your FRC api key to enable the program to collect data.");
            System.out.println("First enter username.");
            String Username = scanStr.nextLine();
            propK.setProperty("usernameR", Username);
            System.out.println("Now, enter your key.");
            String key = scanStr.nextLine();
            propK.setProperty("keyR", key);
            propK.store(fos, "");
            fos.close();
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n"+ e.getMessage());
        }
    }
    void AddFTCKey () {
        try {
            try {
                FileInputStream is = new FileInputStream(".frc-datawizard.properties");
                propK.load(is);
                is.close();
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found. Creating it.");
                FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
                fos.close();
            }
            if (propK.containsKey("usernameT")) {
                System.out.println("API Key already found, overwrite? Answer yes or no.");
                String answer = scanStr.nextLine();
                if (answer.equalsIgnoreCase("no")) {
                    System.exit(0);
                }

            }
            FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
            System.out.println("This will assist you in adding your FTC api key to enable the program to collect data.");
            System.out.println("First enter username.");
            String Username = scanStr.nextLine();
            propK.setProperty("usernameT", Username);
            System.out.println("Now, enter your key.");
            String key = scanStr.nextLine();
            propK.setProperty("keyT", key);
            propK.store(fos, "");
            fos.close();
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n"+ e.getMessage());
        }
    }
    void Clear () {
        try {
            FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
            fos.close();
            System.out.println("API Key cleared.");
        } catch (IOException e) {
            System.out.println("I/O Error, maybe you haven't added your key with prefmgr -s? \n"+ e.getMessage());
        }
    }
    void ListKey() {
        try {
            FileInputStream is = new FileInputStream(".frc-datawizard.properties");
            propK.load(is);
            propK.forEach((k, v) -> System.out.println(k + "       " + v));
            is.close();
        } catch (IOException e) {
            System.out.println("I/O Error, maybe you haven't added your key with prefmgr -s? \n"+ e.getMessage());
        }
    }
    String encodedKey(String base3)  {
        if (base3.equals(mainStarter.frc_base)) {
            try {
                String encodedKey;
                FileInputStream is = new FileInputStream(".frc-datawizard.properties");
                propK.load(is);
                is.close();
                if (!propK.containsKey("usernameR") || !propK.containsKey("keyR")) {
                    System.out.println("Please enter your API key to use the program.");
                    System.exit(2);
                }
                String Username = propK.getProperty("usernameR");
                String key = propK.getProperty("keyR");
                encodedKey = Base64.getEncoder().encodeToString((Username + ":" + key).getBytes());
                return encodedKey;
            } catch (IOException e) {
                System.out.println("You must first import your key using the prefmgr -s command.\n" + e.getMessage());
                System.exit(1);
                return null;
            }
        }
        else {
            try {
                String encodedKey;
                FileInputStream is = new FileInputStream(".frc-datawizard.properties");
                propK.load(is);
                is.close();
                if (!propK.containsKey("usernameT") || !propK.containsKey("keyT")) {
                    System.out.println("Please enter your API key to use the program.");
                    System.exit(2);
                }
                String Username = propK.getProperty("usernameT");
                String key = propK.getProperty("keyT");
                encodedKey = Base64.getEncoder().encodeToString((Username + ":" + key).getBytes());
                return encodedKey;
            } catch (IOException e) {
                System.out.println("You must first import your key using the prefmgr -s command.\n" + e.getMessage());
                System.exit(1);
                return null;
            }
        }
    }
    void setYear(int year)  {
        try {
            FileInputStream is = new FileInputStream(".frc-datawizard.properties");
            propK.load(is);
            is.close();
            propK.setProperty("default_year", String.valueOf(year));
            FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
            propK.store(fos,"");
            fos.close();
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n" + e.getMessage());
        }
    }
    boolean ifYear()  {
        try {
            FileInputStream is = new FileInputStream(".frc-datawizard.properties");
            propK.load(is);
            is.close();
            return propK.containsKey("default_year");
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n" + e.getMessage());
            System.exit(1);
            return false;
        }

    }
    int getYear() {
        try {
        FileInputStream is = new FileInputStream(".frc-datawizard.properties");
        propK.load(is);
        is.close();
        return Integer.parseInt(propK.getProperty("default_year"));
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n" + e.getMessage());
            System.exit(1);
            return 0;
        }
    }
}
