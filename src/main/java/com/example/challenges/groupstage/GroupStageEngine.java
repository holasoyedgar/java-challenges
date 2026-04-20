package com.example.challenges.groupstage;

import com.example.challenges.groupstage.domain.GroupStageReceipt;
import com.example.challenges.groupstage.domain.GroupStageRequest;
import com.example.challenges.groupstage.domain.TeamStanding;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupStageEngine {

    public GroupStageReceipt calculateStandings(GroupStageRequest request) {
        if (request == null || request.matches() == null || request.matches().isEmpty()) {
            return GroupStageReceipt.emptyReceipt();
        }

        List<TeamStanding> finalStandings = request.matches().stream()
                .filter(Objects::nonNull)
                .flatMap(matchResult -> Stream.of(
                        TeamStanding.fromResult(matchResult.homeTeam(), matchResult.homeGoals(), matchResult.awayGoals()),
                        TeamStanding.fromResult(matchResult.awayTeam(), matchResult.awayGoals(), matchResult.homeGoals())))
                .collect(Collectors.toMap(TeamStanding::teamName, Function.identity(), TeamStanding::merge))
                .values().stream()
                .sorted(Comparator.comparing(TeamStanding::points)
                        .thenComparing(TeamStanding::goalDifference)
                        .thenComparing(TeamStanding::goalsScored)
                        .reversed()
                        .thenComparing(TeamStanding::teamName))
                .toList();

        return new GroupStageReceipt(finalStandings);
    }
}