package it.polimi.ingsw.Parser.ProductionCard;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.CardMarket.CardMarket;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;


import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CardParser {

    private static final String path = "/cards/DevelopmentCard.json";
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
        List<DevelopmentCardBuilder> cards;

        try {
            reader = new BufferedReader(new InputStreamReader(it.polimi.ingsw.Parser.ProductionCard.CardParser.class.getResourceAsStream(path), StandardCharsets.UTF_8));

            Type collectionType = new TypeToken<List<DevelopmentCardBuilder>>(){}.getType();
            List<DevelopmentCardBuilder> parsedCard = parser.fromJson(reader, collectionType);

            cards = parsedCard;
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