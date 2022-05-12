package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.ADVANTAGE;
import static com.javintx.tennis.ScoreValue.FORTY;

public class AdvantageToFortyRule extends DefaultRule {

    public static final AdvantageToFortyRule INSTANCE = new AdvantageToFortyRule();

    private AdvantageToFortyRule() {
    }

    @Override
    public Optional<Score> apply(final Player player, final Score score) {
        if (FORTY == winnerScoreValue(player, score) && ADVANTAGE == looserScoreValue(player, score)) {
            return Optional.of(updateScore(player, winnerScoreValue(player, score), FORTY));
        }

        return Optional.empty();
    }
}
