# Live Football World Cup Score Board Library

Here is the documentation for how to use **Live Football World Cup Score Board Library **! 
This World Cup Scoreboard library shows all the ongoing matches and their scores

The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   a. Home team
   b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java (JDK 21), It could work on older version if we set the compiler source to older one in the pom file.
- Maven (for building and managing dependencies)

### Installation

1. Run `mvn clean install` to build the library and install it in your local Maven repository. 
2. Add the following dependency to your `pom.xml`:

   ```xml
   <dependency>
      <groupId>com.sportradar</groupId>
      <artifactId>football-worldcup-scoreboard</artifactId>
      <version>1.0.0-SNAPSHOT</version>
   </dependency>
3. [Optional] Upload it to Nexus server or online maven repository.


## Usage

Here’s how you can use the library in your Java project:

1. Import the necessary classes as follows:
```
   import com.sportradar.worldcup.scoreboard.WorldCupScoreBoard; 
   import com.sportradar.worldcup.scoreboard.FootballWorldCupScoreBoard;
```
2. Create an instance of FootballWorldCupScoreBoard as follows:
```
   WorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard();
```
3. You are ready,  
Now you can use this instance to do all the supported operations on your scoreboard.
4. Models 
- Match: represents one Match in scoreboard and consists of 2 Teams [homeTeam, awayTeam].
- Team : represents one Team in the match, Team consists of [name, score].
5. Exceptions:
There are 2 Exceptions that you need to handle when using the scoreboard
  - TeamAlreadyPlayingException: thrown by startNewMatch function if the match already started.
  - MatchNotExistException: thrown by updateMatchScore, finishMatch functions if the match is not exist on the scoreboard.
6. More Documentation,  
For more details how every operation works, you can check the Java docs in WorldCupScoreBoard interface.

# Example

```
   // create scoreBoard instance
   WorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard();

   // start few new matches
   scoreBoard.startNewMatch("Mexico", "Canada");
   scoreBoard.startNewMatch("Spain", "Brazil");
   scoreBoard.startNewMatch("Germany", "France");
   
   // update the score for this matches
   scoreBoard.updateMatchScore(new Match("Mexico", 0, "Canada", 5));
   scoreBoard.updateMatchScore(new Match("Spain", 10, "Brazil", 2));
   scoreBoard.updateMatchScore(new Match("Germany", 2, "France", 2));

   // finish 1 match
   scoreBoard.finishMatch("Mexico","Canada");
   
   // getSummary
   List<Match> orderedSummary = scoreBoard.getSummary();

   /**
   Output:
   Spain:   10 -  Brazil: 2
   Germany: 2  -  France: 2
   **/

```


   
