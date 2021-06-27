package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;


import java.util.ArrayList;
import java.util.List;

public class Player {

    private String username;
    private List<LeaderCard> leaderCard;

    private List<List<DevelopmentCardData>> productions;

    private int victoryPoints;


    private List<Shelf> shelves;
    private ResourceList chest;


    public void initShelves(List<Shelf> shelves){
        shelves.add(new Shelf(0,1,0, null));
        shelves.add(new Shelf(1,2,0, null));
        shelves.add(new Shelf(2,3,0, null));
    }

    public Player(String username){

        this.username = username;
        this.chest = new ResourceList();
        this.productions = new ArrayList<>();
        this.shelves = new ArrayList<>();
        this.leaderCard = new ArrayList<>();
        this.chest = new ResourceList();

        initShelves(this.shelves);

    }


    public void addToChest(ResourceList list){
        chest.addAll(list);
    }

    public void updateShelf(int position, int maxSize, int size, Marble.Color color){
        if(shelves.get(position)!= null){
            shelves.get(position).update(position, maxSize, size, color);
        } else {
            shelves.add(position, new Shelf(position, maxSize, size, color));
        }
    }

    public void buyCard(int position, DevelopmentCardData cardData){
        productions.get(position).add(cardData);
    }


    public String getUsername() {
        return username;
    }

    public List<LeaderCard> getLeaderCard() {
        return leaderCard;
    }

    public List<List<DevelopmentCardData>> getProductions() {
        return productions;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }


    public List<Shelf> getShelves() {
        return shelves;
    }

    public ResourceList getChest() {
        return chest;
    }
}
