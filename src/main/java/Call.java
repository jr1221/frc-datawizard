
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

public class Call {
        String modifiedLast;
    String caller(String urlstr, boolean debug, String base2) {
        String json;
        int respCode;
        try {
            URL url = new URL(urlstr);
            HttpsURLConnection urlMaker = (HttpsURLConnection) url.openConnection();
            PreferenceReadWrite apiGET = new PreferenceReadWrite();
            String encodeBytes = apiGET.encodedKey(base2);
            if (encodeBytes == null && !urlstr.equals(Main.FRC_BASE)) {
                return null;
            }
            urlMaker.setRequestProperty("Authorization", " Basic " + encodeBytes);
            urlMaker.setRequestProperty("Accept", " application/json");
            urlMaker.connect();
            respCode = urlMaker.getResponseCode();
            modifiedLast = urlMaker.getHeaderField("Last-Modified");
            BufferedReader in1;
            if (respCode == 200) {
                in1 = new BufferedReader(new InputStreamReader(urlMaker.getInputStream()));
                json = in1.lines().collect(Collectors.joining());
            } else {
                System.out.println("Error in URL: " + urlstr);
                System.out.println("Response Code: " + respCode);
                InputStream in2 = urlMaker.getErrorStream();
                try {
                    in1 = new BufferedReader(new InputStreamReader(in2));
                    json = in1.lines().collect(Collectors.joining());
                    System.out.println(json);
                    return null;
                } catch (Exception ignored) {
                }
                System.out.println("Your key may be incorrect or expired.  Try entering it again. Exiting...");
                return null;
            }
            in1.close();
            if (debug) {
                System.out.println("Info regarding the API request: \n " + urlMaker.getHeaderFields() + "\n " + urlMaker.getURL());
                System.out.println("The JSON Response\n" + json);
            }
            urlMaker.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
            return null;
        }
        return json;
    }
}
