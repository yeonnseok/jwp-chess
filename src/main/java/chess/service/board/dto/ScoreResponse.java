package chess.service.board.dto;

public class ScoreResponse {
    private final double whiteScore;
    private final double blackScore;

    public ScoreResponse(final double whiteScore, final double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
