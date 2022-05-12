package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.FORTY;
import static com.javintx.tennis.ScoreValue.THIRTY;

public class ThirtyToFortyRule extends DefaultRule {

    public static final ThirtyToFortyRule INSTANCE = new ThirtyToFortyRule();

    private ThirtyToFortyRule() {
    }

    @Override
    public Optional<Score> apply(final Player player, final Score score) {
        if (THIRTY == winnerScoreValue(player, score)) {
            return Optional.of(updateScore(player, FORTY, looserScoreValue(player, score)));
        }

        return Optional.empty();
    }
}
