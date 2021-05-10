package com.littlepay.codetest.model;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TapAndTripCombine {
    private TapEntity previousTapEntity;
    private List<Trip> trips;

    public void clearPreviousTapEntity() {
        this.previousTapEntity = null;
    }

    public void appendTrip(Trip trip) {
        this.trips.add(trip);
    }
}
