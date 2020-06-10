package chess.domain;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException() {
        super("보드 범위를 벗어난 위치입니다.");
    }
}
