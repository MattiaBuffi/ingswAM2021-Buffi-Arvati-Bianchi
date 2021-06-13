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

    /**
     * TODO: Write constructor JavaDoc
     */
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

    /**
     * This method return the list of marbles of the selected row or column by calling alternatively getRow or getColumn.
     * The indexes of row and column are:
     * - 0-3 for the columns starting from left
     * - 4-6 for the rows starting from the bottom
     * @param position int number between 0 and 6
     * @return the list of marble of the selected row/column
     */
    public List<Marble> get(int position){
        if(position<4){
            return getColumn(position);
        } else {
            return getRow(position-4);
        }
    }

    /**
     * Call alternatively insertInColumn or insertInRow depending on parameter
     * @param position int number between 0 and 6
     */
    public void insertExtra(int position){
        if(position<4){
            insertInColumn(position);
        } else {
            insertInRow(position-4);
        }

        broadcaster.notifyAllPlayers(new ResourceMarketUpdate(position, get(position)));
        broadcaster.notifyAllPlayers(new ResourceMarketExtra(bonusMarble));

    }

    /**
     * Method that return the list of the marble of the selected row.
     * @param position int number between 0 and 2
     * @return List of marble of the selected row
     */
    private List<Marble> getRow(int position){

        ArrayList<Marble> list = new ArrayList<>();

        for(int i = 0; i< ROW_SIZE; i++){
            list.add(marblesGrid[i][position]);
        }

        return list;
    }

    /**
     * Method that return the list of the marble of the selected column.
     * @param position int number between 0 and 3
     * @return List of marble of the selected column
     */
    private List<Marble> getColumn(int position){
        ArrayList<Marble> list = new ArrayList<>();

        for(int i = 0; i< COLUMN_SIZE; i++){
            list.add(marblesGrid[position][i]);
        }

        return list;
    }

    /**
     * Method that insert the bonus marble of the market in the right spot after the resources of a row are taken. The
     * method also replace the bonus marble with the last marble of the row selected.
     * @param position int number between 0 and 2
     */
    private void insertInRow(int position){
        Marble tmpBonusMarble = bonusMarble;

        bonusMarble = marblesGrid[ROW_SIZE -1][position];

        for(int i = 0; i<(ROW_SIZE - 1); i++){
            marblesGrid[i][position] = marblesGrid[i+1][position];
        }

        marblesGrid[3][position] = tmpBonusMarble;
    }

    /**
     * Method that insert the bonus marble of the market in the right spot after the resources of a column are taken. The
     * method also replace the bonus marble with the last marble of the column selected.
     * @param position int number between 0 and 3
     */
    private void insertInColumn(int position){
        Marble tmpBonusMarble = bonusMarble;

        bonusMarble = marblesGrid[position][COLUMN_SIZE -1];

        for(int i = 0; i<(COLUMN_SIZE - 1); i++){
            marblesGrid[position][i] = marblesGrid[position][i+1];
        }
        marblesGrid[position][COLUMN_SIZE -1] = tmpBonusMarble;
    }

    /**
     * Get the marbles grid of the market
     * @return Entire marbles grid of the market
     */
    public Marble[][] getMarblesGrid() {
        return marblesGrid;
    }

    /**
     * Get the bonus marble
     * @return Bonus marble
     */
    public Marble getBonusMarble() {
        return bonusMarble;
    }






}
