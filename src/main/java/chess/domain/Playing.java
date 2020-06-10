package chess.domain;

public class Playing implements State {

    private final Team movedTurn;

    public Playing(final Team team) {
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
