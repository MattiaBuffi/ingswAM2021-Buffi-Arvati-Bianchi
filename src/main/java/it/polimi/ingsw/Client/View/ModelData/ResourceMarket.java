package it.polimi.ingsw.Client.View.ModelData;

import it.polimi.ingsw.Model.Marble.Marble;

import java.util.ArrayList;
import java.util.List;

public class ResourceMarket {


    private static final int COLUMN_SIZE = 3;
    private static final int ROW_SIZE = 4;

    private Marble[][] marblesGrid;
    private Marble bonusMarble;


    public ResourceMarket(){
        this.marblesGrid = new Marble[ROW_SIZE][COLUMN_SIZE];
    }



    public List<Marble> get(int position){
        if(position<4){
            return getRow(position);
        } else {
            return getColumn(position-4);
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
    }
}
