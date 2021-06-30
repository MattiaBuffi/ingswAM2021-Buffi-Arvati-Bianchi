package it.polimi.ingsw.Parser.LeaderCardParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CardParser {

    private static final String path = "src/main/resources/cards/LeaderCard.json";
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
        List<LeaderCardBuilder> cards = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(path));

            Type collectionType = new TypeToken<List<LeaderCardBuilder>>(){}.getType();
            List<LeaderCardBuilder> parsedCard = parser.fromJson(reader, collectionType);

            if (parsedCard == null) {
                //error
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
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