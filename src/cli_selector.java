

public class cli_selector {
    String urlselect(boolean teamB, boolean eventB, int year, String event, int team, int choose0, String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
        cli_selectAll selectAll1 = new cli_selectAll();
        String urlselect1;
        if (eventB) {
            urlselect1 = selectAll1.selectYE(eventB, teamB, year, event, team, choose0, choose1,choose2,choose3, choose4, choose5, choose6);
        } else
            urlselect1 = selectAll1.selectY(eventB, teamB, year, team, event, choose0, choose1, choose2, choose3, choose4);
        return urlselect1;
    }
}


class cli_selectAll {
    String selectYE(boolean eventB, boolean teamB, int year, String event, int team, int choose0, String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
        cli_selectAll selectorMA = new cli_selectAll();
        String selectYE = null;
        if (choose0 == 1)
            selectYE = selector.base + year + "/alliances/" + event;
        else if (choose0 == 2) {
            selectYE = selector.base + year + "/awards/" + event;
            if (teamB)
                selectYE = selectYE + "/" + team;
        } else if (choose0 == 3) {
            selectYE = selectorMA.selectMa(teamB, year, event, team,  choose1, choose2,  choose3, choose4, choose5, choose6);
        } else if (choose0 == 5) {
            selectYE = selectorMA.selectEL(eventB, teamB, year, team, event, choose1, choose2);
        } else if (choose0 == 6) {
            selectYE = selectorMA.selectTL(eventB, teamB, year, team, event, choose1, choose2);
        } else if (choose0 == 4) {
            selectYE = selector.base + year + "/rankings/" + event + "/";
            if (teamB)
                selectYE = selectYE + "?teamNumber=" + team;
            else {
                if (!choose1.equals("0"))
                    selectYE = selectYE + "?top=" + choose1;
            }

        }
        return selectYE;
    }





    String selectMa(boolean teamB, int year, String event, int team, String choose1, String choose2, String choose3, String choose4, String choose5, String choose6) {
        String qualP = null;
        String selectMa = null;
        int selectMaMd;
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
        }
        selectMaMd = Integer.parseInt(choose1);
        if (choose2.equals("1"))
            qualP = "qual";
        else if (choose2.equals("2"))
            qualP = "playoff";
        if (teamB) {
            if (selectMaMd==1)
                selectMa = selectMa + "?teamNumber="+team;
            else
                selectMa = selectMa+qualP+"?teamNumber="+team;
        }
        else {
            selectMa = selectMa + qualP;
            boolean maB = false;
            int answer1;
            int answer2;
            int answer3;
            if (selectMaMd==3) {
                maB = true;
                selectMaMd = Integer.parseInt(choose3);
                if (selectMaMd == 1)
                    selectMa = selectMa + "/hybrid";
            }
            if (maB) {
                answer1 = Integer.parseInt(choose4);
                answer2 = Integer.parseInt(choose5);
                answer3 = Integer.parseInt(choose6);
            }
            else {
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
        String selectEL = selector.base +year+"/events/";
        if (teamB)
            selectEL= selectEL+"?teamNumber="+team;
        else if (eventB)
            selectEL=selectEL+event;
        else {
            if (!(choose1.equals("0")))
                selectEL = selectEL+"?districtCode="+ choose1;
            else {
                if (choose2.equals("1"))
                    selectEL=selectEL+"?excludeDistrict=true";
            }
        }
        return selectEL;
    }
    String selectTL(boolean eventB, boolean teamB, int year, int team, String event, String choose1, String choose2) {
        String selectTL = selector.base +year+"/teams/";
        if (teamB)
            selectTL= selectTL+"?teamNumber="+team;
        else if (eventB)
            selectTL=selectTL+"?eventCode="+event;
        else {
            if (choose1.equals("1")) {
                selectTL = selectTL + "?districtCode=" + choose2;
            }
            else if (choose1.equals("2")) {
                choose2= choose2.replace(" ","%20");
                selectTL=selectTL+"?state="+choose2;
            }
        }
        return selectTL;
    }

    String selectY(boolean eventB, boolean teamB, int year, int team, String event, int choose0, String choose1, String choose2, String choose3, String choose4) {
        cli_selectAll selectEL = new cli_selectAll();
        String selectY = null;
        if (choose0 == 1)
            selectY = selector.base + year;
        else if (choose0 == 2) {
            if (teamB)
                selectY = selector.base + year + "/awards/" + team;
            else
                selectY = selector.base + year + "/awards/list";
        } else if (choose0 == 4)
            selectY = selectEL.selectEL(eventB,  teamB,  year,  team, event, choose1, choose2);
        else if (choose0 == 6)
            selectY = selectEL.selectTL(eventB,  teamB,  year,  team, event, choose1, choose2);
        else if (choose0 ==5)
            selectY = selector.base +year+"/districts";
        else if (choose0 == 3) {
            if (teamB)
                selectY = selector.base + year + "/rankings/district/?teamNumber=" + team;
            else {
                boolean dsB = false;
                if (choose1.equals("0"))
                    selectY = selector.base + year + "/rankings/district";
                else {
                    selectY = selector.base + year + "/rankings/district/" + choose2;
                    dsB=true;
                }
                int select;
                int toppage;
                if (dsB) {
                    toppage = Integer.parseInt(choose3);
                    select = Integer.parseInt(choose4);
                }
                 else {
                    toppage = Integer.parseInt(choose2);
                    select = Integer.parseInt(choose3);
                }
                if (toppage == 1)
                    selectY = selectY + "/?top=" + select;
                else if (toppage == 2)
                    selectY = selectY + "/?page=" + select;
            }
        }
        return selectY;
    }
}


