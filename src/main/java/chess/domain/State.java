package chess.domain;

public interface State {
    Team getMovedTurn();

    Team getNextTurn();
}
