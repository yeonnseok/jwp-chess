package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @DisplayName("a1 ~ h8까지 포지션 캐시")
    @ParameterizedTest
    @MethodSource("providePositionSource")
    void positionCache(String positionValue, char expectedFile, int expectedRank) {
        Position position = Position.from(positionValue);
        assertThat(position.getFile()).isEqualTo(expectedFile);
        assertThat(position.getRank()).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> providePositionSource() {
        return Stream.of(
                Arguments.of("a1", 'a', 1), Arguments.of("a2", 'a', 2),
                Arguments.of("a3", 'a', 3), Arguments.of("a4", 'a', 4),
                Arguments.of("a5", 'a', 5), Arguments.of("a6", 'a', 6),
                Arguments.of("a7", 'a', 7), Arguments.of("a8", 'a', 8),

                Arguments.of("b1", 'b', 1), Arguments.of("b2", 'b', 2),
                Arguments.of("b3", 'b', 3), Arguments.of("b4", 'b', 4),
                Arguments.of("b5", 'b', 5), Arguments.of("b6", 'b', 6),
                Arguments.of("b7", 'b', 7), Arguments.of("b8", 'b', 8),

                Arguments.of("c1", 'c', 1), Arguments.of("c2", 'c', 2),
                Arguments.of("c3", 'c', 3), Arguments.of("c4", 'c', 4),
                Arguments.of("c5", 'c', 5), Arguments.of("c6", 'c', 6),
                Arguments.of("c7", 'c', 7), Arguments.of("c8", 'c', 8),

                Arguments.of("d1", 'd', 1), Arguments.of("d2", 'd', 2),
                Arguments.of("d3", 'd', 3), Arguments.of("d4", 'd', 4),
                Arguments.of("d5", 'd', 5), Arguments.of("d6", 'd', 6),
                Arguments.of("d7", 'd', 7), Arguments.of("d8", 'd', 8),

                Arguments.of("e1", 'e', 1), Arguments.of("e2", 'e', 2),
                Arguments.of("e3", 'e', 3), Arguments.of("e4", 'e', 4),
                Arguments.of("e5", 'e', 5), Arguments.of("e6", 'e', 6),
                Arguments.of("e7", 'e', 7), Arguments.of("e8", 'e', 8),

                Arguments.of("f1", 'f', 1), Arguments.of("f2", 'f', 2),
                Arguments.of("f3", 'f', 3), Arguments.of("f4", 'f', 4),
                Arguments.of("f5", 'f', 5), Arguments.of("f6", 'f', 6),
                Arguments.of("f7", 'f', 7), Arguments.of("f8", 'f', 8),

                Arguments.of("g1", 'g', 1), Arguments.of("g2", 'g', 2),
                Arguments.of("g3", 'g', 3), Arguments.of("g4", 'g', 4),
                Arguments.of("g5", 'g', 5), Arguments.of("g6", 'g', 6),
                Arguments.of("g7", 'g', 7), Arguments.of("g8", 'g', 8),

                Arguments.of("h1", 'h', 1), Arguments.of("h2", 'h', 2),
                Arguments.of("h3", 'h', 3), Arguments.of("h4", 'h', 4),
                Arguments.of("h5", 'h', 5), Arguments.of("h6", 'h', 6),
                Arguments.of("h7", 'h', 7), Arguments.of("h8", 'h', 8)
        );
    }

    @DisplayName("보드 범위 벗어난 포지션 생성시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"a9", "k5", "r10", "xx", "12"})
    void constructWithOverRange(String positionValue) {
        assertThatThrownBy(() -> {
            Position.from(positionValue);
        }).isInstanceOf(InvalidPositionException.class);
    }

}