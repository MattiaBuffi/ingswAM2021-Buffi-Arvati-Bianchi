package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.CardStorage.Selection.SelectBasic;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.TestData.TestBroadcaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class PlayerCardStorageTest {



    private PlayerCardStorage setupPlayerStorage(){
        return new PlayerCardStorage(new TestBroadcaster());
    }

    @Test
    void initialization() {
        PlayerCardStorage storage = new PlayerCardStorage(new TestBroadcaster());
        assertEquals( 0, storage.getCards().size());
    }

    @Test
    void basicProductionCorrect(){

        PlayerCardStorage storage = setupPlayerStorage();
        ProductionSelector selector = new SelectBasic(Marble.Color.BLUE, Marble.Color.BLUE, Marble.Color.GREY);

        ProductionCard card = storage.getCard(selector);

        assertEquals("basic", card.getId());

    }

    @Test
    void basicProductionIncorrectInput(){

        PlayerCardStorage storage = setupPlayerStorage();
        ProductionSelector selector = new SelectBasic(Marble.Color.BLUE, Marble.Color.RED, Marble.Color.GREY);

        ProductionCard card = storage.getCard(selector);

        assertNull(card);

    }

    @Test
    void basicProductionIncorrectOutput(){

        PlayerCardStorage storage = setupPlayerStorage();
        ProductionSelector selector = new SelectBasic(Marble.Color.BLUE, Marble.Color.BLUE, Marble.Color.RED);

        ProductionCard card = storage.getCard(selector);

        assertNull(card);

    }








}