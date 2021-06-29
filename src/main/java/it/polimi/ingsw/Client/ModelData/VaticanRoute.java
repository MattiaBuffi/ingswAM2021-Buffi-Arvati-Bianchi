package it.polimi.ingsw.Client.ModelData;

import java.util.HashMap;
import java.util.Map;

public class VaticanRoute {

    public Map<String, Integer> tokenPosition;

    public VaticanRoute() {
        tokenPosition = new HashMap<>();
    }

    public int getPlayerFaithPoint(String username){
        if(tokenPosition.containsKey(username)){
            return tokenPosition.get(username);
        }
        return 0;
    }




}
