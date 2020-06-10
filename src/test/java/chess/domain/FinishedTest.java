package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinishedTest {

    @DisplayName("이전에 움직인 차례")
    @Test
    void getMovedTurn() {
        Finished finished = new Finished(Team.BLACK);

        assertThat(finished.getMovedTurn()).isEqualTo(Team.BLACK);
    }

    @DisplayName("다음에 움직일 차례")
    @Test
    void getNextTurn() {
        Finished finished = new Finished(Team.BLACK);

        assertThat(finished.getNextTurn()).isEqualTo(Team.WHITE);
    }

}