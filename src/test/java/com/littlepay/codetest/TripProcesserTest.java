package com.littlepay.codetest;

import java.util.*;
import com.littlepay.codetest.model.StopId;
import com.littlepay.codetest.model.TapEntity;
import com.littlepay.codetest.model.TapType;
import com.littlepay.codetest.model.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.hamcrest.collection.IsMapContaining;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripProcesserTest {
    TripProcessor tripProcessor = new TripProcessor();

    TapEntity mockTapEntity1 =  TapEntity.builder()
            .dateTimeUtc(new Date())
            .tapType(TapType.ON)
            .busId("34")
            .companyId("2")
            .id(1)
            .pan("mockpan")
            .stopId(StopId.STOP1)
            .build();

    TapEntity mockTapEntity2 =  TapEntity.builder()
            .dateTimeUtc(new Date())
            .tapType(TapType.ON)
            .busId("34")
            .companyId("2")
            .id(1)
            .pan("mockpan")
            .stopId(StopId.STOP2)
            .build();

    TapEntity mockTapEntity3 = TapEntity.builder()
            .dateTimeUtc(new Date())
            .tapType(TapType.OFF)
            .busId("34")
            .companyId("2")
            .id(1)
            .pan("mockpan")
            .stopId(StopId.STOP3)
            .build();

    @Test
    void shouldGetTripMapGivenTapEntities() {

        List<TapEntity> tapEntities = new ArrayList<>();
        tapEntities.add(mockTapEntity1);
        tapEntities.add(mockTapEntity2);
        tapEntities.add(mockTapEntity3);

        List<Trip> tripList = new ArrayList<>(
                Arrays.asList(
                        Trip.builder()
                                .endTap(Optional.empty())
                                .startTap(mockTapEntity1)
                                .build(),
                        Trip.builder()
                                .endTap(Optional.ofNullable(mockTapEntity3))
                                .startTap(mockTapEntity2)
                                .build()
                )
        );

        tapEntities.stream().forEach(tapEntity -> {
            tripProcessor.process(tapEntity);
        });
        assertThat(tripProcessor.getMap(), IsMapContaining.hasKey("mockpan"));
        assertThat(tripProcessor.getMap(), IsMapContaining.hasValue(tripList));
    }

    @Test
    void shouldReturnAllTripLists() {
        List<TapEntity> tapEntities = new ArrayList<>();
        tapEntities.add(mockTapEntity1);
        tapEntities.add(mockTapEntity2);
        tapEntities.add(mockTapEntity3);
        tapEntities.stream().forEach(tapEntity -> {
            tripProcessor.process(tapEntity);
        });
        List<Trip> tripList = tripProcessor.getAllTripLists();
        assertEquals(2, tripList.size());
    }
}
