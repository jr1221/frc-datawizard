
public class CliSelector {

    public static boolean gui = false;

    String urlselect(boolean teamB, boolean eventB, int year, String event, int team, int choose0, String choose1, String choose2, String choose3, String choose4, boolean gui2, String base4) {
        gui = gui2;
        cli_selectAll selectAll1 = new cli_selectAll();
        String urlselect1;
        urlselect1 = selectAll1.selectAC(teamB, eventB, year, event, team, choose0, choose1, choose2, choose3, choose4, base4);
        return urlselect1;
    }

    static boolean check_chooseX(String choosen) {
        if (choosen.equals("No Parameter Selected") || choosen.equals("") || choosen.equals(" ")) {
            if (gui) {
                MessageDialog.main("You need more parameters!");
            } else {
                System.out.println("You need more parameters!");
            }
            return true;
        }
        return false;
    }

    static boolean error(String err) {
        if (gui) {
            MessageDialog.main(err);
        } else {
            System.out.println(err);
        }
        return true;
    }
}

class cli_selectAll {

    String selectAC(boolean teamB, boolean eventB, int year, String event, int team, int choose0, String choose1, String choose2, String choose3, String choose4, String base4) {
        boolean modeBase = true;
        boolean check;
        check = false;
        if (base4.equals(Main.FRC_BASE)) {
            modeBase = false;
        }
        String selectAC;
        if (eventB) {
            switch (choose0) {
                case 3:
                    selectAC = base4 + year + "/alliances/" + event;
                    return selectAC;
                case 4:
                    selectAC = base4 + year + "/matches/" + event + "/";
                    selectAC = selectMES(selectAC, choose0, choose1, choose2, choose3, choose4, team, teamB);
                    return selectAC;
                case 5:
                    selectAC = base4 + year + "/scores/" + event + "/";
                    selectAC = selectMES(selectAC, choose0, choose1, choose2, choose3, choose4, team, teamB);
                    return selectAC;
                case 6:
                    selectAC = base4 + year + "/schedule/" + event + "/";
                    selectAC = selectMES(selectAC, choose0, choose1, choose2, choose3, choose4, team, teamB);
                    return selectAC;
                case 7:
                    selectAC = base4 + year + "/rankings/" + event + "/";
                    if (teamB) {
                        selectAC = selectAC + "?teamNumber=" + team;
                        return selectAC;
                    } else {
                        check = CliSelector.check_chooseX(choose1);
                        if (check) {
                            return null;
                        }
                        if (!choose1.equals("0")) {
                            selectAC = selectAC + "?top=" + choose1;
                        }
                    }
                    return selectAC;
            }
        }
        if (!modeBase) {
            switch (choose0) {
                case 8:
                    selectAC = base4 + year + "/events/";
                    if (teamB) {
                        selectAC = selectAC + "?teamNumber=" + team;
                        return selectAC;
                    }
                    if (eventB) {
                        selectAC = selectAC + event;
                        return selectAC;
                    }
                    check = CliSelector.check_chooseX(choose1);
                    if (check) {
                        return null;
                    }
                    if (!(choose1.equals("0"))) {
                        selectAC = selectAC + "?districtCode=" + choose1;
                    } else {
                        check = CliSelector.check_chooseX(choose2);
                        if (check) {
                            return null;
                        }
                        if (choose2.equals("1")) {
                            selectAC = selectAC + "?excludeDistrict=true";
                        }
                    }
                    return selectAC;
            }
        }
        switch (choose0) {
            case 9:
                selectAC = base4 + year + "/teams/";
                if (teamB) {
                    selectAC = selectAC + "?teamNumber=" + team;
                    return selectAC;
                }
                if (eventB) {
                    selectAC = selectAC + "?eventCode=" + event;
                    return selectAC;
                }
                check = CliSelector.check_chooseX(choose1);
                if (check) {
                    return null;
                }
                check = CliSelector.check_chooseX(choose2);
                if (check) {
                    return null;
                }
                switch (choose1) {
                    case "1":
                        selectAC = selectAC + "?districtCode=" + choose2;
                        break;
                    case "2":
                        choose2 = choose2.replace("_", "%20");
                        selectAC = selectAC + "?state=" + choose2;
                        break;
                    default:
                        check = CliSelector.error("Incorrect Parameter: " + choose1);
                }
                if (check) {
                    return null;
                }
                return selectAC;
            case 10:
                selectAC = base4 + year;
                return selectAC;
            case 11:
                if (teamB) {
                    selectAC = base4 + year + "/awards/" + team;
                    return selectAC;
                }
                if (eventB) {
                    selectAC = base4 + year + "/awards/" + event;
                    return selectAC;
                }
                selectAC = base4 + year + "/awards/list";
                return selectAC;
            case 12:
                if (teamB) {
                    selectAC = base4 + year + "/rankings/district/?teamNumber=" + team;
                    return selectAC;
                }
                check = CliSelector.check_chooseX(choose1);
                if (check) {
                    return null;
                }
                if (choose1.equals("0")) {
                    selectAC = base4 + year + "/rankings/district";
                } else {
                    selectAC = base4 + year + "/rankings/district/" + choose1;
                }
                switch (choose2) {
                    case "1":
                        selectAC = selectAC + "/?top=" + choose3;
                        break;
                    case "2":
                        selectAC = selectAC + "/?page=" + choose3;
                        break;
                    default:
                        check = CliSelector.error("Incorrect Parameter: " + choose2);
                }
                if (check) {
                    return null;
                }
                return selectAC;
            case 13:
                selectAC = base4 + year + "/districts";
                return selectAC;
        }
        if (teamB && choose0 == 14 && !modeBase) {
            selectAC = base4 + year + "/avatars?teamNumber=" + team;
            return selectAC;
        }
        CliSelector.error("Invalid Parameters, you need at least one parameter, or your parameters do not match your inputted info.");
        return null;
    }

    String selectMES(String selectBEG, int choose0, String choose1, String choose2, String choose3, String choose4, int team, boolean teamB) {
        String selectMES = selectBEG;
        String qualP = null;
        boolean check = CliSelector.check_chooseX(choose1);
        if (check) {
            return null;
        }
        switch (choose1) {
            case "1":
                qualP = "qual";
                break;
            case "2":
                qualP = "playoff";
                break;
            default:
                check = CliSelector.error("Incorrect Parameter: " + choose1);
        }
        if (check) {
            return null;
        }
        if (teamB) {
            selectMES = selectMES + qualP + "?teamNumber=" + team;
            return selectMES;
        }
        selectMES = selectMES + qualP;
        boolean maB = false;
        check = CliSelector.check_chooseX(choose2);
        if (check) {
            return null;
        }
        if (choose0 == 6) {
            maB = true;
            if (choose2.equals("1")) {
                selectMES = selectMES + "/hybrid/";
            }

        }
        if (!maB && !choose2.equals("0")) {
            selectMES = selectMES + "?matchNumber=" + choose2;
            return selectMES;
        }
        check = CliSelector.check_chooseX(choose3);
        if (check) {
            return null;
        }
        if (!choose3.equals("0")) {
            selectMES = selectMES + "?start=" + choose3;
            return selectMES;
        }
        check = CliSelector.check_chooseX(choose4);
        if (check) {
            return null;
        }
        selectMES = selectMES + "?end=" + choose4;
        return selectMES;
    }
}
