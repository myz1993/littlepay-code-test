package com.littlepay.codetest.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class Trip {
    private final Date startTime;
    private final Date finishTime;
    private final Long durationSecs;
    private final String fromStopId;
    private final String toStopId;
    private final BigDecimal chargedAmount;
    private final String companyId;
    private final String busId;
    private final String pan;
    private final String status;
}
