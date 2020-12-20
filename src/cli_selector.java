

public class cli_selector {
    String urlselect(boolean teamB, boolean eventB, int year, String event, int team, int choose0 , String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
        cli_selectAll selectAll1 = new cli_selectAll();
        String urlselect1;
        urlselect1 = selectAll1.selectA(teamB, eventB, year, event, team, choose0, choose1,choose2,choose3, choose4, choose5, choose6);
        return urlselect1;
    }
}
class check_empty {
    static void check_chooseX(String choosen) {
        if (choosen.equals("VVV")) {
            System.out.println("You need more paramters");
            System.exit(2);
        }
    }
}


class cli_selectAll {
    String selectA(boolean teamB, boolean eventB, int year, String event, int team, int choose0, String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
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
                    selectA = selectMES(selectA, choose0, choose1, choose2, choose3, choose4, choose5, choose6, event, team, teamB);
                    break;
                case 5:
                    selectA = selector.base + year + "/scores/" + event + "/";
                    selectA = selectMES(selectA, choose0, choose1, choose2, choose3, choose4, choose5, choose6, event, team, teamB);
                    break;
                case 6:
                    selectA = selector.base + year + "/schedule/" + event + "/";
                    selectA = selectMES(selectA, choose0, choose1, choose2, choose3, choose4, choose5, choose6, event, team, teamB);
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
                    check_empty.check_chooseX(choose2);
                    switch (choose1) {
                        case "1":
                            selectA = selectA + "?districtCode=" + choose2;
                            break;
                        case "2":
                            choose2 = choose2.replace(" ", "%20");
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
                    boolean dsB = false;
                    check_empty.check_chooseX(choose1);
                    if (choose1.equals("0"))
                        selectA = selector.base + year + "/rankings/district";
                    else {
                        check_empty.check_chooseX(choose2);
                        selectA = selector.base + year + "/rankings/district/" + choose2;
                        dsB = true;
                    }
                    int select;
                    int toppage;
                    check_empty.check_chooseX(choose2);
                    check_empty.check_chooseX(choose3);
                    if (dsB) {
                        toppage = Integer.parseInt(choose3);
                        check_empty.check_chooseX(choose4);
                        select = Integer.parseInt(choose4);
                    } else {
                        toppage = Integer.parseInt(choose2);
                        select = Integer.parseInt(choose3);
                    }
                    switch (toppage) {
                        case 1:
                            selectA = selectA + "/?top=" + select;
                            break;
                        case 2:
                            selectA = selectA + "/?page=" + select;
                            break;
                        default:
                            System.out.println("Incorrect Parameter :" + toppage);
                            System.exit(2);
                    }
                }
                break;
            case 13:
                selectA = selector.base + year + "/districts";
                break;
        }
        if (teamB) {
            switch (choose0) {
                case 14:
                    selectA = selector.base + year + "/avatars?teamNumber=" + team;
                    break;
            }
        }
        if (selectA==null) {
            System.out.println("Invalid Parameters, you need at least one parameter, or your parameters do not match your inputted info.");
            System.exit(2);
        }
        return selectA;
    }
    static String selectMES (String selectBEG, int choose0, String choose1, String choose2, String choose3, String choose4, String choose5, String choose6, String event, int team, boolean teamB) {
        String selectMES = selectBEG;
        int selectMaMd = choose0;
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
        if (teamB) {
            if (selectMaMd == 1)
                selectMES = selectMES + "?teamNumber=" + team;
            else
                selectMES = selectMES + qualP + "?teamNumber=" + team;
        } else {
            selectMES = selectMES + qualP;
            boolean maB = false;
            int answer1;
            int answer2;
            int answer3;
            check_empty.check_chooseX(choose2);
            if (selectMaMd == 6) {
                maB = true;
                selectMaMd = Integer.parseInt(choose2);
                if (selectMaMd == 1)
                    selectMES = selectMES + "/hybrid";
            }
            if (maB) {
                check_empty.check_chooseX(choose3);
                answer1 = Integer.parseInt(choose3);
            } else {
                answer1 = Integer.parseInt(choose2);
            }
            if (answer1 != 0)
                selectMES = selectMES + "?matchNumber=" + answer1;
            else {
                if (maB) {
                             check_empty.check_chooseX(choose4);
                              answer2 = Integer.parseInt(choose4);
                } else {
                        check_empty.check_chooseX(choose3);
                        answer2 = Integer.parseInt(choose3);
                }
                if (answer2 != 0)
                    selectMES = selectMES + "?start=" + answer2;
                else {
                    if (maB) {
                        check_empty.check_chooseX(choose5);
                        answer3 = Integer.parseInt(choose5);
                    } else {
                        check_empty.check_chooseX(choose4);
                        answer3 = Integer.parseInt(choose4);
                    }
                    selectMES = selectMES + "?end=" + answer3;
                }
           }
        }
        return selectMES;
    }
}


