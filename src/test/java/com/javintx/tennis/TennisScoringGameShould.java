package com.javintx.tennis;

import com.javintx.tennis.rules.AdvantageToFortyRule;
import com.javintx.tennis.rules.AdvantageToGameRule;
import com.javintx.tennis.rules.FifteenToThirtyRule;
import com.javintx.tennis.rules.FortyToAdvantageRule;
import com.javintx.tennis.rules.FortyToGameRule;
import com.javintx.tennis.rules.LoveToFifteenRule;
import com.javintx.tennis.rules.ResetFortyRule;
import com.javintx.tennis.rules.Rule;
import com.javintx.tennis.rules.ThirtyToFortyRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.javintx.tennis.Player.RECEIVER;
import static com.javintx.tennis.Player.SERVER;
import static com.javintx.tennis.ScoreValue.ADVANTAGE;
import static com.javintx.tennis.ScoreValue.FIFTEEN;
import static com.javintx.tennis.ScoreValue.FORTY;
import static com.javintx.tennis.ScoreValue.GAME;
import static com.javintx.tennis.ScoreValue.LOVE;
import static com.javintx.tennis.ScoreValue.THIRTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TennisScoringGameShould {
    @Test
    void increase_score_correctly_for_a_winning_point() {
        // given
        var tennisScoreGame = new TennisScoreGame();

        // when
        var actual = tennisScoreGame.winAPoint(SERVER);

        // then
        var score = new Score(FIFTEEN, LOVE);
        assertEquals(score, actual);

        // when
        actual = tennisScoreGame.winAPoint(RECEIVER);

        // then
        score = new Score(FIFTEEN, FIFTEEN);
        assertEquals(score, actual);

        // when
        actual = tennisScoreGame.winAPoint(SERVER);

        // then
        score = new Score(THIRTY, FIFTEEN);
        assertEquals(score, actual);

        // when
        actual = tennisScoreGame.winAPoint(SERVER);

        // then
        score = new Score(FORTY, FIFTEEN);
        assertEquals(score, actual);

        // when
        actual = tennisScoreGame.winAPoint(SERVER);

        // then
        score = new Score(GAME, FIFTEEN);
        assertEquals(score, actual);

        // when

        // then
        assertThrows(RuntimeException.class, () -> tennisScoreGame.winAPoint(SERVER));
    }

    @Test
    void deuce_and_advantage_score_correctly() {
        // given
        var tennisScoreGame = new TennisScoreGame();

        tennisScoreGame.winAPoint(SERVER);
        tennisScoreGame.winAPoint(SERVER);
        tennisScoreGame.winAPoint(SERVER);
        tennisScoreGame.winAPoint(RECEIVER);
        tennisScoreGame.winAPoint(RECEIVER);
        var actual = tennisScoreGame.winAPoint(RECEIVER);
        var score = new Score(FORTY, FORTY);
        assertEquals(score, actual);

        // when
        actual = tennisScoreGame.winAPoint(SERVER);

        // then
        score = new Score(ADVANTAGE, FORTY);
        assertEquals(score, actual);

        // when
        actual = tennisScoreGame.winAPoint(RECEIVER);

        // then
        score = new Score(FORTY, FORTY);
        assertEquals(score, actual);

        // when
        tennisScoreGame.winAPoint(RECEIVER);
        actual = tennisScoreGame.winAPoint(RECEIVER);

        // then
        score = new Score(FORTY, GAME);
        assertEquals(score, actual);
    }

    @Test
    void apply_extra_rules() {
        // given
        var extraTennisRules = List.<Rule>of(
                AdvantageToGameRule.INSTANCE,
                FortyToGameRule.INSTANCE,
                AdvantageToFortyRule.INSTANCE,
                FortyToAdvantageRule.INSTANCE,
                ResetFortyRule.INSTANCE, // extra rule
                ThirtyToFortyRule.INSTANCE,
                FifteenToThirtyRule.INSTANCE,
                LoveToFifteenRule.INSTANCE);
        var tennisScoreGame = new TennisScoreGame(extraTennisRules);

        tennisScoreGame.winAPoint(SERVER);
        tennisScoreGame.winAPoint(SERVER);
        tennisScoreGame.winAPoint(SERVER);
        tennisScoreGame.winAPoint(RECEIVER);
        var actual = tennisScoreGame.winAPoint(RECEIVER);

        var score = new Score(FORTY, THIRTY);
        assertEquals(score, actual);

        actual = tennisScoreGame.winAPoint(RECEIVER);
        score = new Score(LOVE, LOVE);
        assertEquals(score, actual);
    }
}
