import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TeamController {
    PlayerList playerList;

    public List<Team> getTeams() {
        return teams;
    }

    private List<Team> teams;

    public TeamController() {
        playerList = new PlayerList();
        List<Player> players = playerList.getPlayerList();
        setPlayerScore(players);
        this.teams = generateTeams(players);
    }

    public void setPlayerScore(List<Player> aPlayers) {
        for (Player player : aPlayers) {
            double score = 0;
            score += getPointsBasedOnOverload(player);
            score += getPointsBasedOnSlayer(player);
            score += getPointsBasedOnExperience(player);
            score = (score * getMultiplierBasedOnPlaytime(player));
            player.setPlayerScore(Math.round(score * 10) / 10d);
        }
    }

    private int getPointsBasedOnOverload(Player aPlayer) {
        return aPlayer.isHasOverload() ? 4 : 0;
    }

    private int getPointsBasedOnSlayer(Player aPlayer) {
        return switch (aPlayer.getSlayerLevel()) {
            case EIGHTY_TO_NINETYNINE -> 2;
            case NINETYNINE_TO_ONEFIFTEEN -> 3;
            case ONEFIFTEEN_TO_ONETWENTY -> 4;
            default -> 0;
        };
    }

    private int getPointsBasedOnExperience(Player aPlayer) {
        return switch (aPlayer.getBossExperience()) {
            case TWO -> 2;
            case THREE -> 4;
            case FOUR -> 6;
            case FIVE -> 7;
            case SIX -> 8;
            case SEVEN -> 10;
            case EIGHT -> 11;
            case NINE -> 13;
            default -> 0;
        };
    }

    private double getMultiplierBasedOnPlaytime(Player aPlayer) {
        return switch (aPlayer.getPlaytime()) {
            case TWO_TO_FOUR -> 1.4;
            case FOUR_TO_SIX -> 1.6;
            case SIX_TO_EIGHT -> 1.8;
            case MORE_THAN_EIGHT -> 2;
            default -> 1;
        };
    }

    private List<Team> generateTeams(List<Player> aPlayerlist) {
        List<Team> optimalTeams = new ArrayList<Team>();
        List<Team> randomTeams = new ArrayList<Team>();
        double currentOptimalTeamPointDifference = 1000;

        for (int i = 0; i < 10000000; i++) {
            randomTeams = getRandomTeams(aPlayerlist);
            calculateTeamPoints(randomTeams);
            double averagePointDifference = calculateTeamsPointDifference(randomTeams);
            double potentialOptimalTeamPointDifference = averagePointDifference - calculatePointsBasedOnPreferredTeammates(randomTeams);
            if (potentialOptimalTeamPointDifference < currentOptimalTeamPointDifference) {
                optimalTeams = randomTeams;
                currentOptimalTeamPointDifference = potentialOptimalTeamPointDifference;
                System.out.println("New optimal team found with average point difference of: " + averagePointDifference);
                System.out.println("Taking into account preferred teammates a point difference of: " + potentialOptimalTeamPointDifference);
                System.out.println();
            }
        }
        return optimalTeams;
    }

    private List<Team> getRandomTeams(List<Player> aPlayerlist) {
        List<Team> teamList = new ArrayList<Team>();
        //Total of 15 participants
        List<Integer> randomPosition = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14).collect(Collectors.toList());
        Random random = new Random();
        while (!randomPosition.isEmpty()) {
            //Make teams of 3
            Team team = new Team();
            List teamArray = new ArrayList<Player>();
            for (int i = 0; i < 3; i++) {
                int playerNumber = random.nextInt(randomPosition.size());
                teamArray.add(aPlayerlist.get(randomPosition.get(playerNumber)));
                randomPosition.remove(playerNumber);
            }
            team.setPlayers(teamArray);
            teamList.add(team);
        }
        return teamList;
    }

    private void calculateTeamPoints(List<Team> aTeams) {
        for (Team team : aTeams) {
            double teamPoints = 0;
            for (Player player : team.getPlayers()) {
                teamPoints += player.getPlayerScore();
            }
            team.setTotalPoints(Math.round(teamPoints * 10) / 10d);
        }
    }

    private double calculateTeamsPointDifference(List<Team> aTeams) {
        List<Double> points = new ArrayList<Double>();
        double totalPointDifference = 0;

        for (Team team : aTeams) {
            points.add(team.getTotalPoints());
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double pointDifference = Math.abs(points.get(i) - points.get(j));
                totalPointDifference += pointDifference;
            }
        }
        return totalPointDifference / ((points.size() * (points.size() - 1)) / 2);
    }

    private double calculatePointsBasedOnPreferredTeammates(List<Team> aTeams) {
        double pointsToDeduct = 0;
        for (Team team : aTeams) {
            pointsToDeduct += calculateTeamPreferredTeamMatesPoints(team);
        }
        return pointsToDeduct;
    }

    private double calculateTeamPreferredTeamMatesPoints(Team aTeam) {
        double preferredTeamPoints = 0;
        List<Player> players = aTeam.getPlayers();
        for (Player player : players) {
            if (player.getPreferredTeammates() != null) {
                for (Player teammate : players) {
                    if (player.getPreferredTeammates().contains(teammate)) {
                        preferredTeamPoints += 0.5;
                    }
                }
            }
        }
        return preferredTeamPoints;
    }
}