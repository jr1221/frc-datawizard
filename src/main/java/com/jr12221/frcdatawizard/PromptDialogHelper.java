package com.jr12221.frcdatawizard;

import java.awt.Point;

public class PromptDialogHelper {

    PromptDialog abc;
    Point previousHere = new Point(0, 0);
    String quickCodeCon = "";

    public String guiURLGet(String base, boolean modeBase, int year, String event, int team, boolean eventB, boolean teamB) {
        String selectA;
        String messageOut;
        int select;
        String selectStr;
        String response;
        messageOut = "Options;\n";
        if (eventB) {
            messageOut
                    += "\n (3) Alliance selections in event "
                    + "\n (4) Match Data"
                    + "\n (5) Score Results"
                    + "\n (6) Match Schedule"
                    + "\n (7) Rankings in event ";
        }
        if (!modeBase) {
            messageOut += "\n (8) Event listings";
        }
        messageOut
                += "\n (9) Team listings"
                + "\n (10) Year info"
                + "\n (11) Awards info for that year"
                + "\n (12) District rankings"
                + "\n (13) District listings ";
        if (teamB && !modeBase) {
            messageOut += "\n (14) Team Avatar (Display)";
        }
        response = sendDialog(messageOut);

        if (response == null) {
            return null;
        } else {
            select = Integer.parseInt(response);
        }
        if (eventB) {
            switch (select) {
                case 3 -> {
                    selectA = base + year + "/alliances/" + event;
                    return selectA;
                }
                case 4 -> {
                    selectA = base + year + "/matches/" + event + "/";
                    selectA = selectMa(teamB, team, selectA);
                    return selectA;
                }
                case 5 -> {
                    selectA = base + year + "/scores/" + event + "/";
                    selectA = selectMa(teamB, team, selectA);
                    return selectA;
                }
                case 6 -> {
                    selectA = base + year + "/schedule/" + event + "/";
                    selectA = selectMa(teamB, team, selectA);
                    return selectA;
                }
                case 7 -> {
                    selectA = base + year + "/rankings/" + event + "/";
                    if (teamB) {
                        selectA = selectA + "?teamNumber=" + team;
                        return selectA;
                    }
                    messageOut = "Options;\n (0) All rankings \n (<x>) Top x Rankings";
                    response = sendDialog(messageOut);
                    if (response == null) {
                        return null;
                    } else {
                        select = Integer.parseInt(response);
                    }
                    if (select != 0) {
                        selectA = selectA + "?top=" + select;
                    }
                    return selectA;
                }
            }
        }
        if (!modeBase) {
            switch (select) {
                case 8 -> {
                    selectA = base + year + "/events/";
                    if (teamB) {
                        selectA = selectA + "?teamNumber=" + team;
                        return selectA;
                    }
                    if (eventB) {
                        selectA = selectA + event;
                        return selectA;
                    }
                    messageOut = "Options;\n (0) DO not filter by district\n (<x>) Enter district code to filter by (ex. NE)";
                    response = sendDialog(messageOut);
                    if (response == null) {
                        return null;
                    } else {
                        selectStr = response;
                    }
                    if (!(selectStr.equals("0"))) {
                        selectA = selectA + "?districtCode=" + selectStr;
                    } else {
                        messageOut = "Would you like to exclude district events and championships? Yes (1) No (2)";
                        response = sendDialog(messageOut);
                        if (response == null) {
                            return null;
                        } else {
                            select = Integer.parseInt(response);
                        }
                        if (select == 1) {
                            selectA = selectA + "?excludeDistrict=true";
                        }
                    }
                    return selectA;
                }
            }
        }
        switch (select) {
            case 9 -> {
                selectA = base + year + "/teams/";
                if (teamB) {
                    selectA = selectA + "?teamNumber=" + team;
                    return selectA;
                }
                if (eventB) {
                    selectA = selectA + "?eventCode=" + event;
                    return selectA;
                }
                messageOut = "Options;\n (1) Filter by district code\n (2) Filter by state";
                response = sendDialog(messageOut);
                if (response == null) {
                    return null;
                } else {
                    select = Integer.parseInt(response);
                }
                switch (select) {
                    case 1 -> {
                        messageOut = "(<x>) District code x to filter by";
                        response = sendDialog(messageOut);
                        if (response == null) {
                            return null;
                        } else {
                            selectStr = response;
                        }
                        selectA = selectA + "?districtCode=" + selectStr;
                    }
                    case 2 -> {
                        messageOut = "(<x>) State x to filter by (!!!Use an underscore for a space)";
                        response = sendDialog(messageOut);
                        if (response == null) {
                            return null;
                        } else {
                            selectStr = response;
                        }
                        selectStr = selectStr.replace("_", "%20");
                        selectA = selectA + "?state=" + selectStr;
                    }
                    default -> {
                        System.out.println("Incorrect Parameter: " + select);
                        System.exit(2);
                    }
                }
                return selectA;
            }
            case 10 -> {
                selectA = base + year;
                return selectA;
            }
            case 11 -> {
                if (teamB) {
                    selectA = base + year + "/awards/" + team;
                    return selectA;
                }
                if (eventB) {
                    selectA = base + year + "/awards/" + event;
                    return selectA;
                }
                selectA = base + year + "/awards/list";
                return selectA;
            }
            case 12 -> {
                if (teamB) {
                    selectA = base + year + "/rankings/district/?teamNumber=" + team;
                    return selectA;
                }
                messageOut = "Options;\n (0) Print all district info\n (1) Print info by district code";
                response = sendDialog(messageOut);
                if (response == null) {
                    return null;
                } else {
                    select = Integer.parseInt(response);
                }
                if (select == 0) {
                    selectA = base + year + "/rankings/district";
                } else {
                    messageOut = "(<x>) District code x to show rankings";
                    response = sendDialog(messageOut);
                    if (response == null) {
                        return null;
                    } else {
                        selectStr = response;
                    }
                    selectA = base + year + "/rankings/district/" + selectStr;
                }
                messageOut = "Options;\n (1) Top rankings\n (2) Page numbers";
                response = sendDialog(messageOut);
                if (response == null) {
                    return null;
                } else {
                    select = Integer.parseInt(response);
                }
                int toppage = select;
                messageOut = "(<x>) x top rankings or page numbers to limit results to";
                response = sendDialog(messageOut);
                if (response == null) {
                    return null;
                } else {
                    select = Integer.parseInt(response);
                }
                if (toppage == 1) {
                    selectA = selectA + "/?top=" + select;
                } else if (toppage == 2) {
                    selectA = selectA + "/?page=" + select;
                }
                return selectA;
            }
            case 13 -> {
                selectA = base + year + "/districts";
                return selectA;
            }
        }
        if (teamB && select == 14 && !modeBase) {
            selectA = base + year + "/avatars?teamNumber=" + team;
            return selectA;
        }
        System.out.println("Invalid Parameters");
        return null;
    }

    String selectMa(boolean teamB, int team, String selectMa) {
        String qualP = null;
        String messageOut;
        int selectMaMd;
        int select;
        String response;
        messageOut = "Options;\n (1) Qualification matches\n (2) Playoff matches";
        response = sendDialog(messageOut);
        if (response == null) {
            return null;
        } else {
            select = Integer.parseInt(response);
        }
        selectMaMd = select;
        switch (select) {
            case 1 ->
                qualP = "qual";
            case 2 ->
                qualP = "playoff";
            default -> {
                System.out.println("Incorrect Parameter: " + select);
                System.exit(2);
            }
        }
        if (teamB) {
            selectMa = selectMa + qualP + "?teamNumber=" + team;
            return selectMa;
        }
        selectMa = selectMa + qualP;
        if (selectMaMd == 6) {
            messageOut = "Would you like to enter hybrid event schedule mode? \n (1) Yes \n (2) No";
            response = sendDialog(messageOut);
            if (response == null) {
                return null;
            } else {
                select = Integer.parseInt(response);
            }
            if (select == 1) {
                selectMa = selectMa + "/hybrid";
            }
        }
        if (!(selectMaMd == 6)) {
            messageOut = "Options;\n (0) Specify search matches to start or end number \n (<x>) Match number x";
            response = sendDialog(messageOut);
            if (response == null) {
                return null;
            } else {
                select = Integer.parseInt(response);
            }
            if (select != 0) {
                selectMa = selectMa + "?matchNumber=" + select;
                return selectMa;
            }
        }
        messageOut = "Options;\n (0) Enter an end parameter for match search. \n (<x>) Start at x in match search";
        response = sendDialog(messageOut);
        if (response == null) {
            return null;
        } else {
            select = Integer.parseInt(response);
        }
        if (select != 0) {
            selectMa = selectMa + "?start=" + select;
        } else {
            messageOut = "(<x>) End at x in match search.";
            response = sendDialog(messageOut);
            if (response == null) {
                return null;
            } else {
                select = Integer.parseInt(response);
            }
            selectMa = selectMa + "?end=" + select;
        }
        return selectMa;
    }

    private String sendDialog(String message) {
        abc = new PromptDialog(new javax.swing.JFrame(), true, message);
        abc.setLocation(previousHere);
        abc.setVisible(true);
        previousHere = abc.previousLoc;
        String answer = abc.answerField.getText();
        if (answer.equals("done")) {
            return null;
        } else {
            quickCodeCon += answer + "+";
            return answer;
        }
    }
}
