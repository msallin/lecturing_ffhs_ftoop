package ffhs.lecturing.jpl.generics.solution;

public class SportsTeam {
}

abstract class Entity<TId> {
    public TId id;
}

class Player extends Entity<String> {
}

class Team extends Entity<Integer> {
}

class Repo<TEntity extends Entity<TId>, TId> {
    public TEntity findById(TId id){
        // do smth
        return (TEntity) new Object();
    }
}

class PlayerRepository extends Repo<Player, String> {
}

class TeamRepository extends Repo<Team, Integer> {
}
