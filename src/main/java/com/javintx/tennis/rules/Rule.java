package com.javintx.tennis.rules;

import com.javintx.tennis.Player;
import com.javintx.tennis.Score;

import java.util.Optional;

public interface Rule {

    Optional<Score> apply(final Player player, final Score score);

}

