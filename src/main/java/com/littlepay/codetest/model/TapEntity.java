package com.littlepay.codetest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TapEntity {
    private final int id;
    private final Date dateTimeUtc;
    private final String tapType;
    private final String stopId;
    private final String companyId;
    private final String BusId;
    private final String Pan;

}
