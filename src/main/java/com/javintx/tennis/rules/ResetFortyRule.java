package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.FORTY;
import static com.javintx.tennis.ScoreValue.LOVE;
import static com.javintx.tennis.ScoreValue.THIRTY;

public class ResetFortyRule extends DefaultRule {

    public static final ResetFortyRule INSTANCE = new ResetFortyRule();

    private ResetFortyRule() {
    }

    @Override
    public Optional<Score> apply(final Player player, final Score score) {
        if (THIRTY == winnerScoreValue(player, score) && FORTY == looserScoreValue(player, score)) {
            return Optional.of(updateScore(player, LOVE, LOVE));
        }

        return Optional.empty();
    }
}
