package com.littlepay.codetest;

import java.util.*;
import com.littlepay.codetest.model.*;
import lombok.Getter;

@Getter
public class TripProcessor {
    private Map<String, List<Trip>> map;

    public TripProcessor() {
        this.map = new HashMap<>();
    }

    public void process(TapEntity currentTapEntity) {

        if(this.map.get(currentTapEntity.getPan()) == null) {
            Trip trip = new Trip();
            trip.setStartTap(currentTapEntity);
            trip.setEndTap(Optional.empty());
            List<Trip> tripList = new ArrayList<Trip>();
            tripList.add(trip);
            this.map.put(currentTapEntity.getPan(), tripList);

            return;
        }

        List<Trip> currentTripList = this.map.get(currentTapEntity.getPan());

        if (currentTapEntity.getTapType().equals(TapType.ON)) {
            Trip trip = new Trip();
            trip.setStartTap(currentTapEntity);
            trip.setEndTap(Optional.empty());
            currentTripList.add(trip);
        } else if (currentTapEntity.getTapType().equals(TapType.OFF)) {
            currentTripList.get(currentTripList.size() - 1).setEndTap(Optional.ofNullable(currentTapEntity));
        }
    }

    public List<Trip> getAllTripLists() {
        List<Trip> tripListSummary = new ArrayList<>();

        for (List<Trip> value : this.map.values()) {
           value.stream().forEach(trip -> {
               tripListSummary.add(trip);
           });
        }

        return tripListSummary;
    }

}


