package it.polimi.ingsw.Parser.LeaderCardParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CardParser {

    private static final String path = "/cards/LeaderCard.json";
    private static final List<LeaderCardBuilder> LEADER_CARD_BUILDERS = parseLeaderDeckBuilder();

    public static List<LeaderCard> getLeaderCard(){

        List<LeaderCard> leaderCards =new ArrayList<>();

        for (LeaderCardBuilder cardBuilder: LEADER_CARD_BUILDERS){
            leaderCards.add(cardBuilder.getCard());
        }
        return leaderCards;
    }

    private static List<LeaderCardBuilder> parseLeaderDeckBuilder() {
        Gson parser = new Gson();
        BufferedReader reader = null;
        List<LeaderCardBuilder> cards;

        try {
            reader = new BufferedReader(new InputStreamReader(it.polimi.ingsw.Parser.LeaderCardParser.CardParser.class.getResourceAsStream(path), StandardCharsets.UTF_8));

            Type collectionType = new TypeToken<List<LeaderCardBuilder>>(){}.getType();

            cards = parser.fromJson(reader, collectionType);

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return cards;
    }
}