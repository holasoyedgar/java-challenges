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
        if (request == null || !request.isValid()) {
            throw new IllegalArgumentException("Tournament request is invalid");
        }
        if (request.areMatchesEmpty()) {
            return new TournamentResult(List.of());
        }
        Map<String, TeamStanding> standings = new HashMap<>();
        for (MatchResult result : request.matches()) {
            standings.put(result.homeTeam(), TeamStanding.calculateTeamStanding(standings, result.homeTeam(), result.homeGoals(), result.awayGoals()));
            standings.put(result.awayTeam(), TeamStanding.calculateTeamStanding(standings, result.awayTeam(), result.awayGoals(), result.homeGoals()));
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