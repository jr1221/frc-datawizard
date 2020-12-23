# frc-datawizard
A CLI application written in java to search the FRC API and organize their extensive data (from 2015+).  
FRC-Datawizard, which is unaffiliated with FIRST, accelerates what would be manual calls to an 
interactive menu that guides all options available with the inputted data points.  

### Features
* Can call all endpoints of the FRC Events v2 API.
* Thanks to the Jackson Databind libraries, can support all years the FRC API supports (basic data 2006+) (detailed data 2015+) (avatars 2018+).
* Renders team avatars in a simple window
* Shows data in a simple window, with refresh and search capabilities.
* Helpful interactive prompts to guide in options for data requesting.
* Quick input codes that you can record, so you can bypass the prompt.
* Interactive prompt to save, test, and manage your API key.
* Ineractive prompt to save default year.

### Installation
This program comes packaged in a JAR file for easy use with java jdk 11, and it is cross platform.
#### Linux
Debian: `apt install openjdk-11-jre`  
Arch: `pacman -S jre11-openjdk`  

Run ` java --version` to confirm it is version 11.XXX.  If it is not version 11 reference the direct path of the java executable or;  
Debian: `update-alternatives --config java` and choose the openjdk-11-jre from the list.  
Arch: `archlinux-java set java-11-openjdk`  

Most other distros have this package, but the package name may be worded differently.  

#### Windows
The only way to install java on windows the FOSS way is via https://github.com/ojdkbuild/ojdkbuild.  Use the msi installer under version 11.XXX

### How To Run
Open the terminal (or the command prompt of your choice in Windows) and enter `cd /path/to/the/.jar/`.  Next, run `java -jar NAME_OF_FILE.jar`, name of file being that which was downloaded under assests in the releases page. _IMPORTANT: See [this](Adding-key-with-prefmgr) to add your key, required to use the program._

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
#### Adding key with prefmgr
Simply register for your api here: https://frc-events.firstinspires.org/services/API and use the `--set-key` flag to add the key into the program.  It will make a file called `.frc-datawizard.properties`.  If you delete or move this file the program will not run.  Also, be sure to add a key before you `--list-key`, as an error means the program found the file empty or non-existent.  
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
