package com.littlepay.codetest.calculators;

import java.util.Date;
import java.util.Optional;
import com.littlepay.codetest.model.TapEntity;

public class DurationSecondsCal {
    public Long getDurationSecs(TapEntity startTap, Optional<TapEntity> endTap) {

        if (endTap.isEmpty()) {
            return null;
        }
        return (endTap.get().getDateTimeUtc().getTime() - startTap.getDateTimeUtc().getTime())/1000;
    }
}
