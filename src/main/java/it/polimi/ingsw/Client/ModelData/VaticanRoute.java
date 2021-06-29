package it.polimi.ingsw.Client.ModelData;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VaticanRoute {

    public Map<String, Integer> tokenPosition;
    public Map<String, Set<Integer>> vaticanReport;


    public VaticanRoute() {
        tokenPosition = new HashMap<>();
        vaticanReport = new HashMap<>();
    }


    public int getPlayerFaithPoint(String username){
        if(tokenPosition.containsKey(username)){
            return tokenPosition.get(username);
        }
        return 0;
    }

    public Set<Integer> getVaticanReports(String username){
        if(vaticanReport.containsKey(username)){
            return vaticanReport.get(username);
        }
        return Collections.EMPTY_SET;
    }




}
