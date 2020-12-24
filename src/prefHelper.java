
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
    void AddKey () {
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
            if (propK.containsKey("username")) {
                System.out.println("API Key already found, overwrite? Answer yes or no.");
                String answer = scanStr.nextLine();
                if (answer.equalsIgnoreCase("no")) {
                    System.exit(0);
                }

            }
            FileOutputStream fos = new FileOutputStream(".frc-datawizard.properties");
            System.out.println("This will assist you in adding your api key to enable the program to collect data.");
            System.out.println("First enter username.");
            String Username = scanStr.nextLine();
            propK.setProperty("username", Username);
            System.out.println("Now, enter your key.");
            String key = scanStr.nextLine();
            propK.setProperty("key", key);
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
    String encodedKey()  {
        try {
            String encodedKey;
            FileInputStream is = new FileInputStream(".frc-datawizard.properties");
            propK.load(is);
            is.close();
            if (!propK.containsKey("username")||!propK.containsKey("key")) {
                System.out.println("Please enter your API key to use the program.");
                System.exit(2);
            }
            String Username = propK.getProperty("username");
            String key = propK.getProperty("key");
            encodedKey = Base64.getEncoder().encodeToString((Username + ":" + key).getBytes());
            return encodedKey;
        } catch (IOException e) {
            System.out.println("I/O Error.  Oh no.  Check for write permissions.\n"+ e.getMessage());
            System.exit(1);
            return null;
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
