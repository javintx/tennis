package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

import static com.javintx.tennis.ScoreValue.ADVANTAGE;
import static com.javintx.tennis.ScoreValue.GAME;

public class AdvantageToGameRule extends DefaultRule {

    public static final AdvantageToGameRule INSTANCE = new AdvantageToGameRule();

    private AdvantageToGameRule() {
    }

    @Override
    public Optional<Score> apply(final Player player, final Score score) {
        if (ADVANTAGE == winnerScoreValue(player, score)) {
            return Optional.of(updateScore(player, GAME, looserScoreValue(player, score)));
        }

        return Optional.empty();
    }
}
