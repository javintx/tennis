package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.ADVANTAGE;
import static com.javintx.tennis.ScoreValue.FORTY;
import static com.javintx.tennis.ScoreValue.GAME;

public class FortyToGameRule extends DefaultRule {

    public static final FortyToGameRule INSTANCE = new FortyToGameRule();

    private FortyToGameRule() {
    }

    @Override
    public Optional<Score> apply(final Player player, final Score score) {
        if (FORTY == winnerScoreValue(player, score) && ADVANTAGE != looserScoreValue(player, score) && FORTY != looserScoreValue(player, score)) {
            return Optional.of(updateScore(player, GAME, looserScoreValue(player, score)));
        }

        return Optional.empty();
    }
}
