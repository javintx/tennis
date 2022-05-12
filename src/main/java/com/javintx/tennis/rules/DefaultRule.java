package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;
import com.javintx.tennis.ScoreValue;

import static com.javintx.tennis.Player.SERVER;

public abstract class DefaultRule implements Rule {

    protected ScoreValue winnerScoreValue(final Player player, final Score score) {
        if (player == SERVER) {
            return score.server();
        }
        return score.receiver();
    }

    protected ScoreValue looserScoreValue(final Player player, final Score score) {
        if (player == SERVER) {
            return score.receiver();
        }
        return score.server();
    }

    protected Score updateScore(final Player player, final ScoreValue winner, final ScoreValue looser) {
        if (SERVER == player) {
            return new Score(winner, looser);
        }
        return new Score(looser, winner);
    }
}
