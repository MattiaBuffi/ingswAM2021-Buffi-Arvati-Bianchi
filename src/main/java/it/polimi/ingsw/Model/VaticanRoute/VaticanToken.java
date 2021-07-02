package it.polimi.ingsw.Model.VaticanRoute;

/**
 * Represent a token of the vatican route
 */
public class VaticanToken implements FaithHandler{

    private String owner;
    private int position;
    private int victoryPoint;

    private int lastPointPosition;

    private final VaticanRoute route;


    public VaticanToken(VaticanRoute route, String owner){
        this.position = 0;
        this.victoryPoint = 0;
        this.route = route;
        this.owner = owner;
    }

    public VaticanToken(VaticanRoute route, int position, String owner){
        this.position = position;
        this.victoryPoint = 0;
        this.lastPointPosition = 0;
        this.route = route;
        this.owner = owner;
    }


    public String getOwner() {
        return owner;
    }


    @Override
    public void advance(int amount) {
        this.route.advance(this, amount);
    }



    @Override
    public void give(int amount) {

        for (VaticanToken t: route.getTokenList()){
            if(!(t == this)){
                t.advance(amount);
            }
        }

    }


    public int getLastPointPosition() {
        return lastPointPosition;
    }

    public void setLastPointPosition(int lastPointPosition) {
        this.lastPointPosition = lastPointPosition;
    }



    public int getPosition(){
        return position;
    }

    protected void setPosition(int position){
        this.position = position;
    }

    public int getVictoryPoints(){
        return victoryPoint;
    }

    protected void setVictoryPoint(int victoryPoint){
        this.victoryPoint = victoryPoint;
    }


}
