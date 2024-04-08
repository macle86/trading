package io.runon.trading.technical.analysis.indicators.stochastic;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import io.runon.trading.TimeNumber;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 스토캐스틱 결과 데이터
 * @author macle
 */
@Data
public class StochasticData implements TimeNumber {

    long time;
    BigDecimal k;
    BigDecimal d;
    BigDecimal slowD;

    @Override
    public String toString(){
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create().toJson(this);
    }

    @Override
    public BigDecimal getNumber() {
        return d;
    }
}
