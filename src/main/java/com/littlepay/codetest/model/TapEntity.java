package com.littlepay.codetest.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class TapEntity {
    private final int id;
    private final Date dateTimeUtc;
    private final TapType tapType;
    private final StopId stopId;
    private final String companyId;
    private final String busId;
    private final String pan;

}
