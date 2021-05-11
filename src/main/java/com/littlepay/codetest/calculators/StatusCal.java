package com.littlepay.codetest.calculators;

import java.util.Optional;
import com.littlepay.codetest.model.TapEntity;
import com.littlepay.codetest.model.TapType;
import com.littlepay.codetest.model.TripStatus;

public class StatusCal {
    public TripStatus getTripStatus(TapEntity startTap, Optional<TapEntity> endTap) {

        if (endTap.isEmpty()) {
            return TripStatus.INCOMPLETED;
        }

        if (startTap.getStopId().toString().equals(endTap.get().getStopId().toString())) {
            return TripStatus.CANCELED;
        }

        return TripStatus.COMPLETED;
    }
}
