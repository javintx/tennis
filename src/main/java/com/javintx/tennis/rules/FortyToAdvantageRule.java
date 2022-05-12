package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.ADVANTAGE;
import static com.javintx.tennis.ScoreValue.FORTY;

public class FortyToAdvantageRule extends DefaultRule {

    public static final FortyToAdvantageRule INSTANCE = new FortyToAdvantageRule();

    private FortyToAdvantageRule() {
    }

    @Override
    public Optional<Score> apply(Player player, Score score) {
        if (FORTY == winnerScoreValue(player, score) && FORTY == looserScoreValue(player, score)) {
            return Optional.of(updateScore(player, ADVANTAGE, looserScoreValue(player, score)));
        }

        return Optional.empty();
    }
}
