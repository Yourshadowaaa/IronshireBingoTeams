import java.util.List;

/*
This project automatically generates teams based on the following factors:
Overload access: Yes = 4, No = 0
Slayer level: 0-80 = 0, 80-99 = 2, 99-115 = 3, 115=120 = 4
Bossing experience: Ranging from 2 to 13 points
Playtime: Less than 1 hour = 1, 2-4 hours = 1.4, 4-6 hours = 1.6, 6-8 hours = 1.8, 8+ hours = 2

The following calculation is done to assign points to each person based on the points mentioned above:
(Overload access + Slayer level + Bossing Experience) * Playtime

Random teams are made, calculating the total points per team.
If there is a composition of teams where the average between every combination of teams is lower, this is the new optimal team setup until a new one is found or we're done simulating 10.000.000 combinations.

For each member that is matched with a teammate they preferred the average points difference gets reduced by 0.5.
This favours teams that consist of preferred teammates slightly over 'balanced' teams.
 */
public class Main {
    public static void main(String[] args) {
        TeamController teamController = new TeamController();
        printTeams(teamController.getTeams());
    }

    private static void printTeams(List<Team> aTeams) {
        int teamNumber = 1;
        for (Team team : aTeams) {
            System.out.println("Team: " + teamNumber + "  -  " + team.getTotalPoints() + " points total");
            for (Player player : team.getPlayers()) {
                System.out.println(player.getRsn() + "  -  " + player.getPlayerScore());
            }
            System.out.println();
            System.out.println();
            teamNumber++;
        }
    }
}