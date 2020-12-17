import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import java.io.IOException;


@Command(name = "FRC-Datawizard", footer = "Apache License v2",
        description = "Searches the FRC API", mixinStandardHelpOptions = true, version = "Version 0.6 (Development)")
public class mainStarter implements Runnable {
    @Spec
    CommandLine.Model.CommandSpec spec;
    call call1 = new call();

    @Option(names = {"-d","--debug"}, description = "Display server status information and debugging messages") boolean debug;
    @Option(names = {"-g","--gui-data"}, description = "Uses a simple GUI window to display results for you") boolean gui;
    @Command(name = "prompt", description = "Enter the interactive Prompt")
    void interactive() {
        interactiveBegin.interactive();
    }

    @Command(name = "cli", description = "Use the cli flags (at least partially)", mixinStandardHelpOptions = true)
    void cli( @Option(names = {"-y","--year"}, required = true, description = "mandatory number") int year,
              @Option(names = {"-e", "--event"}, description = "The event code to request data for") String event,
              @Option(names = {"-t", "--team"}, description = "The team number to request data for") int team,
              @Option(names = {"-c","--continue-cli"},description = "Do not enter next interactive prompt") boolean interB,
              @Parameters(index = "0") int choose0,
              @Parameters(index = "1",arity = "0..1",defaultValue = "VVV") String choose1,
              @Parameters(index = "2",arity = "0..1",defaultValue = "VVV") String choose2,
              @Parameters(index = "3",arity = "0..1",defaultValue = "VVV") String choose3,
              @Parameters(index = "4",arity = "0..1",defaultValue = "VVV") String choose4,
              @Parameters(index = "5",arity = "0..1",defaultValue = "VVV") String choose5,
              @Parameters(index = "6",arity = "0..1",defaultValue = "VVV") String choose6){
        boolean teamB = false;
        boolean eventB = false;
        if (event != null) {
            eventB = true;

        }
        if (team != 0) {
            teamB = true;

        }
        String urlstr;
        if (!interB) {
            selector selector1 = new selector();
            urlstr = selector1.urlselect(teamB, eventB, year, event, team);
        } else {
            cli_selector cliSelect = new cli_selector();
            urlstr = cliSelect.urlselect(teamB, eventB, year, event, team, choose0, choose1, choose2, choose3, choose4, choose5, choose6);
        }
        try {
            call1.caller(urlstr, debug, gui);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand");
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new mainStarter());
        if (args.length == 0) {
            cmd.usage(System.out);
        } else {
            cmd.execute(args);
        }
    }
}


