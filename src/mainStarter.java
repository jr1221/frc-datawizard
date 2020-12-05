import java.io.IOException;
import java.util.Scanner;

class mainStarter {
    public static void main(String[] args) throws IOException {
        boolean teamB = true;
        boolean eventB = true;
        boolean yearB = true;
        call call1 = new call();
        selector selector1 = new selector();
        Scanner scanInt = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        int year;
        String event;
        int team;
        System.out.println("FRC-Datawizard version 0.6");
        System.out.println("Welcome to the interactive prompt.  Please follow the instructions to get an accurate output of the expansive FRC data this program can compile.");
        System.out.println("Entering 0 for year, team, and event shows api status information and verbose debug info.");
        System.out.println("Enter season (ex. 2019) \n Enter 0 for season n/a");
        year = scanInt.nextInt();
        if (year == 0)
            yearB = false;
        System.out.println("Enter event code (ex. MELEW) \n Enter 0 for event n/a");
        event = scanStr.nextLine();
        if (event.equals("0"))
            eventB = false;
        System.out.println("Options;\n Enter team number (ex. 118) \n Enter 0 for team n/a");
        team = scanInt.nextInt();
        if (team == 0)
            teamB = false;
        String urlstr = selector1.urlselect(teamB, eventB, yearB, year, event, team);
        call1.caller(urlstr);

    }
    static void ReturnData (String[] allKey,String[] allVal,int index) {
        System.out.println("And here is your data: \n");
        int index2=0;
        while (index2<index) {
            System.out.printf("   %-35s %35s %n", allKey[index2], allVal[index2]);
            index2++;
        }
    }
}


