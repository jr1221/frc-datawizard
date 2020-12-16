

public class cli_selector {
    String urlselect(boolean teamB, boolean eventB, int year, String event, int team, int choose0 , String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
        cli_selectAll selectAll1 = new cli_selectAll();
        String urlselect1;
        if (eventB) {
            urlselect1 = selectAll1.selectYE(teamB, year, event, team, choose0, choose1,choose2,choose3, choose4, choose5, choose6);
        } else {
            urlselect1 = selectAll1.selectY(teamB, year, team, event, choose0, choose1, choose2, choose3, choose4);
        }
        return urlselect1;
    }
}
class check_empty {
    void check_chooseX(String choosen) {
        if (choosen.equals("VVV")) {
            System.out.println("You need more paramters");
            System.exit(2);
        }
    }
}


class cli_selectAll {
    check_empty checkerX = new check_empty();
    String selectYE(boolean teamB, int year, String event, int team, int choose0, String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
        cli_selectAll selectorMA = new cli_selectAll();
        String selectYE = null;
        switch (choose0) {
            case 1:
                selectYE = selector.base + year + "/alliances/" + event;
                break;
            case 2:
                selectYE = selector.base + year + "/awards/" + event;
                if (teamB)
                    selectYE = selectYE + "/" + team;
                break;
            case 3:
                selectYE = selectorMA.selectMa(teamB, year, event, team, choose1, choose2, choose3, choose4, choose5, choose6);
                break;
            case 4:
                selectYE = selector.base + year + "/rankings/" + event + "/";
                if (teamB)
                    selectYE = selectYE + "?teamNumber=" + team;
                else {
                    checkerX.check_chooseX(choose1);
                    if (!choose1.equals("0"))
                        selectYE = selectYE + "?top=" + choose1;
                }
                break;
            case 5:
                selectYE = selectorMA.selectEL(true, teamB, year, team, event, choose1, choose2);
                break;
            case 6:
                selectYE = selectorMA.selectTL(true, teamB, year, team, event, choose1, choose2);
                break;
            default:
                System.out.println("Incorrect Parameter: "+ choose0);
                System.exit(2);
        }
        return selectYE;
    }


    String selectMa(boolean teamB, int year, String event, int team, String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
        String qualP = null;
        String selectMa = null;
        int selectMaMd;
        checkerX.check_chooseX(choose1);
        switch (choose1) {
            case "1":
                selectMa = selector.base + year + "/matches/" + event + "/";
                break;
            case "2":
                selectMa = selector.base + year + "/scores/" + event + "/";
                break;
            case "3":
                selectMa = selector.base + year + "/schedule/" + event + "/";
                break;
            default:
                System.out.println("Incorrect Parameter: "+ choose1);
                System.exit(2);
        }
        selectMaMd = Integer.parseInt(choose1);
        checkerX.check_chooseX(choose2);
        switch (choose2) {
            case "1":
                qualP = "qual";
                break;
            case "2":
                qualP = "playoff";
                break;
            default:
                System.out.println("Incorrect Parameter: " + choose2);
                System.exit(2);
        }
        if (teamB) {
            if (selectMaMd == 1)
                selectMa = selectMa + "?teamNumber=" + team;
            else
                selectMa = selectMa + qualP + "?teamNumber=" + team;
        } else {
            selectMa = selectMa + qualP;
            boolean maB = false;
            int answer1;
            int answer2;
            int answer3;
            checkerX.check_chooseX(choose3);
            checkerX.check_chooseX(choose4);
            checkerX.check_chooseX(choose5);
            if (selectMaMd == 3) {
                maB = true;
                selectMaMd = Integer.parseInt(choose3);
                if (selectMaMd == 1)
                    selectMa = selectMa + "/hybrid";
            }
            if (maB) {
                answer1 = Integer.parseInt(choose4);
                answer2 = Integer.parseInt(choose5);
                checkerX.check_chooseX(choose6);
                answer3 = Integer.parseInt(choose6);
            } else {
                answer1 = Integer.parseInt(choose3);
                answer2 = Integer.parseInt(choose4);
                answer3 = Integer.parseInt(choose5);
            }
            if (answer1 != 0)
                selectMa = selectMa + "?matchNumber=" + answer1;
            else {
                if (answer2 != 0)
                    selectMa = selectMa + "?start=" + answer2;
                else {
                    selectMa = selectMa + "?end=" + answer3;
                }
            }
        }
        return selectMa;
    }

    String selectEL(boolean eventB, boolean teamB, int year, int team, String event, String choose1, String choose2) {
        String selectEL = selector.base + year + "/events/";
        if (teamB)
            selectEL = selectEL + "?teamNumber=" + team;
        else if (eventB)
            selectEL = selectEL + event;
        else {
            checkerX.check_chooseX(choose1);
            if (!(choose1.equals("0")))
                selectEL = selectEL + "?districtCode=" + choose1;
            else {
                checkerX.check_chooseX(choose2);
                if (choose2.equals("1"))
                    selectEL = selectEL + "?excludeDistrict=true";
            }
        }
        return selectEL;
    }

    String selectTL(boolean eventB, boolean teamB, int year, int team, String event, String choose1, String choose2) {
        String selectTL = selector.base + year + "/teams/";
        if (teamB)
            selectTL = selectTL + "?teamNumber=" + team;
        else if (eventB)
            selectTL = selectTL + "?eventCode=" + event;
        else {
            checkerX.check_chooseX(choose1);
            checkerX.check_chooseX(choose2);
            switch (choose1) {
                case "1":
                    selectTL = selectTL + "?districtCode=" + choose2;
                    break;
                case "2":
                    choose2 = choose2.replace(" ", "%20");
                    selectTL = selectTL + "?state=" + choose2;
                    break;
                default:
                    System.out.println("Incorrect Parameter: " + choose1);
                    System.exit(2);
            }
        }
        return selectTL;
    }

    String selectY(boolean teamB, int year, int team, String event, int choose0, String choose1, String choose2, String choose3, String choose4) {
        cli_selectAll selectEL = new cli_selectAll();
        String selectY = null;
        switch (choose0) {
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
                    boolean dsB = false;
                    checkerX.check_chooseX(choose1);
                    if (choose1.equals("0"))
                        selectY = selector.base + year + "/rankings/district";
                    else {
                        checkerX.check_chooseX(choose2);
                        selectY = selector.base + year + "/rankings/district/" + choose2;
                        dsB = true;
                    }
                    int select;
                    int toppage;
                    checkerX.check_chooseX(choose2);
                    checkerX.check_chooseX(choose3);
                    if (dsB) {
                        toppage = Integer.parseInt(choose3);
                        checkerX.check_chooseX(choose4);
                        select = Integer.parseInt(choose4);
                    } else {
                        toppage = Integer.parseInt(choose2);
                        select = Integer.parseInt(choose3);
                    }
                    switch (toppage) {
                        case 1:
                            selectY = selectY + "/?top=" + select;
                            break;
                        case 2:
                            selectY = selectY + "/?page=" + select;
                            break;
                        default:
                            System.out.println("Incorrect Parameter :" + toppage);
                            System.exit(2);
                    }
                }
                break;
            case 4:
                selectY = selectEL.selectEL(false, teamB, year, team, event, choose1, choose2);
                break;
            case 5:
                selectY = selector.base + year + "/districts";
                break;
            case 6:
                selectY = selectEL.selectTL(false, teamB, year, team, event, choose1, choose2);
                break;
            case 7:
                selectY  = selector.base +year+"/avatars?teamNumber="+team;
                break;
            default:
                System.out.println("Incorrect Parameter: "+ choose0);
                System.exit(2);
        }
        return selectY;
    }
}


