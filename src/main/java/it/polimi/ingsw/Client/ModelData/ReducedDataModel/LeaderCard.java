package it.polimi.ingsw.Client.ModelData.ReducedDataModel;

import it.polimi.ingsw.Model.LeaderCard.ActivationStrategy.ActivationStrategy;
import it.polimi.ingsw.Model.Marble.Marble;

import java.io.Serializable;
import java.util.List;

public class LeaderCard implements Serializable {

    private static final long serialVersionUID = 1166470735692348182L;
    private String id; //ok
    private ActivationStrategy.Type type;//ok
    private Marble.Color color; //ok
    private int victoryPoint;//ok
    private boolean active;//ok
    private List<Marble.Color> resourceRequirement;
    private List<String> developmentCardRequirement;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActivationStrategy.Type getType() {
        return type;
    }

    public void setType(ActivationStrategy.Type type) {
        this.type = type;
    }

    public Marble.Color getColor() {
        return color;
    }

    public void setColor(Marble.Color color) {
        this.color = color;
    }

    public int getVictoryPoint() {
        return victoryPoint;
    }

    public void setVictoryPoint(int victoryPoint) {
        this.victoryPoint = victoryPoint;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Marble.Color> getResourceRequirement() {
        return resourceRequirement;
    }

    public void setResourceRequirement(List<Marble.Color> resourceRequirement) {
        this.resourceRequirement = resourceRequirement;
    }

    public List<String> getDevelopmentCardRequirement() {
        return developmentCardRequirement;
    }

    public void setDevelopmentCardRequirement(List<String> developmentCardRequirement) {
        this.developmentCardRequirement = developmentCardRequirement;
    }
}
