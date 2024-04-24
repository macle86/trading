package io.runon.trading.technical.analysis.indicators.market.mv;

import io.runon.trading.TimeNumber;
import io.runon.trading.TimeNumberData;
import io.runon.trading.technical.analysis.candle.Candles;
import io.runon.trading.technical.analysis.candle.IdCandles;
import io.runon.trading.technical.analysis.candle.IdCandleTimes;
import io.runon.trading.technical.analysis.candle.TradeCandle;
import io.runon.trading.technical.analysis.indicators.market.MarketIndicators;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Market Volume Power
 * 0~ 500
 * 최대치 값은 max.volume.power 설정에 의해 달라질 수 있음
 * @author macle
 */
public class Mvp extends MarketIndicators<TimeNumber> {


    public Mvp(IdCandles[] idCandles){
        super(idCandles);
        scale = 2;
    }
    public Mvp(IdCandleTimes idCandleTimes){
        super(idCandleTimes);
        scale = 2;
    }

    @Override
    public TimeNumber getData(int index) {

        long time = times[index];

        int validSymbolCount = 0;

        int searchLength = searchIndex(index);

        BigDecimal sum = BigDecimal.ZERO;

        for(IdCandles symbolCandle : idCandles){

            TradeCandle[] candles = symbolCandle.getCandles();

            int openTimeIndex = Candles.getOpenTimeIndex(candles, time, searchLength);
            if(openTimeIndex == -1){
                continue;
            }

            BigDecimal vp = candles[openTimeIndex].getVolumePower();
            if(vp == null){
                continue;
            }
            sum = sum.add(vp);
            validSymbolCount++;

        }

        if(validSymbolCount == 0 || sum.compareTo(BigDecimal.ZERO) == 0){
            return new TimeNumberData(time, BigDecimal.ZERO);
        }

        return new TimeNumberData(time, sum.divide(new BigDecimal(validSymbolCount), scale, RoundingMode.HALF_UP));
    }

    @Override
    public TimeNumber[] newArray(int startIndex, int end) {
        TimeNumber[] array = new TimeNumber[end - startIndex];

        for (int i = 0; i < array.length ; i++) {
            array[i] = getData(i + startIndex);
        }
        return array;
    }
}
