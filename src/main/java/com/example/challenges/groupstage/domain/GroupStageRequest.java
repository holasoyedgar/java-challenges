package com.example.challenges.groupstage.domain;

import java.util.List;

public record GroupStageRequest(
        List<MatchResult> matches
) {}