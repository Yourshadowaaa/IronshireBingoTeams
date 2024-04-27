import Enums.BOSSEXPERIENCE;
import Enums.PLAYTIME;
import Enums.SLAYERLEVEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerList {
    public List<Player> getPlayerList() {
        return playerList;
    }

    private List<Player> playerList;


    public PlayerList() {
        this.playerList = createPlayers();
        this.playerList = setPreferredTeammates(this.playerList);
    }

    private List<Player> createPlayers() {
        ArrayList<Player> newPlayers = new ArrayList<Player>();
        newPlayers.add(
                new Player(
                        "Lundai",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.FOUR,
                        PLAYTIME.LESS_THAN_ONE,
                        null));
        newPlayers.add(
                new Player(
                        "Dan Lei",
                        true,
                        SLAYERLEVEL.EIGHTY_TO_NINETYNINE,
                        BOSSEXPERIENCE.THREE,
                        PLAYTIME.TWO_TO_FOUR,
                        null));
        newPlayers.add(
                new Player(
                        "NotTheHCIM",
                        true,
                        SLAYERLEVEL.NINETYNINE_TO_ONEFIFTEEN,
                        BOSSEXPERIENCE.FOUR,
                        PLAYTIME.TWO_TO_FOUR,
                        Collections.singletonList("PL leIronMan")));
        newPlayers.add(
                new Player(
                        "im not hench",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.EIGHT,
                        PLAYTIME.TWO_TO_FOUR,
                        null));
        newPlayers.add(
                new Player(
                        "leech pvmer",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.NINE,
                        PLAYTIME.TWO_TO_FOUR,
                        Arrays.asList("im not hench", "Mtx_Rent", "Get Robbed")));
        newPlayers.add(
                new Player(
                        "Millido",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.SIX,
                        PLAYTIME.SIX_TO_EIGHT,
                        null));
        newPlayers.add(
                new Player(
                        "Poppz",
                        true,
                        SLAYERLEVEL.NINETYNINE_TO_ONEFIFTEEN,
                        BOSSEXPERIENCE.NINE,
                        PLAYTIME.TWO_TO_FOUR,
                        Arrays.asList("im not hench", "leech pvmer", "Get Robbed")));
        newPlayers.add(
                new Player(
                        "PL leIronMan",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.NINE,
                        PLAYTIME.SIX_TO_EIGHT,
                        Collections.singletonList("NotTheHCIM")));
        newPlayers.add(
                new Player(
                        "EmotiveGuy",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.THREE,
                        PLAYTIME.TWO_TO_FOUR,
                        null));
        newPlayers.add(
                new Player(
                        "Meliwa",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.NINE,
                        PLAYTIME.LESS_THAN_ONE,
                        null));
        newPlayers.add(
                new Player(
                        "Elton Solo",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.NINE,
                        PLAYTIME.TWO_TO_FOUR,
                        null));
        newPlayers.add(
                new Player(
                        "Tiny Birbs",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.NINE,
                        PLAYTIME.FOUR_TO_SIX,
                        null));
        newPlayers.add(
                new Player(
                        "Metastrate",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.EIGHT,
                        PLAYTIME.LESS_THAN_ONE,
                        Arrays.asList("NotTheHCIM", "PL leIronMan")));
        newPlayers.add(
                new Player(
                        "Mtx_Rent",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.NINE,
                        PLAYTIME.MORE_THAN_EIGHT,
                        null));
        newPlayers.add(
                new Player(
                        "Affable Sage",
                        true,
                        SLAYERLEVEL.ONEFIFTEEN_TO_ONETWENTY,
                        BOSSEXPERIENCE.SIX,
                        PLAYTIME.TWO_TO_FOUR,
                        Arrays.asList("im not hench", "Meliwa")));
        return newPlayers;
    }

    private List<Player> setPreferredTeammates(List<Player> aPlayerList) {
        List<Player> newPlayerList = new ArrayList<Player>();
        //For each player
        for (Player player : aPlayerList) {
            List<String> teamMatesRsns = player.getPreferredTeammatesRsn();
            //If they have preferred teammates
            if (teamMatesRsns != null) {
                List<Player> preferredTeammates = new ArrayList<Player>();
                //Fill an array with the actual players
                for (String teammateRsn : teamMatesRsns) {
                    preferredTeammates.add(getPlayerByRsn(aPlayerList, teammateRsn));
                }
                player.setPreferredTeammates(preferredTeammates);
            }
            newPlayerList.add(player);
        }
        return newPlayerList;
    }

    public Player getPlayerByRsn(List<Player> aPLayerList, String aRsn) {
        for (Player player : aPLayerList) {
            if (player.getRsn().equals(aRsn)) {
                return player;
            }
        }
        return null;
    }
}