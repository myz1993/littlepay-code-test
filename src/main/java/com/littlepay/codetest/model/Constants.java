package com.littlepay.codetest.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<String, HashMap<String, BigDecimal>> PRICE_MAP = Map.of(
            "Stop1", new HashMap() {{
                put("Stop1", BigDecimal.valueOf(0));
                put("Stop2", BigDecimal.valueOf(3.25));
                put("Stop3", BigDecimal.valueOf(7.30));
            }},
            "Stop2", new HashMap() {{
                put("Stop1", BigDecimal.valueOf(3.25));
                put("Stop2", BigDecimal.valueOf(0));
                put("Stop3", BigDecimal.valueOf(5.50));
            }},
            "Stop3", new HashMap() {{
                put("Stop1", BigDecimal.valueOf(7.30));
                put("Stop2", BigDecimal.valueOf(5.50));
                put("Stop3", BigDecimal.valueOf(0));
            }}
    );
}
