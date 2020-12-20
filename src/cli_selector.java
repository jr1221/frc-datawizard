

public class cli_selector {
    String urlselect(boolean teamB, boolean eventB, int year, String event, int team, int choose0 , String choose1, String choose2, String choose3, String choose4) {
        cli_selectAll selectAll1 = new cli_selectAll();
        String urlselect1;
        urlselect1 = selectAll1.selectA(teamB, eventB, year, event, team, choose0, choose1,choose2,choose3, choose4);
        return urlselect1;
    }
}
class check_empty {
    static void check_chooseX(String choosen) {
        if (choosen.equals("No Parameter Selected")) {
            System.out.println("You need more paramters");
            System.exit(2);
        }
    }
}


class cli_selectAll {
    String selectA(boolean teamB, boolean eventB, int year, String event, int team, int choose0, String choose1, String choose2, String choose3, String choose4) {
        String selectA = null;
        if (eventB) {
            switch (choose0) {
                case 2:
                    selectA = selector.base + year + "/alliances/" + event;
                    break;
                case 3:
                    selectA = selector.base + year + "/awards/" + event;
                    if (teamB)
                        selectA = selectA + "/" + team;
                    break;
                case 4:
                    selectA = selector.base + year + "/matches/" + event + "/";
                    selectA = selectMES(selectA, choose0, choose1, choose2, choose3, choose4, team, teamB);
                    break;
                case 5:
                    selectA = selector.base + year + "/scores/" + event + "/";
                    selectA = selectMES(selectA, choose0, choose1, choose2, choose3, choose4, team, teamB);
                    break;
                case 6:
                    selectA = selector.base + year + "/schedule/" + event + "/";
                    selectA = selectMES(selectA, choose0, choose1, choose2, choose3, choose4, team, teamB);
                    break;
                case 7:
                    selectA = selector.base + year + "/rankings/" + event + "/";
                    if (teamB)
                        selectA = selectA + "?teamNumber=" + team;
                    else {
                        check_empty.check_chooseX(choose1);
                        if (!choose1.equals("0"))
                            selectA = selectA + "?top=" + choose1;
                    }
                    break;
            }
        }
        switch (choose0) {
            case 8:
                selectA = selector.base + year + "/events/";
                if (teamB)
                    selectA = selectA + "?teamNumber=" + team;
                else if (eventB)
                    selectA = selectA + event;
                else {
                    check_empty.check_chooseX(choose1);
                    if (!(choose1.equals("0")))
                        selectA = selectA + "?districtCode=" + choose1;
                    else {
                        check_empty.check_chooseX(choose2);
                        if (choose2.equals("1"))
                            selectA = selectA + "?excludeDistrict=true";
                    }
                }
                break;
            case 9:
                selectA = selector.base + year + "/teams/";
                if (teamB)
                    selectA = selectA + "?teamNumber=" + team;
                else if (eventB)
                    selectA = selectA + "?eventCode=" + event;
                else {
                    check_empty.check_chooseX(choose1);
                    check_empty.check_chooseX(choose2);
                    switch (choose1) {
                        case "1":
                            selectA = selectA + "?districtCode=" + choose2;
                            break;
                        case "2":
                            choose2 = choose2.replace("_", "%20");
                            selectA = selectA + "?state=" + choose2;
                            break;
                        default:
                            System.out.println("Incorrect Parameter: " + choose1);
                            System.exit(2);
                    }
                }
                break;
            case 10:
                selectA = selector.base + year;
                break;
            case 11:
                if (teamB)
                    selectA = selector.base + year + "/awards/" + team;
                else
                    selectA = selector.base + year + "/awards/list";
                break;
            case 12:
                if (teamB)
                    selectA = selector.base + year + "/rankings/district/?teamNumber=" + team;
                else {
                    check_empty.check_chooseX(choose1);
                    if (choose1.equals("0"))
                        selectA = selector.base + year + "/rankings/district";
                    else {
                        selectA = selector.base + year + "/rankings/district/" + choose1;
                    }
                    switch (choose2) {
                        case "1":
                            selectA = selectA + "/?top=" + choose3;
                            break;
                        case "2":
                            selectA = selectA + "/?page=" + choose3;
                            break;
                        default:
                            System.out.println("Incorrect Parameter :" + choose2);
                            System.exit(2);
                    }
                }
                break;
            case 13:
                selectA = selector.base + year + "/districts";
                break;
        }
        if (teamB) {
            if (choose0 == 14) {
                selectA = selector.base + year + "/avatars?teamNumber=" + team;
            }
        }
        if (selectA == null) {
            System.out.println("Invalid Parameters, you need at least one parameter, or your parameters do not match your inputted info.");
            System.exit(2);
        }
        return selectA;
    }

    static String selectMES(String selectBEG, int choose0, String choose1, String choose2, String choose3, String choose4, int team, boolean teamB) {
        String selectMES = selectBEG;
        String qualP = null;
        switch (choose1) {
            case "1":
                qualP = "qual";
                break;
            case "2":
                qualP = "playoff";
                break;
            default:
                System.out.println("Incorrect Parameter: " + choose1);
                System.exit(2);
        }
        if (teamB)
            selectMES = selectMES + qualP + "?teamNumber=" + team;
        else {
            selectMES = selectMES + qualP;
            boolean maB = false;
            check_empty.check_chooseX(choose2);
            if (choose0 == 6) {
                maB = true;
                if (choose2.equals("1")) {
                    selectMES = selectMES + "/hybrid/";
                }

            }
            if (!maB && !choose2.equals("0"))
                selectMES = selectMES + "?matchNumber=" + choose2;
            else {
                check_empty.check_chooseX(choose3);
                if (!choose3.equals("0"))
                    selectMES = selectMES + "?start=" + choose3;
                else {
                    check_empty.check_chooseX(choose4);
                    selectMES = selectMES + "?end=" + choose4;
                }
            }
        }
        return selectMES;
    }
}


