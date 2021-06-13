package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Model.Marble.Marble;

import java.util.ArrayList;
import java.util.List;

public class ResourceMarket {


    private static final int COLUMN_SIZE = 3;
    private static final int ROW_SIZE = 4;

    private Marble[][] marblesGrid;
    private Marble bonusMarble;

    private boolean changed;


    public ResourceMarket(){
        this.marblesGrid = new Marble[ROW_SIZE][COLUMN_SIZE];
    }



    public List<Marble> get(int position){
        if(position<4){
            return getColumn(position);
        } else {
            return getRow(position-4);
        }
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





    public void update(int position, List<Marble> marbles){
        if(position<4){
            insertInColumn(position, marbles);
        } else {
            insertInRow(position-4, marbles);
        }
        changed = true;
    }


    private void insertInRow(int position, List<Marble> marbles){

        for(int i = 0; i< ROW_SIZE ; i++){
            marblesGrid[i][position] = marbles.get(i);
        }


    }


    private void insertInColumn(int position, List<Marble> marbles){


        for(int i = 0; i< COLUMN_SIZE; i++){
            marblesGrid[position][i] = marbles.get(i);
        }

    }


    public Marble getBonusMarble() {
        return bonusMarble;
    }

    public void setBonusMarble(Marble bonusMarble) {
        this.bonusMarble = bonusMarble;
        changed = true;
    }

}
