package it.polimi.ingsw.Parser;

import it.polimi.ingsw.Model.LeaderCard.ActivationStrategy.*;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequireCards;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequireResources;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequirementStrategy;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

public class Decoder {

    public static ResourceList decodeList(List<String> list, int quantity, int color){
        ResourceList decodedList = new ResourceList();
        for(String s: list){
            decodedList.add(decodeMarbleColor(s.charAt(color)), Character.getNumericValue(s.charAt(quantity)));
        }
        return decodedList;
    }

    public static Marble.Color decodeMarbleColor(Character string){
        switch (string){
            case 'R':
                return Marble.Color.RED;
            case 'W':
                return Marble.Color.WHITE;
            case 'Y':
                return Marble.Color.YELLOW;
            case 'G':
                return Marble.Color.GREY;
            case 'B':
                return Marble.Color.BLUE;
            case 'P':
                return Marble.Color.PURPLE;
            default:
                return null;
        }
    }


    public static DevelopmentCard.Color decodeCardColor(char c){
        switch (c){
            case 'G':
                return DevelopmentCard.Color.GREEN;
            case 'B':
                return DevelopmentCard.Color.BLUE;
            case 'Y':
                return DevelopmentCard.Color.YELLOW;
            case 'P':
                return DevelopmentCard.Color.PURPLE;
            default:
                return null;
        }
    }

    private static List<DevelopmentCard.Info> extractCardRequirements(List<String> list){
        List<DevelopmentCard.Info> infos = new ArrayList<>();

        for (String s: list){
            int quantity = Character.getNumericValue(s.charAt(0));
            int level = Character.getNumericValue(s.charAt(2));
            DevelopmentCard.Color color= decodeCardColor(s.charAt(4));
            for (int i = 0; i < quantity; i++) {
                infos.add(new DevelopmentCard.Info(level, color));
            }
        }

        return infos;
    }

    public static RequirementStrategy decodeRequirementStrategy(String requirementType, List<String> requiredResource){

        switch (requirementType){
            case "CARD":
                return new RequireCards(extractCardRequirements(requiredResource));
            case "RSS":
                return new RequireResources(decodeList(requiredResource, 0,2));
            default:
                return null;
        }

    }



    public static ActivationStrategy decodeActivationStrategy(String activationType, String color){
        switch (activationType){
            case "DISCOUNT":
                return new Discount(decodeMarbleColor(color.charAt(0)));
            case "STORAGE":
                return new ExtraShelf(decodeMarbleColor(color.charAt(0)));
            case "WHITE":
                return new WhiteMarble(decodeMarbleColor(color.charAt(0)));
            case "DEVELOPMENT":
                return new ExtraProduction(decodeMarbleColor(color.charAt(0)));
        }
        return null;
    }

}
