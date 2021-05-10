package com.littlepay.codetest;

import java.text.ParseException;
import java.util.Objects;
import com.littlepay.codetest.model.TripEntity;

public class TripEntityParser {
    public String[] parseToString(TripEntity tripEntity) throws ParseException {
        if (Objects.isNull(tripEntity)) {
            //返回nulls
            return null;
        }

        String[] outPutTripSummary = {
                tripEntity.getStartTime(),
                tripEntity.getFinishTime(),
                Objects.toString(tripEntity.getDurationSecs()),
                tripEntity.getFromStopId(),
                tripEntity.getToStopId(),
                tripEntity.getChargedAmount().toString(),
                tripEntity.getCompanyId(),
                tripEntity.getBusId(),
                tripEntity.getPan(),
                tripEntity.getStatus()
        };

        return outPutTripSummary;
    }
}
