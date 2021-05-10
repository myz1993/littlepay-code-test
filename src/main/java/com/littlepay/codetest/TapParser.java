package com.littlepay.codetest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import com.littlepay.codetest.model.TapEntity;

public class TapParser {
    private static final String SEPARATOR = ",";

    public TapEntity parseToTapEntity(String line) throws ParseException {
        if (Objects.isNull(line) || line.trim().length() == 0) {
            //返回null
            return null;
        }

        String[] itemAttrs = line.split(SEPARATOR);

        if (itemAttrs.length != 7) {
            //抛出异常
            throw new IllegalArgumentException("Item format is not correct: " + line);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = formatter.parse(itemAttrs[1]);


        return new TapEntity(Integer.parseInt(itemAttrs[0]), date, itemAttrs[2].trim(), itemAttrs[3].trim(), itemAttrs[4].trim(), itemAttrs[5].trim(), itemAttrs[6].trim());
    }
}
