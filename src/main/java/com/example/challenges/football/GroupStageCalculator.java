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
        Map<String, TeamStanding> standings = getTeamStandings(request);

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

    private static Map<String, TeamStanding> getTeamStandings(TournamentRequest request) {
        Map<String, TeamStanding> standings = new HashMap<>();
        for (MatchResult result : request.matches()) {
            standings.compute(result.homeTeam(),
                    (teamName, standing) -> (standing == null ? new TeamStanding(teamName, 0, 0, 0) : standing)
                                    .withMatchResult(result.homeGoals(), result.awayGoals()));

            standings.compute(result.awayTeam(),
                    (teamName, standing) -> (standing == null ? new TeamStanding(teamName, 0, 0, 0) : standing)
                            .withMatchResult(result.awayGoals(), result.homeGoals()));
        }
        return standings;
    }
}