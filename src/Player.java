import Enums.BOSSEXPERIENCE;
import Enums.PLAYTIME;
import Enums.SLAYERLEVEL;

import java.util.List;

public class Player {
    private final String rsn;
    private final boolean hasOverload;
    private final SLAYERLEVEL slayerLevel;
    private final BOSSEXPERIENCE bossExperience;
    private final PLAYTIME playtime;
    private final List<String> preferredTeammatesRsn;
    private List<Player> preferredTeammates;
    private double playerScore;

    public Player(String rsn, boolean hasOverload, SLAYERLEVEL slayerLevel, BOSSEXPERIENCE bossExperience, PLAYTIME playtime, List<String> preferredTeammatesRsn) {
        this.rsn = rsn;
        this.hasOverload = hasOverload;
        this.slayerLevel = slayerLevel;
        this.bossExperience = bossExperience;
        this.playtime = playtime;
        this.preferredTeammatesRsn = preferredTeammatesRsn;
    }

    public String getRsn() {
        return rsn;
    }

    public List<String> getPreferredTeammatesRsn() {
        return preferredTeammatesRsn;
    }

    public List<Player> getPreferredTeammates() {
        return preferredTeammates;
    }

    public void setPreferredTeammates(List<Player> aPlayerList) {
        this.preferredTeammates = aPlayerList;
    }

    public PLAYTIME getPlaytime() {
        return playtime;
    }

    public BOSSEXPERIENCE getBossExperience() {
        return bossExperience;
    }

    public SLAYERLEVEL getSlayerLevel() {
        return slayerLevel;
    }

    public boolean isHasOverload() {
        return hasOverload;
    }

    public double getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(double playerScore) {
        this.playerScore = playerScore;
    }
}