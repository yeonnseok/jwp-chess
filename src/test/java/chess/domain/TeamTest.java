package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {

    @DisplayName("상대편 팀 구하기")
    @Test
    void getOpposingTeam() {
        final Team team = Team.WHITE;

        assertThat(team.getOpposingTeam()).isEqualTo(Team.BLACK);
    }

    @DisplayName("none")
    @Test
    void getNone() {
        final Team team = Team.NONE;

        assertThat(team.getOpposingTeam()).isEqualTo(Team.NONE);
    }

}