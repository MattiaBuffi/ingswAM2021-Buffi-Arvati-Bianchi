package it.polimi.ingsw.Parser.LeaderCardParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Parser.ProductionCard.DevelopmentCardBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CardParser {

    private static final String path = "src/main/resources/cards/LeaderCard.json";
    private static final List<LeaderCard> LEADER_CARDS = parseLeaderDeckBuilder();

    public static List<LeaderCard> getLeaderCards(){
        return new ArrayList<>(LEADER_CARDS);
    }

    private static List<LeaderCard> parseLeaderDeckBuilder() {
        Gson parser = new Gson();
        BufferedReader reader = null;
        List<LeaderCard> cards = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(path));

            Type collectionType = new TypeToken<List<LeaderCardBuilder>>(){}.getType();
            List<LeaderCardBuilder> parsedCard = parser.fromJson(reader, collectionType);

            if (parsedCard == null) {
                //error
            }

            for (LeaderCardBuilder cardBuilder: parsedCard){
                cards.add(cardBuilder.getCard());
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