import java.io.IOException;
import java.util.Scanner;

public class interactiveBegin {
    public static boolean intStarter = false;
    static public void interactive() {
        intStarter=true;
        boolean teamB = true;
        boolean eventB = true;
        boolean debug = false;
        call call1 = new call();
        selector selector1 = new selector();
        Scanner scanInt = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        int year;
        String event;
        int team;
        System.out.println("Welcome to the interactive prompt.  Please follow the instructions to get an accurate output of the expansive FRC data this program can compile.");
        System.out.println("Enter d before (no spaces) event triggers debug info.");
        System.out.println("Enter season (ex. 2019)");
        year = scanInt.nextInt();
        System.out.println("Enter event code (ex. MELEW) \n Enter 0 for event n/a");
        event = scanStr.nextLine();
        if (event.charAt(1)=='0')
            eventB = false;
        if (event.charAt(0)=='d') {
            debug = true;
            event = event.substring(1);
        }
        System.out.println("Options;\n Enter team number (ex. 118) \n Enter 0 for team n/a");
        team = scanInt.nextInt();
        if (team == 0)
            teamB = false;
        String urlstr = selector1.urlselect(teamB, eventB, year, event, team);
        try {
            call1.caller(urlstr, debug, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
