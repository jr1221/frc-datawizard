# FRC-Datawizard
An application written in Java to search the FRC API and FTC API, the quickest updating source of FRC + FTC info. FRC-Datawizard, which is unaffiliated with FIRST, allows the user to harness the data of the FRC + FTC APIs in a fully featured GUI.

### Features
- Can call all endpoints of the FRC Events v2 API ***and*** all endpoints of the FTC Events API
- Features are avaiable in a GUI & CLI
  - Save your FRC and FTC API keys
  - Save a default year for quick reuse
  - Toggle between FRC/FTC data
  - Toggle between terminal and GUI output
  - Search bar to see the datapoint you want!
  
- Use Quick Codes to access data quickly
    - or
- Use a Prompt to interactively choose what data you would like to see
  


### Installation
This program comes packaged in a JAR file for easy use with Java JDK 11 across all OSes.
#### Linux
Debian: `apt install openjdk-11-jre`  
Arch: `pacman -S jre11-openjdk`  

Run ` java --version` to confirm it is version 11.XXX.  If it is not version 11 reference the direct path of the java executable or;  
Debian: `update-alternatives --config java` and choose the openjdk-11-jre from the list.  
Arch: `archlinux-java set java-11-openjdk`  

Nearly all distros have this package, but the package name may be worded differently.  

#### Windows
The only way to install java on windows without registering (albiet free) is via https://github.com/ojdkbuild/ojdkbuild.  Use the msi installer under version 11.XXX

### How To Run
First head over to releases and download the file ending in `.jar`
Next, open the terminal (or the command prompt of your choice in Windows) and enter the path to the jarfile with cd before (ex. `cd /Users/YOUR_USERNAME/Downloads/frc-datawizard-XXXXX.jar)`.  Next, run `java -jar NAME_OF_FILE.jar`, name of file being that which was downloaded under assests in the releases page. This will launch the gui.  The docs for the CLI are below.

****_IMPORTANT: See [farther down on this page](#adding-key-with-prefmgr) to add your key, required to use the program._****

## Usage

```
Usage: [-dghV] [COMMAND]
Searches the FRC API
  -d, --debug        Display debugging messages.
  -g, --gui-window   Uses a simple GUI window to display results for you.  Use
                       all other flags normally.
  -h, --help         Show this help message and exit.
  -V, --version      Print version information and exit.
Commands:
  cli                  Use the cli flags (at least partially)
  prompt               Enter the interactive Prompt
  prefmgr              Manage your API key and other preferences.
  -s, --server-status  Displays server information in the terminal.  Do not
                         specify with any commands or flags.
Licensed under EUPLv2
```
`--debug` and `--gui-window` need to be specified with either `cli` or `prompt`.  
The program flow is as follows.
1.  Choose `-g` or omit it based on whether you would like data in a new window or in the terminal.
2.  EITHER:  
A. Choose `cli` for using command line flags to input; NOTE: Unless default specified, `--year` is required.
* `--year <ENTER YEAR>` 
* `--event <ENTER EVENT>` (in six letter code)
* `--team <ENTER TEAM NUM>`  
    * The `--continue-cli` flag allows the user to enter the menu numbers of the prompt in one command, (seperated by spaces).  If omitted, a menu is displayed.
    
    B. Choose `prompt` if you would like the program to ask you for each item.
    
```
Usage: cli [-chV] [-e=<arg1>] [-t=<arg2>] [-y=<arg0>] [<arg4>]
                          [<arg5>] [<arg6>] [<arg7>] [<arg8>]
Use the cli flags (at least partially)
      [<arg4>]
      [<arg5>]
      [<arg6>]
      [<arg7>]
      [<arg8>]
  -c, --continue-cli   Do not enter next interactive prompt
  -e, --event=<arg1>   The event code to request data for
  -h, --help           Show this help message and exit.
  -t, --team=<arg2>    The team number to request data for
  -V, --version        Print version information and exit.
  -y, --year=<arg0>    mandatory number, unless default entered.
```
`prefmgr` allows the user to add their api keys with ease.  
## Adding API key with prefmgr cli or GUI  (REQUIRED)
FIRST requires users of the API to register Simply register for your api here: https://frc-events.firstinspires.org/services/API for FRC and https://ftc-events.firstinspires.org/services/API/register for FTC.  Once you confirm your email, you will recieve an email with you username and key.  
To add to the program:
- For the GUI go to Options > Add Key > Set API Key > and enter your username and key, then clikc add key for the corresponding competition. 
- For the CLI use the `prefmgr --set-XXX-key` (FRC or FTC) flag and input `(username) SPACE (key)` the key into the program.  
The program will make a file called `.frc-datawizard.properties`.  If you delete or move this file the program will no longer have your API key.  
Also in prefmgr, is `--set-year <YEAR>` which automatically sets that year when using `cli` to input year.  If you specify a year, your default will be ignored.
```
Usage: prefmgr [-hlsVw] [-y=<arg2>]
Manage your API key and other preferences.
  -h, --help              Show this help message and exit.
  -l, --list-key          List your API key
  -s, --set-key           Set your API key
  -V, --version           Print version information and exit.
  -w, --wipe              Wipe the stored key, and the defaults.
  -y, --set-year=<arg2>   Set your default year, used unless you specify one.
```
### License
This program is licensed under the EUPL 1.2 European Union Public License. 
#### Libraries
This program utilizes the Picocli, Gson, JSONIter, and Jackson (Core, Annotations, and Databind) libraries.
