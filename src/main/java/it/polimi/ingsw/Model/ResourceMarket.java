package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.MarbleFactory;

import java.util.ArrayList;
import java.util.Collections;

public class ResourceMarket {
    public static final int MARKET_SIZE = 13;
    public static final int MARKET_ROWS = 3;
    public static final int MARKET_COLUMN = 4;
    public static final MarbleColor[] MARBLES_COLORS = {MarbleColor.RED, MarbleColor.WHITE, MarbleColor.WHITE, MarbleColor.WHITE, MarbleColor.WHITE,
            MarbleColor.BLUE, MarbleColor.BLUE, MarbleColor.YELLOW, MarbleColor.YELLOW,
            MarbleColor.GREY, MarbleColor.GREY, MarbleColor.PURPLE, MarbleColor.PURPLE};

    private Marble[][] marblesGrid = new Marble[MARKET_ROWS][MARKET_COLUMN];
    private Marble bonusMarble;


    public ResourceMarket(){
        ArrayList<Marble> tmpList = new ArrayList<>();

        for (int i=0; i<MARKET_SIZE; i++){
            tmpList.add(MarbleFactory.getMarble(MARBLES_COLORS[i]));
        }

        Collections.shuffle(tmpList);

        for(int i = 0; i < MARKET_ROWS; i++){
            for(int j = 0; j < MARKET_COLUMN; j++){
                marblesGrid[i][j] = tmpList.get((i * 4) + j);
            }
        }
        bonusMarble = tmpList.get(12);
    }

    public ArrayList<Marble> getRow(int position){
        ArrayList<Marble> list = new ArrayList<>();
        for(int i=0;i<MARKET_COLUMN;i++){
            list.add(marblesGrid[position][i]);
        }

        InsertInRow(position);

        return list;
    }

    private void InsertInRow(int position){
        Marble tmpBonusMarble = bonusMarble;

        bonusMarble = marblesGrid[position][0];
        for(int i=0;i<(MARKET_COLUMN - 1);i++){
            marblesGrid[position][i] = marblesGrid[position][i+1];
        }
        marblesGrid[position][3] = tmpBonusMarble;
    }


    public ArrayList<Marble> getColumn(int position){
        ArrayList<Marble> list = new ArrayList<>();

        for(int i=0;i<3;i++){
            list.add(marblesGrid[i][position]);
        }

        InsertInColumn(position);

        return list;
    }

    private void InsertInColumn(int position){
        Marble tmpBonusMarble = bonusMarble;

        bonusMarble = marblesGrid[0][position];
        for(int i=0;i<(MARKET_ROWS - 1);i++){
            marblesGrid[i][position] = marblesGrid[i+1][position];
        }
        marblesGrid[2][position] = tmpBonusMarble;
    }

    public Marble[][] getMarblesGrid() {
        return marblesGrid;
    }

    public Marble getBonusMarble() {
        return bonusMarble;
    }
}
