package com.example.challenges.groupstage;

import com.example.challenges.groupstage.domain.GroupStageReceipt;
import com.example.challenges.groupstage.domain.GroupStageRequest;
import com.example.challenges.groupstage.domain.MatchResult;
import com.example.challenges.groupstage.domain.TeamStanding;

import java.util.*;
import java.util.stream.Collectors;

public class GroupStageEngine {

    public GroupStageReceipt calculateStandings(GroupStageRequest request) {
        if (request == null || request.matches() == null || request.matches().isEmpty()) {
            return GroupStageReceipt.emptyReceipt();
        }
        Map<String, TeamStanding> teamStandings = getTeamStandings(request);

        List<TeamStanding> finalStandings = teamStandings.values().stream()
                .sorted(Comparator.comparing(TeamStanding::points)
                        .thenComparing(TeamStanding::goalDifference)
                        .thenComparing(TeamStanding::goalsScored)
                        .reversed()
                        .thenComparing(TeamStanding::teamName))
                .toList();
        return new GroupStageReceipt(finalStandings);
    }

    private static Map<String, TeamStanding> getTeamStandings(GroupStageRequest request) {
        Map<String, TeamStanding> teamStandings = new HashMap<>();
        for (MatchResult matchResult : request.matches()) {
            if (matchResult == null) {
                // Ignoring the null match result, with streams it could be filtered out.
                continue;
            }
            teamStandings.compute(matchResult.homeTeam(), (teamName, teamStanding) ->
                    ((teamStanding == null) ? TeamStanding.defaultTeamStanding(matchResult.homeTeam()) : teamStanding)
                            .withResult(matchResult.homeGoals(), matchResult.awayGoals()));

            teamStandings.compute(matchResult.awayTeam(), (teamName, teamStanding) ->
                    ((teamStanding == null) ? TeamStanding.defaultTeamStanding(matchResult.awayTeam()) : teamStanding)
                            .withResult(matchResult.awayGoals(), matchResult.homeGoals()));

        }
        return teamStandings;
    }
}