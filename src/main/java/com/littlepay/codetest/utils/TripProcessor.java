package com.littlepay.codetest.utils;

import java.text.SimpleDateFormat;
import java.util.*;
import com.littlepay.codetest.model.*;

public class TripProcessor {
    Map<String, TapAndTripCombine> map;

    public TripProcessor() {
        this.map = new HashMap<>();
    }

    public void process(TapEntity currentTapEntity) {
        if(this.map.get(currentTapEntity.getPan()) == null) {
            TapAndTripCombine tapAndTripCombine = new TapAndTripCombine();
            tapAndTripCombine.setPreviousTapEntity(currentTapEntity);
            tapAndTripCombine.setTrips(new ArrayList<Trip>());
            this.map.put(currentTapEntity.getPan(), tapAndTripCombine);

            return;
        }

        TapAndTripCombine tapAndTripCombine = this.map.get(currentTapEntity.getPan());

        if (tapAndTripCombine.getPreviousTapEntity() == null) {
            tapAndTripCombine.setPreviousTapEntity(currentTapEntity);
            return;
        }

        if (currentTapEntity.getTapType().equals("OFF")) {
            TapEntity previousTapEntity = tapAndTripCombine.getPreviousTapEntity();

            Long timeDuration = (currentTapEntity.getDateTimeUtc().getTime() - previousTapEntity.getDateTimeUtc().getTime())/1000;


            Trip trip = Trip.builder()
                    .startTime(previousTapEntity.getDateTimeUtc())
                    .finishTime(currentTapEntity.getDateTimeUtc())
                    .durationSecs(timeDuration)
                    .companyId(currentTapEntity.getCompanyId())
                    .busId(currentTapEntity.getBusId())
                    .fromStopId(previousTapEntity.getStopId())
                    .toStopId(currentTapEntity.getStopId())
                    .chargedAmount(Constants.PRICE_MAP.get(previousTapEntity.getStopId()).get(currentTapEntity.getStopId()))
                    .pan(currentTapEntity.getPan())
                    .status(previousTapEntity.getStopId().equals(currentTapEntity.getStopId()) ? "CANCELED" : "COMPLETED")
                    .build();
            tapAndTripCombine.appendTrip(trip);
            tapAndTripCombine.clearPreviousTapEntity();
        } else if (currentTapEntity.getTapType().equals("ON")) {
            TapEntity previousTapEntity = tapAndTripCombine.getPreviousTapEntity();
            Trip trip = Trip.builder()
                    .startTime(previousTapEntity.getDateTimeUtc())
                    .finishTime(null)
                    .durationSecs(null)
                    .companyId(currentTapEntity.getCompanyId())
                    .busId(currentTapEntity.getBusId())
                    .fromStopId(previousTapEntity.getStopId())
                    .toStopId(null)
                    .chargedAmount(Collections.max(Constants.PRICE_MAP.get(previousTapEntity.getStopId()).values()))
                    .pan(currentTapEntity.getPan())
                    .status("INCOMPLETED")
                    .build();
            tapAndTripCombine.appendTrip(trip);
            tapAndTripCombine.clearPreviousTapEntity();
            tapAndTripCombine.setPreviousTapEntity(currentTapEntity);
        }
    }

    private void processTheLastTapOnRecord() {
        for (TapAndTripCombine value : this.map.values()) {
            if (value.getPreviousTapEntity() != null) {
                TapEntity previousTapEntity = value.getPreviousTapEntity();
                Trip trip = Trip.builder()
                        .startTime(previousTapEntity.getDateTimeUtc())
                        .finishTime(null)
                        .durationSecs(null)
                        .companyId(previousTapEntity.getCompanyId())
                        .busId(previousTapEntity.getBusId())
                        .fromStopId(previousTapEntity.getStopId())
                        .toStopId(null)
                        .chargedAmount(Collections.max(Constants.PRICE_MAP.get(previousTapEntity.getStopId()).values()))
                        .pan(previousTapEntity.getPan())
                        .status("INCOMPLETED")
                        .build();
                value.appendTrip(trip);
                value.clearPreviousTapEntity();
                value.setPreviousTapEntity(previousTapEntity);
            }
        }
    }

    public List<TripEntity> getTripEntities() {
        this.processTheLastTapOnRecord();

        List<TripEntity> tripEntities = new ArrayList<>();

        for (TapAndTripCombine value : this.map.values()) {
            value.getTrips().stream().forEach((trip -> {
                TripEntity tripEntity = TripEntity.builder()
                        .startTime(trip.getStartTime() == null ? "null" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(trip.getStartTime()))
                        .finishTime(trip.getFinishTime() == null ? "null" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(trip.getFinishTime()))
                        .durationSecs(trip.getDurationSecs())
                        .fromStopId(trip.getFromStopId())
                        .toStopId(trip.getToStopId() == null ? "null" : trip.getToStopId())
                        .chargedAmount(trip.getChargedAmount())
                        .companyId(trip.getCompanyId())
                        .busId(trip.getBusId())
                        .pan(trip.getPan())
                        .status(trip.getStatus())
                        .build();
                tripEntities.add(tripEntity);
            }));
        }
        return tripEntities;
        }

}
