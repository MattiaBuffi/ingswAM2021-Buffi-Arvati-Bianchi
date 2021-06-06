package it.polimi.ingsw.Model.ResourceMarket;

import it.polimi.ingsw.Message.Model.ResourceMarketExtra;
import it.polimi.ingsw.Message.Model.ResourceMarketUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResourceMarket {


    public static final int MARKET_SIZE = 13;
    public static final int COLUMN_SIZE = 3;
    public static final int ROW_SIZE = 4;


    public static final Marble.Color[] MARBLES_COLORS = {Marble.Color.RED, Marble.Color.WHITE, Marble.Color.WHITE, Marble.Color.WHITE, Marble.Color.WHITE,
            Marble.Color.BLUE, Marble.Color.BLUE, Marble.Color.YELLOW, Marble.Color.YELLOW,
            Marble.Color.GREY, Marble.Color.GREY, Marble.Color.PURPLE, Marble.Color.PURPLE};


    //inverting start
    private Marble[][] marblesGrid = new Marble[ROW_SIZE][COLUMN_SIZE];
    private Marble bonusMarble;

    private final EventBroadcaster broadcaster;

    public ResourceMarket(EventBroadcaster broadcaster){

        this.broadcaster = broadcaster;


        ArrayList<Marble> tmpList = new ArrayList<>();

        for (int i=0; i<MARKET_SIZE; i++){
            tmpList.add(MarbleFactory.getMarble(MARBLES_COLORS[i]));
        }

        Collections.shuffle(tmpList);

        for(int i = 0; i < COLUMN_SIZE; i++){
            for(int j = 0; j < ROW_SIZE; j++){
                marblesGrid[j][i] = tmpList.get((i * 4) + j);
            }
        }
        bonusMarble = tmpList.get(12);


        broadcaster.notifyAllPlayers(new ResourceMarketUpdate(4, get(4)));
        broadcaster.notifyAllPlayers(new ResourceMarketUpdate(5, get(5)));
        broadcaster.notifyAllPlayers(new ResourceMarketUpdate(6, get(6)));
        broadcaster.notifyAllPlayers(new ResourceMarketExtra(bonusMarble));

    }


    public List<Marble> get(int position){
        if(position<4){
            return getRow(position);
        } else {
            return getColumn(position-4);
        }
    }

    public void insertExtra(int position){
        if(position<4){
            insertInColumn(position);
        } else {
            insertInRow(position-4);
        }

        broadcaster.notifyAllPlayers(new ResourceMarketUpdate(position, get(position)));
        broadcaster.notifyAllPlayers(new ResourceMarketExtra(bonusMarble));

    }


    private List<Marble> getRow(int position){

        ArrayList<Marble> list = new ArrayList<>();

        for(int i = 0; i< ROW_SIZE; i++){
            list.add(marblesGrid[i][position]);
        }

        return list;
    }


    private List<Marble> getColumn(int position){
        ArrayList<Marble> list = new ArrayList<>();

        for(int i = 0; i< COLUMN_SIZE; i++){
            list.add(marblesGrid[position][i]);
        }

        return list;
    }


    private void insertInRow(int position){
        Marble tmpBonusMarble = bonusMarble;

        bonusMarble = marblesGrid[ROW_SIZE -1][position];

        for(int i = 0; i<(ROW_SIZE - 1); i++){
            marblesGrid[i][position] = marblesGrid[i+1][position];
        }

        marblesGrid[3][position] = tmpBonusMarble;
    }


    private void insertInColumn(int position){
        Marble tmpBonusMarble = bonusMarble;

        bonusMarble = marblesGrid[position][COLUMN_SIZE -1];

        for(int i = 0; i<(COLUMN_SIZE - 1); i++){
            marblesGrid[position][i] = marblesGrid[position][i+1];
        }
        marblesGrid[position][COLUMN_SIZE -1] = tmpBonusMarble;
    }


    public Marble[][] getMarblesGrid() {
        return marblesGrid;
    }

    public Marble getBonusMarble() {
        return bonusMarble;
    }






}
