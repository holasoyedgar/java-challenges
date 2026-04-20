package com.example.challenges.groupstage.domain;

import java.util.List;

public record GroupStageReceipt(
        List<TeamStanding> standings
) {
    public static GroupStageReceipt emptyReceipt() {
        return new GroupStageReceipt(List.of());
    }
}