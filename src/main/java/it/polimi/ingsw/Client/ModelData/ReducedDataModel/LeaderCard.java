package it.polimi.ingsw.Client.ModelData.ReducedDataModel;

import it.polimi.ingsw.Model.Marble.MarbleColor;

import java.util.List;

public class LeaderCard {

    private String id;
    private String type;
    private int victoryPoint;
    private boolean active;
    private List<MarbleColor> resourceRequirement;

    //not a string----- need custom object
    private List<String> developmentCardRequirement;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<MarbleColor> getResourceRequirement() {
        return resourceRequirement;
    }

    public void setResourceRequirement(List<MarbleColor> resourceRequirement) {
        this.resourceRequirement = resourceRequirement;
    }

    public List<String> getDevelopmentCardRequirement() {
        return developmentCardRequirement;
    }

    public void setDevelopmentCardRequirement(List<String> developmentCardRequirement) {
        this.developmentCardRequirement = developmentCardRequirement;
    }
}
