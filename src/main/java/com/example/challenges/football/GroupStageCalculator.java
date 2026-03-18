package com.example.challenges.football;

import com.example.challenges.football.domain.MatchResult;
import com.example.challenges.football.domain.TeamStanding;
import com.example.challenges.football.domain.TournamentRequest;
import com.example.challenges.football.domain.TournamentResult;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupStageCalculator {

    public TournamentResult calculateStandings(TournamentRequest request) {
        if (request == null || request.matches().isEmpty()) {
            return new TournamentResult(List.of());
        }
        Map<String, TeamStanding> standings = new HashMap<>();
        for (MatchResult result : request.matches()) {
            standings.merge(result.homeTeam(), new TeamStanding(result.homeTeam(), result.homeGoals(), result.awayGoals()), TeamStanding::combineWith);
            standings.merge(result.awayTeam(), new TeamStanding(result.awayTeam(), result.awayGoals(), result.homeGoals()), TeamStanding::combineWith);
        }

        Comparator<TeamStanding> comparator = Comparator.comparing(TeamStanding::points)
                .thenComparing(TeamStanding::goalDifference)
                .thenComparing(TeamStanding::goalsScored)
                .reversed()
                .thenComparing(TeamStanding::teamName);

        List<TeamStanding> sortedStandings = standings.values()
                .stream()
                .sorted(comparator)
                .toList();

        return new TournamentResult(sortedStandings);
    }
}