
import java.io.*;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

public class apiHelper {
    Scanner scanStr = new Scanner(System.in);
    Properties propK = new Properties();
    void AddKey () throws IOException {
        FileInputStream is = new FileInputStream("apiProps.properties");
        propK.load(is);
        if (!propK.isEmpty()) {
            System.out.println("API Key already found, overwrite? Answer yes or no.");
            String answer = scanStr.nextLine();
            if (answer.equalsIgnoreCase("no")) {
                System.exit(0);
            }

        }
        is.close();
        FileOutputStream fos = new FileOutputStream("apiProps.properties");
        System.out.println("This will assist you in adding your api key to enable the program to collect data.");
        System.out.println("First enter username.");
        String Username = scanStr.nextLine();
        propK.setProperty("username",Username);
        System.out.println("Now, enter your key.");
        String key = scanStr.nextLine();
        propK.setProperty("key", key);
        propK.store(fos, "");
        fos.close();
    }
    void Clear () throws IOException {
        FileOutputStream fos = new FileOutputStream("apiProps.properties");
        fos.close();
        System.out.println("API Key cleared.");
    }
    void ListKey() throws IOException {
        FileInputStream is = new FileInputStream("apiProps.properties");
        propK.load(is);
        propK.forEach((k, v) -> System.out.println(k + "       " + v));
        is.close();
    }
    String encodedKey()  {
        String encodedKey;
        try {
            FileInputStream is = new FileInputStream("apiProps.properties");
            propK.load(is);
            is.close();
        }
        catch (IOException i) {}
        if (!propK.containsKey("username")||!propK.containsKey("key")) {
            System.out.println("Please enter your API key to use the program.");
            System.exit(2);
        }
        String Username = propK.getProperty("username");
        String key = propK.getProperty("key");
        encodedKey = Base64.getEncoder().encodeToString((Username + ":" + key).getBytes());
        return encodedKey;
    }
}
