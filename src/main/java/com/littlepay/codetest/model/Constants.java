package com.littlepay.codetest.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<String, HashMap<String, BigDecimal>> PRICE_MAP = Map.of(
            "STOP1", new HashMap() {{
                put("STOP1", BigDecimal.valueOf(0));
                put("STOP2", BigDecimal.valueOf(3.25));
                put("STOP3", BigDecimal.valueOf(7.30));
            }},
            "STOP2", new HashMap() {{
                put("STOP1", BigDecimal.valueOf(3.25));
                put("STOP2", BigDecimal.valueOf(0));
                put("STOP3", BigDecimal.valueOf(5.50));
            }},
            "STOP3", new HashMap() {{
                put("STOP1", BigDecimal.valueOf(7.30));
                put("STOP2", BigDecimal.valueOf(5.50));
                put("STOP3", BigDecimal.valueOf(0));
            }}
    );
}
