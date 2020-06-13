package chess.service.board.dto;

import chess.domain.Finished;
import chess.domain.State;

public class StateResponse {
    private final boolean finished;
    private final String turn;

    private StateResponse(final boolean finished, final String turn) {
        this.finished = finished;
        this.turn = turn;
    }

    public static StateResponse from(final State state) {
        return new StateResponse(state.getClass().isInstance(Finished.class),
                state.getNextTurn().name());
    }

    public boolean isFinished() {
        return finished;
    }

    public String getTurn() {
        return turn;
    }
}
