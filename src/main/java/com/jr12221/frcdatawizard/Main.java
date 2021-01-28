package com.jr12221.frcdatawizard;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "FRC-Datawizard", footer = "Licensed under EUPLv1.2", header = "FRC-Datawizard " + Main.VERSION,
        description = "Searches the FRC API", mixinStandardHelpOptions = true, version = "Version " + Main.VERSION)
public class Main implements Runnable {

    public static final String FTC_BASE = "https://ftc-api.firstinspires.org/v2.0/";
    public static final String FRC_BASE = "https://frc-api.firstinspires.org/v2.0/";
    public static final String VERSION = "1.0";
    public static final String DEFAULT_FONT_NAME = "SansSerif";

    Call call1 = new Call();

    @Command(name = "status", description = "Displays server information in the terminal. -a or --all-info shows advanced server information.")
    void status(@Option(names = {"-a", "--all-info"}, description = "Show advanced server information.") boolean all) {
        String callReturn = call1.caller(FRC_BASE, false, FRC_BASE);
        if (all) {
            Iterator results = new Iterator(callReturn);
            Results.TERM_ReturnData(false, results.allKey, results.allVal, results.index, call1.modifiedLast);
        } else {
            Extractor ex1 = new Extractor(callReturn);
            System.out.println("The API Status is " + ex1.getOuter("status") + "  (You may encounter problems if anything other than \"normal\" is displayed.)");
            System.out.println("The Current Season is " + ex1.getOuter("currentSeason"));
            System.out.println("API Version " + ex1.getOuter("apiVersion"));
        }

    }

    @Command(name = "prefmgr", description = "Manage your API keys and default year.", mixinStandardHelpOptions = true)
    void prefmgr(@Option(names = {"-w", "--wipe"}, description = "Wipe the stored keys, and the default year.") boolean wipe,
            @Option(names = {"-R", "--set-FRC-key"}, description = "Set your FRC API key. Enter as USERNAME (Space) KEY", arity = "2") String[] setR,
            @Option(names = {"-T", "--set-FTC-key"}, description = "Set your FTC API key. Enter as USERNAME (Space) KEY", arity = "2") String[] setT,
            @Option(names = {"-y", "--set-default-year"}, description = "Set your default year, used unless you specify one.", arity = "0..1", interactive = true, echo = true) int setYear,
            @Option(names = {"-l", "--list-preferences"}, description = "List your API keys, and default year.") boolean list) {
        PreferenceReadWrite addkey1 = new PreferenceReadWrite();
        if (wipe) {
            addkey1.Clear();
        }
        if (setYear != 0) {
            addkey1.setYear(setYear);
        }
        if (setR != null) {
            addkey1.AddKeyNoInt(setR[0].trim(), setR[1].trim(), false);
            System.out.println("Testing API...");
            String callReturn = call1.caller("https://frc-api.firstinspires.org/v2.0/2019/teams?teamNumber=1", false, FRC_BASE);
            if (callReturn == null) {
                return;
            } else {
                System.out.println("API Key Code Working!");
            }

        }
        if (setT != null) {
            addkey1.AddKeyNoInt(setT[0].trim(), setT[1].trim(), true);
            System.out.println("Testing API...");
            String callReturn = call1.caller("https://ftc-api.firstinspires.org/v2.0/2019/teams?teamNumber=7244", false, FTC_BASE);
            if (callReturn == null) {
                return;
            } else {
                System.out.println("API Key Code Working!");
            }
        }
        if (list) {
            addkey1.ListKey();
        }
    }

    @Command(name = "prompt", description = "Use an interactive prompt to get data.  This is how you learn quick codes.", mixinStandardHelpOptions = true)
    void prompt(@Option(names = {"-y", "--year"}, description = "Mandatory, unless default entered with prefmgr or GUI.") int year,
            @Option(names = {"-e", "--event"}, description = "The event code to request data for.  Event codes can be foound on the TBA or FIRST sites.") String event,
            @Option(names = {"-t", "--team"}, description = "The team number to request data for.") int team,
            @Option(names = {"-c", "--ftc-data"}, description = "Show FTC Data instead of FRC data.  NOTE: Some menu items are not available, and the prompt will reflect that.") boolean ftcTrue,
            @Option(names = {"-g", "--gui-window"}, description = "Uses a simple GUI window to display results for you.") boolean gui,
            @Option(names = {"-d", "--debug"}, description = "Display debugging messages, such as the url and unparsed JSON.") boolean debug) {
        String base1;
        if (ftcTrue) {
            base1 = FTC_BASE;
        } else {
            base1 = FRC_BASE;
        }
        boolean teamB = false;
        boolean eventB = false;
        if (event != null) {
            eventB = true;

        }
        if (team != 0) {
            teamB = true;
        }
        PreferenceReadWrite pref = new PreferenceReadWrite();
        if (pref.ifYear() && year == 0) {
            year = pref.getYear();
        } else if (year == 0) {
            System.out.println("Year required.");
            return;
        }
        String urlstr;
        PromptSelector prompt = new PromptSelector();
        urlstr = prompt.urlselect(teamB, eventB, year, event, team, base1);
        if (urlstr == null) {
            return;
        }
        String callReturn = call1.caller(urlstr, debug, FRC_BASE);
        if (callReturn == null) {
            return;
        }
        Iterator results = new Iterator(callReturn);
        if (gui) {
            Results.UI_ReturnData(results.allKey, results.allVal, results.allInfo, results.index, call1.modifiedLast);
        } else {
            Results.TERM_ReturnData(debug, results.allKey, results.allVal, results.index, call1.modifiedLast);
        }
    }

    @Command(name = "quick", description = "Use flags and parameters to display information.  (GUI Display still possible via the -g command).", mixinStandardHelpOptions = true)
    void quick(@Option(names = {"-y", "--year"}, description = "Mandatory, unless default entered with prefmgr or GUI.") int year,
            @Option(names = {"-e", "--event"}, description = "The event code to request data for.  Event codes can be foound on the TBA or FIRST sites.") String event,
            @Option(names = {"-t", "--team"}, description = "The team number to request data for.") int team,
            @Option(names = {"-c", "--ftc-data"}, description = "Show FTC Data instead of FRC data.  NOTE: Some menu items are not available, and the prompt will reflect that.") boolean ftcTrue,
            @Option(names = {"-g", "--gui-window"}, description = "Uses a simple GUI window to display results for you.") boolean gui,
            @Option(names = {"-d", "--debug"}, description = "Display debugging messages, such as the url and unparsed JSON.") boolean debug,
            @Parameters(paramLabel = "Quick Code 1", index = "0", arity = "0..1", defaultValue = "0") int choose0,
            @Parameters(paramLabel = "Quick Code 2", index = "1", arity = "0..1", defaultValue = "No Parameter Selected") String choose1,
            @Parameters(paramLabel = "Quick Code 3", index = "2", arity = "0..1", defaultValue = "No Parameter Selected") String choose2,
            @Parameters(paramLabel = "Quick Code 4", index = "3", arity = "0..1", defaultValue = "No Parameter Selected") String choose3,
            @Parameters(paramLabel = "Quick Code 5", index = "4", arity = "0..1", defaultValue = "No Parameter Selected") String choose4) {
        String base1;
        if (ftcTrue) {
            base1 = FTC_BASE;
        } else {
            base1 = FRC_BASE;
        }
        boolean teamB = false;
        boolean eventB = false;
        if (choose0 == 0) {
            System.out.println("You need at least one parameter, use the GUI to get examples.");
            return;
        }
        if (event != null) {
            eventB = true;

        }
        if (team != 0) {
            teamB = true;
        }
        PreferenceReadWrite pref = new PreferenceReadWrite();
        if (pref.ifYear() && year == 0) {
            year = pref.getYear();
        } else if (year == 0) {
            System.out.println("Year required.");
            return;
        }
        String urlstr;
        CliSelector cliSelect = new CliSelector();
        urlstr = cliSelect.urlselect(teamB, eventB, year, event, team, choose0, choose1, choose2, choose3, choose4, gui, base1);
        if (urlstr == null) {
            return;
        }
        String callReturn = call1.caller(urlstr, debug, FRC_BASE);
        if (callReturn == null) {
            return;
        }
        Iterator results = new Iterator(callReturn);
        if (gui) {
            Results.UI_ReturnData(results.allKey, results.allVal, results.allInfo, results.index, call1.modifiedLast);
        } else {
            Results.TERM_ReturnData(debug, results.allKey, results.allVal, results.index, call1.modifiedLast);
        }
    }

    @Override
    public void run() {
        StartHomeForm.main(null);
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new Main());
        cmd.execute(args);
    }
}
