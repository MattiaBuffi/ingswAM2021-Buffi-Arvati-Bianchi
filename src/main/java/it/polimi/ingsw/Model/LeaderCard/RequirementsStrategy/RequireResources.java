package it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy;

import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.Player.Player;
/**
 *  controlla dei requisiti di risorse su un Player
 */
public class RequireResources implements RequirementStrategy {

    private ResourceList resource;

    public RequireResources(ResourceList resource) {
        this.resource = resource;
    }

    public ResourceList getResource() {
        return resource;
    }

    @Override
    public boolean canActivate(Player player) {

        if(!player.getResourceStorage().getResources().contains(resource)){
            return false;
        }

        return true;
    }

    @Override
    public Type getType() {
        return Type.RESOURCE;
    }

}
