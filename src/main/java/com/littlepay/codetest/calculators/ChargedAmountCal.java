package com.littlepay.codetest.calculators;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import com.littlepay.codetest.model.Constants;
import com.littlepay.codetest.model.TapEntity;

public class ChargedAmountCal {
    public BigDecimal getChargedAmount(TapEntity startTap, Optional<TapEntity> endTap) {

        if (endTap.isPresent()) {
            return Constants.PRICE_MAP.get(startTap.getStopId().toString()).get(endTap.get().getStopId().toString());
        }

        return Collections.max(Constants.PRICE_MAP.get(startTap.getStopId().toString()).values());
    }
}
