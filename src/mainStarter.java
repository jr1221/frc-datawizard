import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import java.io.IOException;
import java.util.Scanner;


@Command(name = "FRC-Datawizard", footer = "Apache License v2",
        description = "Searches the FRC API", mixinStandardHelpOptions = true, version = "Version 0.6 (Development)")
class mainStarter implements Runnable {
    interactiveStarter intStart = new interactiveStarter();
    call call1 = new call();
    @Option(names = {"-i", "--interactive"},description = "Enter the interactive prompt")
    private boolean allFiles;
    @Option(names = {"-d", "--debug"},description = "Display server status information and debugging messages")
    private boolean debug;
    @Option(names = {"-y", "--year"}, description = "The season to request data from")
    private int year;
    @Option(names = {"-e", "--event"}, description = "The event code to request data for")
    private String event;
    @Option(names = {"-t", "--team"}, description = "The team number to request data for")
    private int team;
    @Override
    public void run() {
        if (debug) {
            try {
                call1.caller(call.base);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            if (allFiles) {
                intStart.interactive();
            }
            else {
                boolean teamB = false;
                boolean eventB = false;
                boolean yearB = false;
                selector selector1 = new selector();
                if (year != 0) {
                    yearB=true;

                }
                if (event!=null) {
                    eventB=true;

                }
                if (team != 0) {
                    teamB=true;

                }
                String urlstr = selector1.urlselect(teamB, eventB, yearB, year, event, team);
                try {
                    call1.caller(urlstr);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static void main(String[] args) {
        new CommandLine(new mainStarter()).execute(args);
    }
}





class interactiveStarter {
    public void interactive()  {
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
        try {
            call1.caller(urlstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
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


