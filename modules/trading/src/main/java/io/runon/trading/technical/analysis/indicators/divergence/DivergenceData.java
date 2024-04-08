package io.runon.trading.technical.analysis.indicators.divergence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DivergenceData {
    long time;
    DivergenceType divergenceType = DivergenceType.NONE;
    DivergenceUpDownType divergenceUpDownType = DivergenceUpDownType.NONE;

    BigDecimal priceChange = new BigDecimal("0.0");
    BigDecimal compareChange = new BigDecimal("0.0");
    int divergenceLength = 0;
}
