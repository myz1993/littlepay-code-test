package com.littlepay.codetest.parsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import com.littlepay.codetest.model.StopId;
import com.littlepay.codetest.model.TapEntity;
import com.littlepay.codetest.model.TapType;

public class TapParser {

    private static final String SEPARATOR = ",";

    public TapEntity parseToTapEntity(String line) throws ParseException {
        if (Objects.isNull(line) || line.trim().length() == 0) {
            return null;
        }

        String[] itemAttrs = line.split(SEPARATOR);

        if (itemAttrs.length != 7) {
            throw new IllegalArgumentException("Item format is not correct: " + line);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = formatter.parse(itemAttrs[1]);

        return new TapEntity(Integer.parseInt(itemAttrs[0]), date, TapType.valueOf(itemAttrs[2].trim()), StopId.valueOf(itemAttrs[3].trim()), itemAttrs[4].trim(), itemAttrs[5].trim(), itemAttrs[6].trim());
    }
}
