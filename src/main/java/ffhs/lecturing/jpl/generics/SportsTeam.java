package ffhs.lecturing.jpl.generics;

public class SportsTeam {
}

class Player {
    public String Id;
}

class Team {
    public int Id;
}

class PlayerRepository {
    public Player findById(String id) {
        // execute SQL to find player by id
        return new Player();
    }
}

class TeamRepository {
    public Team findById(int id) {
        // execute SQL to find team by id
        return new Team();
    }
}
