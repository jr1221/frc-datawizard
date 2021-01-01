
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "FRC-Datawizard", footer = "Licensed under EUPLv1.2",
        description = "Searches the FRC API", mixinStandardHelpOptions = true, version = "Version 0.8 (Beta)")
public class Main implements Runnable {

    public static final String FTC_BASE = "https://ftc-api.firstinspires.org/v2.0/";
    public static final String FRC_BASE = "https://frc-api.firstinspires.org/v2.0/";
    @Spec
    CommandLine.Model.CommandSpec spec;
    Call call1 = new Call();

    @Command(name = "-s", aliases = {"--server-status"}, description = "Displays server information in the terminal.  Do not specify with any commands or flags.")
    void status() {
        call1.caller(FRC_BASE, false, FRC_BASE);
        Results.TERM_ReturnData(false, call1.allKey, call1.allVal, call1.index);
    }

    @Command(name = "gui", description = "Displays server information in the terminal.  Do not specify with any commands or flags.")
    void guiStart() {
        StartHomeForm.main(null);
    }

    @Command(name = "prefmgr", description = "Manage your API key and other preferences.", mixinStandardHelpOptions = true)
    void prefmgr(@Option(names = {"-w", "--wipe"}, description = "Wipe the stored key, and the defaults.") boolean wipe,
            @Option(names = {"-R", "--set-FRC-key"}, description = "Set your FRC API key. ") boolean setR,
            @Option(names = {"-T", "--set-FTC-key"}, description = "Set your FTC API key. ") boolean setT,
            @Option(names = {"-y", "--set-year"}, description = "Set your default year, used unless you specify one.") int setYear,
            @Option(names = {"-l", "--list-key"}, description = "List your API key") boolean list) {
        PreferenceReadWrite addkey1 = new PreferenceReadWrite();
        if (wipe) {
            addkey1.Clear();
            return;
        }
        if (setYear != 0) {
            addkey1.setYear(setYear);
            return;
        }
        if (setR) {
            addkey1.AddFRCKey();
            System.out.println("If you see data below, your api key was successfully inputted!");
            int callCode = call1.caller("https://frc-api.firstinspires.org/v2.0/2019/teams?teamNumber=1", false, FRC_BASE);
            if (callCode == -1) {
                return;
            }
            Results.TERM_ReturnData(debug, call1.allKey, call1.allVal, call1.index);
            return;
        }
        if (setT) {
            addkey1.AddFTCKey();
            System.out.println("If you see data below, your api key was successfully inputted!");
            int callCode = call1.caller("https://ftc-api.firstinspires.org/v2.0/2019/teams?teamNumber=7244", false, FTC_BASE);
            if (callCode == -1) {
                return;
            }
            Results.TERM_ReturnData(debug, call1.allKey, call1.allVal, call1.index);
            return;
        }
        addkey1.ListKey();
    }
    @Option(names = {"-d", "--debug"}, description = "Display debugging messages.")
    boolean debug;
    @Option(names = {"-u", "--ftc-data"}, description = "Show FTC Data instead of FRC data.  NOTE: Some menu items are not available, and the prompt will reflect that.")
    boolean ftcTrue;
    @Option(names = {"-g", "--gui-window"}, description = "Uses a simple GUI window to display results for you.  Use all other flags normally.")
    boolean gui;

    @Command(name = "cli", description = "Use the cli flags (at least partially)", mixinStandardHelpOptions = true)
    void cli(@Option(names = {"-y", "--year"}, description = "mandatory number, unless default entered.") int year,
            @Option(names = {"-e", "--event"}, description = "The event code to request data for") String event,
            @Option(names = {"-t", "--team"}, description = "The team number to request data for") int team,
            @Option(names = {"-c", "--continue-cli"}, description = "Do not enter next interactive prompt") boolean interB,
            @Parameters(index = "0", arity = "0..1", defaultValue = "0") int choose0,
            @Parameters(index = "1", arity = "0..1", defaultValue = "No Parameter Selected") String choose1,
            @Parameters(index = "2", arity = "0..1", defaultValue = "No Parameter Selected") String choose2,
            @Parameters(index = "3", arity = "0..1", defaultValue = "No Parameter Selected") String choose3,
            @Parameters(index = "4", arity = "0..1", defaultValue = "No Parameter Selected") String choose4) {
        String base1;
        if (ftcTrue) {
            base1 = FTC_BASE;
        } else {
            base1 = FRC_BASE;
        }
        boolean teamB = false;
        boolean eventB = false;
        if (choose0 == 0 && interB) {
            System.out.println("You need at least one parameter, or alternatively run without the -c flag to get a prompt.");
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
        if (!interB) {
            PromptSelector selector1 = new PromptSelector();
            urlstr = selector1.urlselect(teamB, eventB, year, event, team, base1);
        } else {
            CliSelector cliSelect = new CliSelector();
            urlstr = cliSelect.urlselect(teamB, eventB, year, event, team, choose0, choose1, choose2, choose3, choose4, gui, base1);
            if (urlstr == null) {
                return;
            }
        }
        call1.caller(urlstr, debug, FRC_BASE);
        if (gui) {
            Results.UI_ReturnData(call1.allKey, call1.allVal, call1.allInfo, call1.index);
        } else {
            Results.TERM_ReturnData(debug, call1.allKey, call1.allVal, call1.index);
        }
    }

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand");
    }

    public static void main(String[] args) {
        System.out.println("FRC-Datawizard v0.8 (Beta)");
        CommandLine cmd = new CommandLine(new Main());
        if (args.length == 0) {
            cmd.usage(System.out);
        } else {
            cmd.execute(args);
        }

    }
}
