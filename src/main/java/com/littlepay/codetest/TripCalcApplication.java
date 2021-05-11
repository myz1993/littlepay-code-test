package com.littlepay.codetest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.littlepay.codetest.model.TapEntity;
import com.littlepay.codetest.model.Trip;
import com.littlepay.codetest.parsers.TapParser;
import com.littlepay.codetest.parsers.TripOutputParser;
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
        List<Trip> allTripLists = tripProcessor.getAllTripLists();

        TripOutputParser tripOutputParser = new TripOutputParser();

        List<String[]> csvData = new ArrayList<>();

        allTripLists.stream().forEach(trip -> {
            String[] outPutTripSummary = new String[0];
            outPutTripSummary = tripOutputParser.parseToString(trip.getTripOutPut());
            csvData.add(outPutTripSummary);
        });

        try (CSVWriter writer = new CSVWriter(new FileWriter("trips.csv"))) {
            writer.writeAll(csvData);
        }

        csvReader.close();
    }
}

