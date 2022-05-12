# The Tennis Kata

Tennis is a ball and racket sport that is scored in an interesting way. The scoring system of a tennis match is based on
points, games and sets. We will focus on the scoring of points inside a single game for the scope of this exercise,
leaving explicitly out the set & match management.

The rules that we want you to consider are the ones found in the section “Game Score” of the wikipedia page of [Tennis
Scoring System](https://en.wikipedia.org/wiki/Tennis_scoring_system#Game_score) (excluding the tie-break, which is also
out of the scope of this exercise)

# Exercise:

## Feature 1 - Scoring a Game in Real Time

We want a library in Java that can be used to score a game in real time, so we can use it for all of the tennis related
endeavours we plan to undertake in the future. To begin with, we're going to need a way to update the score when a
player wins a point, see what the current score is after each service, and see if there is a winner based on the current
score and the rules above.

### Winning a Point Increases Score Correctly

**As a** library user

**I want** the score to increase when a player wins a point

**So that** I can display the current score correctly

- Given the score is 0:0
- When the server wins a point
- Then the score is 15:0


- Given the score is 15:15
- When the receiver wins a point
- Then the score is 15:30


- Given the score is 30:30
- When the server wins a point
- Then the score is 40:30

### Deuce and Advantage are Scored Correctly

**As a** library user

**I want** deuce and advantage to be scored correctly

**So that** I can display the current score correctly

- Given the score is 40:40
- When the receiver wins a point
- Then the score should be 40:A


- Given the score is A:40
- When the receiver wins a point
- Then the score should be 40:40

### Winning Points are Scored Correctly

**As a** library user
**I want** the winning point to be scored correctly
**So that** I can display the winner

- Given the score is 40:30
- When the server wins a point
- Then the server should win


- Given the score is 40:A
- When the receiver wins a point
- Then the receiver should win

## Feature 2 - External rule loading

We want you to (re)design your library to allow that the Tennis rules that you have applied can be dynamically replaced
or extended externally. We do not want you to implement all possible rulesets, instead we want you to provide enough
tools so other developers that will be integrating your library as a dependency, can use to define their own rules
without modifying the source code of your library. A few examples of how rules could change:

- If we decided to remove the rule of winning by 2 or more points than the opponent, that would mean that the first
  player to score 4 points would directly win
- If we decided that, when the score reaches deuce (40-40), the game is restarted (0-0)
- ...

Please add a small Readme explaining to other developers how to use their own ruleset, accompanied by references to the
tests that show how external rule loading works.

---

To create a new rule it is necessary to create a new class that extends the DefaultRule class and implements the apply
method. The return of the rule, if applied, should be the new score (it is possible to calculate it with the updateScore
method); if not, should be an empty Optional (In the DefaultRule there are some utils methods).

After that, it is necessary to include this new rule in the list of rules that are needed to call the TennisScoreGame
class.

---

### External rule loading - example a)

**As a** library user with my custom ruleset loaded
**I want** the winning point to be scored correctly
**So that** I can display the winner

- Given the score is 40:40
- When the server wins a point
- Then the server should win


- Given the score is 40:40
- When the receiver wins a point
- Then the receiver should win

# Other tips:

- Writing an interactive interfaces such as Command Line Interfaces or HTTP endpoints will not be taken into account (
  neither positive nor negative)
- Writing some kind of database persistence will not take in account
- Concurrency is out of scope of this kata
- A proper use of Java 8+ features is taken into account positively
- Functional Programming approaches are taken into account positively

# Credits

This kata is an evolution of the one provided by [Agile Katas](http://agilekatas.co.uk/katas/Tennis-Kata).
