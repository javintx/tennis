package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.FIFTEEN;
import static com.javintx.tennis.ScoreValue.THIRTY;

public class FifteenToThirtyRule extends DefaultRule {

    public static final FifteenToThirtyRule INSTANCE = new FifteenToThirtyRule();

    private FifteenToThirtyRule() {
    }

    @Override
    public Optional<Score> apply(final Player player, final Score score) {
        if (FIFTEEN == winnerScoreValue(player, score)) {
            return Optional.of(updateScore(player, THIRTY, looserScoreValue(player, score)));
        }

        return Optional.empty();
    }
}
