package com.littlepay.codetest.parsers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import com.littlepay.codetest.model.TripOutput;

public class TripOutputParser {

    private String formatDateTime(Date dateOrNull) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return (dateOrNull == null ? null : dateFormat.format(dateOrNull));
    }

    public String[] parseToString(TripOutput tripOutput) {
        if (Objects.isNull(tripOutput)) {
            return null;
        }

        String[] outPutTripSummary = {
                formatDateTime(tripOutput.getStartTime()),
                formatDateTime(tripOutput.getFinishTime()),
                Objects.toString(tripOutput.getDurationSecs()),
                Objects.toString(tripOutput.getFromStopId()),
                Objects.toString(tripOutput.getToStopId()),
                Objects.toString(tripOutput.getChargedAmount()),
                tripOutput.getCompanyId(),
                tripOutput.getBusId(),
                tripOutput.getPan(),
                Objects.toString(tripOutput.getStatus())
        };

        return outPutTripSummary;
    }
}
