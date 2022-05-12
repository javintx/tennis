package com.javintx.tennis;

import com.javintx.tennis.rules.AdvantageToFortyRule;
import com.javintx.tennis.rules.AdvantageToGameRule;
import com.javintx.tennis.rules.FifteenToThirtyRule;
import com.javintx.tennis.rules.FortyToAdvantageRule;
import com.javintx.tennis.rules.FortyToGameRule;
import com.javintx.tennis.rules.LoveToFifteenRule;
import com.javintx.tennis.rules.Rule;
import com.javintx.tennis.rules.ThirtyToFortyRule;

import java.util.List;

import static com.javintx.tennis.ScoreValue.GAME;
import static com.javintx.tennis.ScoreValue.LOVE;

public class TennisScoreGame {
    public static final List<Rule> DEFAULT_RULES = List.of(
            AdvantageToGameRule.INSTANCE,
            FortyToGameRule.INSTANCE,
            AdvantageToFortyRule.INSTANCE,
            FortyToAdvantageRule.INSTANCE,
            ThirtyToFortyRule.INSTANCE,
            FifteenToThirtyRule.INSTANCE,
            LoveToFifteenRule.INSTANCE);
    private final List<Rule> rules;
    private Score game;

    public TennisScoreGame() {
        this(DEFAULT_RULES);
    }

    public TennisScoreGame(final List<Rule> rules) {
        this.rules = rules;
        game = new Score(LOVE, LOVE);
    }

    public Score winAPoint(final Player player) {
        // some checks
        verifyScores();

        // apply rules
        for (Rule rule : rules) {
            var newScore = rule.apply(player, game);
            if (newScore.isPresent()) {
                game = newScore.get();
                break;
            }
        }

        return game;
    }

    private void verifyScores() {
        if (GAME == game.server() || GAME == game.receiver()) {
            throw new RuntimeException("The game is over");
        }
    }

}
