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
Follow the above instructions to run, and if you do not include any parameters GUI interface is launched (recommended).
```
Usage: FRC-Datawizard [-hV] [COMMAND]
Searches the FRC API
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  quick      Use flags and parameters to display information.  (GUI Display still
             possible via the -g command).
  prefmgr  Manage your API keys and default year.
  prompt   Use an interactive prompt to get data.  This is how you learn quick
             codes.
  status   Displays server information in the terminal. -a or --all-info shows
             advanced server information.
Licensed under EUPLv1.2
```
In order to display data:
1.  Choose either `quick` or `prompt`, quick for entering quick codes or prompt for choosing what data you want.
2.  Enter your parameters in any order, `--ftc-data` and `--gui-window` are true if specified, false if omitted.
* `--year <ENTER YEAR>` (****required**** unless default previously entered)
* `--event <ENTER EVENT>` (in six letter code for FRC, or 6+ letters FTC)
* `--team <ENTER TEAM NUM>`  
* `--ftc-data` If used, returns FTC Data instead of FRC Data (Seperate API Key required)
* `--gui-window` If used, returns data in a gui window with search functionality
* `--debug` If used, displays information such as the URL, JSON reponse, and status codes
For `quick` Enter each quick code, in order, seperated by a space.  State names require underscores for spaces.
Now, hit enter, and if using `prompt`, follow instructions, and then you will have your data!

Full `quick` command information and flags:
```
Usage: quick [-cdghV] [-e=<event>] [-t=<team>] [-y=<year>]
                            [Quick Code 1] [Quick Code 2] [Quick Code 3] [Quick 
                            Code 4] [Quick Code 5]
Use flags and parameters to display information.  (GUI Display still possible
via the -g command).
      [Quick Code 1]
      [Quick Code 2]
      [Quick Code 3]
      [Quick Code 4]
      [Quick Code 5]
  -c, --ftc-data        Show FTC Data instead of FRC data.  NOTE: Some menu
                          items are not available, and the prompt will reflect
                          that.
  -d, --debug           Display debugging messages, such as the url and
                          unparsed JSON.
  -e, --event=<event>   The event code to request data for.  Event codes can be
                          foound on the TBA or FIRST sites.
  -g, --gui-window      Uses a simple GUI window to display results for you.
  -h, --help            Show this help message and exit.
  -t, --team=<team>     The team number to request data for.
  -V, --version         Print version information and exit.
  -y, --year=<year>     Mandatory, unless default entered with prefmgr or GUI.
```
Full `prompt` command information and flags:
```
Usage: prompt [-cdghV] [-e=<event>] [-t=<team>] [-y=<year>]
Use an interactive prompt to get data.  This is how you learn quick codes.
  -c, --ftc-data        Show FTC Data instead of FRC data.  NOTE: Some menu
                          items are not available, and the prompt will reflect
                          that.
  -d, --debug           Display debugging messages, such as the url and
                          unparsed JSON.
  -e, --event=<event>   The event code to request data for.  Event codes can be
                          foound on the TBA or FIRST sites.
  -g, --gui-window      Uses a simple GUI window to display results for you.
  -h, --help            Show this help message and exit.
  -t, --team=<team>     The team number to request data for.
  -V, --version         Print version information and exit.
  -y, --year=<year>     Mandatory, unless default entered with prefmgr or GUI.
  ```

## Adding API key with prefmgr cli or GUI  (REQUIRED)
FIRST requires users of the API to register Simply register for your api here: https://frc-events.firstinspires.org/services/API for FRC and https://ftc-events.firstinspires.org/services/API/register for FTC.  Once you confirm your email, you will recieve an email with you username and key.  
To add to the program:
- For the GUI go to Options > Add Key > Set API Key > and enter your username and key, then click add key for the corresponding program. 
- For the CLI use the `prefmgr --set-XXX-key` (FRC or FTC) flag and input `username SPACE key` the key into the program.  
The program will make a file called `.frc-datawizard.properties`.  If you delete or move this file the program will no longer have your API key.  

#### Setting default year
In order to reduce the hassle of typing in a year every request, there is a feature to set a default.  If you do not specify a year in any mode, the default will be used.  There is a setting to set defaults on the options menu, or run `prefmgr --set-default-year <year>`.

Full `prefmgr` command information and flags:
```
Usage: prefmgr [-hlVw] [-y[=<setYear>]] [-R=<setR> <setR>]...
                              [-T=<setT> <setT>]...
Manage your API keys and default year.
  -h, --help               Show this help message and exit.
  -l, --list-preferences   List your API keys, and default year.
  -R, --set-FRC-key=<setR> <setR>
                           Set your FRC API key. Enter as USERNAME (Space) KEY
  -T, --set-FTC-key=<setT> <setT>
                           Set your FTC API key. Enter as USERNAME (Space) KEY
  -V, --version            Print version information and exit.
  -w, --wipe               Wipe the stored keys, and the default year.
  -y, --set-default-year[=<setYear>]
                           Set your default year, used unless you specify one.
```
### License
This program is licensed under the EUPL 1.2 European Union Public License. 
#### Libraries
This program utilizes the Picocli, Gson, JSONIter, and Jackson (Core, Annotations, and Databind) libraries.
