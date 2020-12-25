import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;



@Command(name = "FRC-Datawizard", footer = "Licensed under EUPLv1.2",
        description = "Searches the FRC API", mixinStandardHelpOptions = true, version = "Version 0.6 (Alpha)")
public class mainStarter implements Runnable {
    @Spec
    CommandLine.Model.CommandSpec spec;
    call call1 = new call();
    @Command(name = "-s", aliases = {"--server-status"},description = "Displays server information in the terminal.  Do not specify with any commands or flags.")
    void status() {
        call1.caller(selector.base, false);
        results.TERM_ReturnData(false);
    }
    @Command(name = "prefmgr",description = "Manage your API key and other preferences.", mixinStandardHelpOptions = true)
    void prefmgr(@Option(names = {"-w","--wipe"}, description = "Wipe the stored key, and the defaults.") boolean wipe,
                @Option(names = {"-s","--set-key"}, description = "Set your API key") boolean set,
                @Option(names = {"-y","--set-year"}, description = "Set your default year, used unless you specify one.") int setYear,
                @Option(names = {"-l","--list-key"}, description = "List your API key") boolean list) {
        prefHelper addkey1 = new prefHelper();
        if (wipe) {
            addkey1.Clear();
            return;
        }
        if (setYear != 0) {
            addkey1.setYear(setYear);
            return;
        }
        if (set) {
            addkey1.AddKey();
            System.out.println("If you see data below, your api key was successfully inputted!");
            call call1 = new call();
            call1.caller("https://frc-api.firstinspires.org/v2.0/2019/teams?teamNumber=1", false);
            results.TERM_ReturnData(debug);
            return;
        }
        addkey1.ListKey();
    }
    @Option(names = {"-d","--debug"}, description = "Display debugging messages.") boolean debug;
    @Option(names = {"-g","--gui-window"}, description = "Uses a simple GUI window to display results for you.  Use all other flags normally.") boolean gui;
    @Command(name = "prompt", description = "Enter the interactive Prompt")
    void interactive() {
        String urlstr = interactiveBegin.interactive();
        call1.caller(urlstr, false);
        if (gui)
            results.UI_ReturnData(debug, urlstr);
        else
            results.TERM_ReturnData(debug);
    }
    @Command(name = "cli", description = "Use the cli flags (at least partially)", mixinStandardHelpOptions = true)
    void cli( @Option(names = {"-y","--year"}, description = "mandatory number, unless default entered.") int year,
              @Option(names = {"-e", "--event"}, description = "The event code to request data for") String event,
              @Option(names = {"-t", "--team"}, description = "The team number to request data for") int team,
              @Option(names = {"-c","--continue-cli"},description = "Do not enter next interactive prompt") boolean interB,
              @Parameters(index = "0",arity = "0..1",defaultValue = "0") int choose0,
              @Parameters(index = "1",arity = "0..1",defaultValue = "No Parameter Selected") String choose1,
              @Parameters(index = "2",arity = "0..1",defaultValue = "No Parameter Selected") String choose2,
              @Parameters(index = "3",arity = "0..1",defaultValue = "No Parameter Selected") String choose3,
              @Parameters(index = "4",arity = "0..1",defaultValue = "No Parameter Selected") String choose4) {
        boolean teamB = false;
        boolean eventB = false;
        if (choose0 == 0&&interB) {
            System.out.println("You need at least one parameter, or alternatively run without the -c flag to get a prompt.");
            System.exit(2);
        }
        if (event != null) {
            eventB = true;

        }
        if (team != 0) {
            teamB = true;
        }
        prefHelper pref = new prefHelper();
        if (pref.ifYear()&& year==0) {
            year = pref.getYear();
        } else if (year==0) {
            System.out.println("Year required.");
            System.exit(2);
        }
        String urlstr;
        if (!interB) {
            selector selector1 = new selector();
            urlstr = selector1.urlselect(teamB, eventB, year, event, team);
        } else {
            cli_selector cliSelect = new cli_selector();
            urlstr = cliSelect.urlselect(teamB, eventB, year, event, team, choose0, choose1, choose2, choose3, choose4);
        }
        call1.caller(urlstr, debug);
        if (gui)
            results.UI_ReturnData(debug, urlstr);
        else
            results.TERM_ReturnData(debug);
    }

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand");
    }

    public static void main(String[] args) {
        System.out.println("FRC-Datawizard v0.6");
        CommandLine cmd = new CommandLine(new mainStarter());
        if (args.length == 0) {
            cmd.usage(System.out);
        } else {
            cmd.execute(args);
        }

    }
}


