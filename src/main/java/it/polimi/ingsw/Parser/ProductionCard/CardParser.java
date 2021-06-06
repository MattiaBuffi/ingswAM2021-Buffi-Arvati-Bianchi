package it.polimi.ingsw.Parser.ProductionCard;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.CardMarket.CardMarket;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CardParser {

    private static final String path = "src/main/resources/cards/DevelopmentCard.json";
    private static final List<DevelopmentCardBuilder> DEVELOPMENT_CARD_BUILDERS = parseDevelopmentDeckBuilder();


    public static List<PurchasableCard> getMarketCards(CardMarket market){

        List<PurchasableCard> purchasableCards = new ArrayList<>();

        for (DevelopmentCardBuilder b: DEVELOPMENT_CARD_BUILDERS){
            purchasableCards.add(b.getCard(market));
        }

        return purchasableCards;

    }

    private static List<DevelopmentCardBuilder> parseDevelopmentDeckBuilder() {
        Gson parser = new Gson();
        BufferedReader reader = null;
        List<DevelopmentCardBuilder> cards = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(path));

            Type collectionType = new TypeToken<List<DevelopmentCardBuilder>>(){}.getType();
            List<DevelopmentCardBuilder> parsedCard = parser.fromJson(reader, collectionType);

            if (parsedCard == null) {
                //error
            }
            cards = parsedCard;
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