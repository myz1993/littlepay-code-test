package com.littlepay.codetest.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TripOutput {
    private final Date startTime;
    private final Date finishTime;
    private final Long durationSecs;
    private final StopId fromStopId;
    private final StopId toStopId;
    private final BigDecimal chargedAmount;
    private final String companyId;
    private final String busId;
    private final String pan;
    private final TripStatus status;
}