import java.util.Base64;

public class apiKEY {
    String encodeBytes() {


        String Username = "REPLACE THESE WORDS WITH USERNAME AND LEAVE QUOTATION MARKS";
        String Authorization_Token = "REPLACE THESE WORDS WITH AUTHORIZATION TOKEN AND LEAVE QUOTATION MARKS";



        String encodeBytes = Base64.getEncoder().encodeToString((Username + ":" + Authorization_Token).getBytes());
        return encodeBytes;
    }
}
