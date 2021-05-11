package com.littlepay.codetest.model;

import java.util.Optional;
import com.littlepay.codetest.calculators.ChargedAmountCal;
import com.littlepay.codetest.calculators.DurationSecondsCal;
import com.littlepay.codetest.calculators.StatusCal;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Trip {
    private TapEntity startTap;
    private Optional<TapEntity> endTap;

    public TripOutput getTripOutPut() {
        DurationSecondsCal durationSecondsCal = new DurationSecondsCal();
        ChargedAmountCal chargedAmountCal = new ChargedAmountCal();
        StatusCal statusCal = new StatusCal();

        TripOutput tripOutput = TripOutput.builder()
                .startTime(startTap.getDateTimeUtc())
                .finishTime(endTap.isPresent()? endTap.get().getDateTimeUtc() : null)
                .durationSecs(durationSecondsCal.getDurationSecs(startTap, endTap))
                .companyId(startTap.getCompanyId())
                .busId(startTap.getBusId())
                .fromStopId(startTap.getStopId())
                .toStopId(endTap.isPresent()? endTap.get().getStopId() : null)
                .chargedAmount(chargedAmountCal.getChargedAmount(startTap, endTap))
                .pan(startTap.getPan())
                .status(statusCal.getTripStatus(startTap, endTap))
                .build();

        return tripOutput;
    }
}
