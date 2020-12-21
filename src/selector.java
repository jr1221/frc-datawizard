import java.util.Scanner;


public class selector {
    public static final String base = "https://frc-api.firstinspires.org/v2.0/";

    String urlselect(boolean teamB, boolean eventB, int year, String event, int team) {
        selectAll selectAll1 = new selectAll();
        String urlselect1;
        urlselect1 = selectAll1.selectA(teamB, year, event, eventB, team);
        return urlselect1;
    }
}


class selectAll {
    public int select;
    public Scanner choose = new Scanner(System.in);
    public Scanner chooseStr = new Scanner(System.in);

    String selectA(boolean teamB, int year, String event, boolean eventB, int team) {
        String selectA = null;
        String selectStr;
        System.out.println("Options;");
        if (eventB) {
            System.out.println(
                    "\n (3) Alliance selections in event " +
                            "\n (4) Match Data" +
                            "\n (5) Score Results" +
                            "\n (6) Match Schedule" +
                            "\n (7) Rankings in event ");
        }
        System.out.println(
                " (8) Event listings"+
                        "\n (9) Team listings"+
                        "\n (10) Year info" +
                        "\n (11) Awards info for that year" +
                        "\n (12) District rankings" +
                        "\n (13) District listings ");
        if (teamB)
            System.out.println(" (14) Team Avatar (Display)");
        select = choose.nextInt();
        if (eventB) {
            switch (select) {
                case 3:
                    selectA = selector.base + year + "/alliances/" + event;
                    return selectA;
                case 4:
                    selectA = selector.base + year + "/matches/" + event + "/";
                    selectA = selectMa(teamB, team, selectA);
                    return selectA;
                case 5:
                    selectA = selector.base + year + "/scores/" + event + "/";
                    selectA = selectMa(teamB, team, selectA);
                    return selectA;
                case 6:
                    selectA = selector.base + year + "/schedule/" + event + "/";
                    selectA = selectMa(teamB, team, selectA);
                    return selectA;
                case 7:
                    selectA = selector.base + year + "/rankings/" + event + "/";
                    if (teamB) {
                        selectA = selectA + "?teamNumber=" + team;
                        return selectA;
                    }
                    System.out.println("Options;\n (0) All rankings \n (<x>) Top x Rankings");
                    select = choose.nextInt();
                    if (select != 0)
                        selectA = selectA + "?top=" + select;
                    return selectA;
            }
        }
        switch (select) {
            case 8:
                selectA = selector.base + year + "/events/";
                if (teamB) {
                    selectA = selectA + "?teamNumber=" + team;
                    return selectA;
                }
                if (eventB) {
                    selectA = selectA + event;
                    return selectA;
                }
                System.out.println("Options;\n (0) DO not filter by district\n (<x>) Enter district code to filter by (ex. NE)");
                selectStr = chooseStr.nextLine();
                if (!(selectStr.equals("0")))
                    selectA = selectA + "?districtCode=" + selectStr;
                else {
                    System.out.println("Would you like to exclude district events and championships? Yes (1) No (2)");
                    select = choose.nextInt();
                    if (select == 1)
                        selectA = selectA + "?excludeDistrict=true";
                }
                return selectA;
            case 9:
                selectA = selector.base + year + "/teams/";
                if (teamB) {
                    selectA = selectA + "?teamNumber=" + team;
                    return selectA;
                }
                if (eventB) {
                    selectA = selectA + "?eventCode=" + event;
                    return selectA;
                }
                System.out.println("Options;\n (1) Filter by district code\n (2) Filter by state");
                select = choose.nextInt();
                switch (select) {
                    case 1:
                        System.out.println("(<x>) District code x to filter by");
                        selectStr = chooseStr.nextLine();
                        selectA = selectA + "?districtCode=" + selectStr;
                        break;
                    case 2:
                        System.out.println("(<x>) State x to filter by (!!!Use an underscore for a space)");
                        selectStr = chooseStr.nextLine();
                        selectStr = selectStr.replace("_", "%20");
                        selectA = selectA + "?state=" + selectStr;
                        break;
                    default:
                        System.out.println("Incorrect Parameter: " + select);
                        System.exit(2);
                }
                return selectA;
            case 10:
                selectA = selector.base + year;
                return selectA;
            case 11:
                if (teamB) {
                    selectA = selector.base + year + "/awards/" + team;
                    return selectA;
                }
                if (eventB) {
                    selectA = selector.base + year + "/awards/" + event;
                    return selectA;
                }
                selectA = selector.base + year + "/awards/list";
                return selectA;
            case 12:
                if (teamB) {
                    selectA = selector.base + year + "/rankings/district/?teamNumber=" + team;
                    return selectA;
                }
                System.out.println("Options;\n (0) Print all district info\n (1) Print info by district code");
                select = choose.nextInt();
                if (select == 0)
                    selectA = selector.base + year + "/rankings/district";
                else {
                    System.out.println("(<x>) District code x to show rankings");
                    selectStr = chooseStr.nextLine();
                    selectA = selector.base + year + "/rankings/district/" + selectStr;
                }
                System.out.println("Options;\n (1) Top rankings\n (2) Page numbers");
                select = choose.nextInt();
                int toppage = select;
                System.out.println("(<x>) x top rankings or page numbers to limit results to");
                select = choose.nextInt();
                if (toppage == 1)
                    selectA = selectA + "/?top=" + select;
                else if (toppage == 2)
                    selectA = selectA + "/?page=" + select;
                return selectA;
            case 13:
                selectA = selector.base + year + "/districts";
                return selectA;
        }
        if (teamB && select == 14) {
            selectA = selector.base + year + "/avatars?teamNumber=" + team;
            return selectA;
        }
        System.out.println("Invalid Parameters");
        System.exit(2);
        return selectA;
    }


    String selectMa(boolean teamB, int team, String selectMa) {
        String qualP = null;
        int selectMaMd;
        selectMaMd = select;
        System.out.println(selectMaMd);
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
            selectMa = selectMa + qualP + "?teamNumber=" + team;
            return selectMa;
        }
        selectMa = selectMa + qualP;
        if (selectMaMd == 6) {
            System.out.println("Would you like to enter hybrid event schedule mode? \n (1) Yes \n (2) No");
            select = choose.nextInt();
            if (select == 1) {
                selectMa = selectMa + "/hybrid";
            }
        }
        if (!(selectMaMd == 6)) {
            System.out.println("Options;\n (0) Specify search matches to start or end number \n (<x>) Match number x");
            select = choose.nextInt();
            if (select != 0) {
                selectMa = selectMa + "?matchNumber=" + select;
                return selectMa;
            }
        }
        System.out.println("Options;\n (0) Enter an end parameter for match search. \n (<x>) Start at x in match search");
        select = choose.nextInt();
        if (select != 0)
            selectMa = selectMa + "?start=" + select;
        else {
            System.out.println("(<x>) End at x in match search.");
            select = choose.nextInt();
            selectMa = selectMa + "?end=" + select;
        }
        return selectMa;
    }
}


