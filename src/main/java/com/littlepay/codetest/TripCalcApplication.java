package com.littlepay.codetest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.littlepay.codetest.model.TapEntity;
import com.littlepay.codetest.model.TripEntity;
import com.littlepay.codetest.utils.TripProcessor;
import com.opencsv.CSVWriter;
import java.io.FileWriter;

public class TripCalcApplication{
    public static void main(String[] args) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("taps.csv"));
        String line;

        TripProcessor tripProcessor = new TripProcessor();

        while ((line = csvReader.readLine()) != null) {
            try{
                TapEntity currentTapEntity = new TapParser().parseToTapEntity(line);
                tripProcessor.process(currentTapEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<TripEntity> tripEntities = tripProcessor.getTripEntities();

        List<String[]> csvData = new ArrayList<>();
        TripEntityParser tripEntityParser = new TripEntityParser();
        tripEntities.stream().forEach(tripEntity -> {
            try {
                String[] outPutTripSummary = tripEntityParser.parseToString(tripEntity);
                csvData.add(outPutTripSummary);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        try (CSVWriter writer = new CSVWriter(new FileWriter("trips.csv"))) {
            writer.writeAll(csvData);
        }


        csvReader.close();
    }
}

