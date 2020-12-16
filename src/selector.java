import java.util.Scanner;


public class selector {
    public static final String base = "https://frc-api.firstinspires.org/v2.0/";

    String urlselect(boolean teamB, boolean eventB, int year, String event, int team) {
        selectAll selectAll1 = new selectAll();
        String urlselect1;
        if (eventB)
            urlselect1 = selectAll1.selectYE(teamB, year, event, team);
        else
            urlselect1 = selectAll1.selectY(teamB, year, team, event);
        return urlselect1;
    }
}


class selectAll {
    public int select;
    public Scanner choose = new Scanner(System.in);

    String selectYE(boolean teamB, int year, String event, int team) {
        selectAll selectorMA = new selectAll();
        String selectYE = null;
        System.out.println("Options;\n (1) Alliance selections in event \n (2) Awards info in event \n (3) Match Data, Score results, Match schedule" +
                "\n (4) Rankings in event \n (5) Event listings. \n (6) Team listings.");
        select = choose.nextInt();
        switch (select) {
            case 1:
                selectYE = selector.base + year + "/alliances/" + event;
                break;
            case 2:
                selectYE = selector.base + year + "/awards/" + event;
                if (teamB)
                    selectYE = selectYE + "/" + team;
                break;
            case 3:
                selectYE = selectorMA.selectMa(teamB, year, event, team);
                break;
            case 4:
                selectYE = selector.base + year + "/rankings/" + event + "/";
                if (teamB)
                    selectYE = selectYE + "?teamNumber=" + team;
                else {
                    System.out.println("Options;\n (0) All rankings \n (<x>) Top x Rankings");
                    select = choose.nextInt();
                    if (select != 0)
                        selectYE = selectYE + "?top=" + select;
                }
                break;
            case 5:
                selectYE = selectorMA.selectEL(true, teamB, year, team, event);
                break;
            case 6:
                selectYE = selectorMA.selectTL(true, teamB, year, team, event);
                break;
            default:
                System.out.println("Incorrect Parameter: "+ select);
                System.exit(2);
        }
        return selectYE;
    }





    String selectMa(boolean teamB, int year, String event, int team) {
        String qualP = null;
        String selectMa = null;
        int selectMaMd;
        System.out.println("Options;\n (1) Event results\n (2) Scoring details\n (3) Match schedule");
        select = choose.nextInt();
        switch (select) {
            case 1:
                selectMa = selector.base + year + "/matches/" + event + "/";
                break;
            case 2:
                selectMa = selector.base + year + "/scores/" + event + "/";
                break;
            case 3:
                selectMa = selector.base + year + "/schedule/" + event + "/";
                break;
            default:
                System.out.println("Incorrect Parameter: " + select);
                System.exit(2);
        }
        selectMaMd = select;
        System.out.println("Options;\n (1) Qualification matches\n (2) Playoff matches");
        select = choose.nextInt();
        switch (select) {
            case 1:
                qualP = "qual";
                break;
            case 2:
                qualP = "playoff";
                break;
            default:
                System.out.println("Incorrect Parameter: " + select);
                System.exit(2);
        }
        if (teamB) {
            if (selectMaMd==1)
                selectMa = selectMa + "?teamNumber="+team;
            else
                selectMa = selectMa+qualP+"?teamNumber="+team;
        }
        else {
            selectMa = selectMa + qualP;
            if (selectMaMd==3) {
                System.out.println("Would you like to enter hybrid event schedule mode? \n (1) Yes \n (2) No");
                selectMaMd = choose.nextInt();
                if (selectMaMd == 1)
                    selectMa = selectMa + "/hybrid";
            }
            System.out.println("Options;\n (0) Specify search matches to start or end number \n (<x>) Match number x");
            select = choose.nextInt();
            if (select != 0)
                selectMa = selectMa + "?matchNumber=" + select;
            else {
                System.out.println("Options;\n (0) Enter an end parameter for match search. \n (<x>) Start at x in match search");
                select = choose.nextInt();
                if (select != 0)
                    selectMa = selectMa + "?start=" + select;
                else {
                    System.out.println("(<x>) End at x in match search.");
                    select = choose.nextInt();
                    selectMa = selectMa + "?end=" + select;
                }
            }
        }
        return selectMa;
    }
    String selectEL(boolean eventB, boolean teamB, int year, int team, String event) {
        String selectEL = selector.base +year+"/events/";
        Scanner chooseStr = new Scanner(System.in);
        String selectStr;
        if (teamB)
            selectEL= selectEL+"?teamNumber="+team;
        else if (eventB)
            selectEL=selectEL+event;
        else {
            System.out.println("Options;\n (0) DO not filter by district\n (<x>) Enter district code to filter by (ex. NE)");
            selectStr = chooseStr.nextLine();
            if (!(selectStr.equals("0")))
                selectEL = selectEL+"?districtCode="+selectStr;
            else {
                System.out.println("Would you like to exclude district events and championships? Yes (1) No (2)");
                select = choose.nextInt();
                if (select==1)
                    selectEL=selectEL+"?excludeDistrict=true";
            }
        }
        return selectEL;
    }
    String selectTL(boolean eventB, boolean teamB, int year, int team, String event) {
        String selectTL = selector.base +year+"/teams/";
        Scanner chooseStr = new Scanner(System.in);
        String selectStr;
        if (teamB)
            selectTL= selectTL+"?teamNumber="+team;
        else if (eventB)
            selectTL=selectTL+"?eventCode="+event;
        else {
            System.out.println("Options;\n (1) Filter by district code\n (2) Filter by state");
            select = choose.nextInt();
            switch (select) {
                case 1:
                    System.out.println("(<x>) District code x to filter by");
                    selectStr = chooseStr.nextLine();
                    selectTL = selectTL + "?districtCode=" + selectStr;
                    break;
                case 2:
                    System.out.println("(<x>) State x to filter by");
                    selectStr = chooseStr.nextLine();
                    selectStr= selectStr.replace(" ","%20");
                    selectTL=selectTL+"?state="+selectStr;
                    break;
                default:
                    System.out.println("Incorrect Parameter: " + select);
                    System.exit(2);
            }
        }
        return selectTL;
    }

    String selectY(boolean teamB, int year, int team, String event) {
        String selectStr;
        selectAll selectEL = new selectAll();
        Scanner chooseStr = new Scanner(System.in);
        String selectY = null;
        System.out.println("Options;\n (1) Year info\n (2) Awards info for that year \n (3) District rankings"+
                "\n (4) Event listings\n (5) District listings\n (6) Team listings");
        if (teamB)
            System.out.println(" (7) Team Avatar (Display)");
        select = choose.nextInt();
        switch (select) {
            case 1:
                selectY = selector.base + year;
                break;
            case 2:
                if (teamB)
                    selectY = selector.base + year + "/awards/" + team;
                else
                    selectY = selector.base + year + "/awards/list";
                break;
            case 3:
                if (teamB)
                    selectY = selector.base + year + "/rankings/district/?teamNumber=" + team;
                else {
                    System.out.println("Options;\n (0) Print all district info\n (1) Print info by district code");
                    select = choose.nextInt();
                    if (select == 0)
                        selectY = selector.base + year + "/rankings/district";
                    else {
                        System.out.println("(<x>) District code x to show rankings");
                        selectStr = chooseStr.nextLine();
                        selectY = selector.base + year + "/rankings/district/" + selectStr;
                    }
                    System.out.println("Options;\n (1) Top rankings\n (2) Page numbers");
                    select = choose.nextInt();
                    int toppage = select;
                    System.out.println("(<x>) x top rankings or page numbers to limit results to");
                    select = choose.nextInt();
                    if (toppage == 1)
                        selectY = selectY + "/?top=" + select;
                    else if (toppage == 2)
                        selectY = selectY + "/?page=" + select;
                }
                break;
            case 4:
                selectY = selectEL.selectEL(false,  teamB,  year,  team, event);
                break;
            case 5:
                selectY = selector.base +year+"/districts";
                break;
            case 6:
                selectY = selectEL.selectTL(false,  teamB,  year,  team, event);
                break;
            case 7:
                selectY  = selector.base +year+"/avatars?teamNumber="+team;
                break;
            default:
                System.out.println("Incorrect Parameter: " + select);
                System.exit(2);
        }
        return selectY;
    }
}


