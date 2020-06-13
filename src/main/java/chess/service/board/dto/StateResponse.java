package chess.service.board.dto;

import chess.domain.Finished;
import chess.domain.State;

import java.util.Objects;

public class StateResponse {
    private final boolean finished;
    private final String movedTurn;
    private final String nextTurn;

    private StateResponse(final boolean finished, final String movedTurn, final String nextTurn) {
        this.finished = finished;
        this.movedTurn = movedTurn;
        this.nextTurn = nextTurn;
    }

    public static StateResponse from(final State state) {
        return new StateResponse(Objects.equals(state.getClass(), Finished.class),
                state.getMovedTurn().name(),
                state.getNextTurn().name());
    }

    public boolean isFinished() {
        return finished;
    }

    public String getMovedTurn() {
        return movedTurn;
    }

    public String getNextTurn() {
        return nextTurn;
    }
}
