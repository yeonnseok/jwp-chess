package chess.domain;

public class Finished implements State {

    private final Team movedTurn;

    public Finished(final Team team) {
        this.movedTurn = team;
    }

    @Override
    public Team getMovedTurn() {
        return movedTurn;
    }

    @Override
    public Team getNextTurn() {
        return movedTurn.getOpposingTeam();
    }
}
