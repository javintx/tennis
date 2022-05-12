package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.FIFTEEN;
import static com.javintx.tennis.ScoreValue.LOVE;

public class LoveToFifteenRule extends DefaultRule {

    public static final LoveToFifteenRule INSTANCE = new LoveToFifteenRule();

    private LoveToFifteenRule() {
    }

    @Override
    public Optional<Score> apply(final Player player, final Score score) {
        if (LOVE == winnerScoreValue(player, score)) {
            return Optional.of(updateScore(player, FIFTEEN, looserScoreValue(player, score)));
        }

        return Optional.empty();
    }
}
