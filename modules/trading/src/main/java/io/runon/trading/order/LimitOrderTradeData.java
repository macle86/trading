package io.runon.trading.order;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import io.runon.trading.Trade;
import io.runon.trading.TradingGson;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 지정가 주문 매매 정보 구현체
 * @author macle
 */
@Data
public class LimitOrderTradeData implements LimitOrderTrade{


    protected String orderId;
    protected Trade.Type tradeType;
    protected BigDecimal price;
    protected BigDecimal openQuantity;
    protected BigDecimal closeQuantity;
    protected BigDecimal fee;


    protected long orderTime =-1;
    protected long closeTime =-1;

    @Override
    public String toString(){
        return TradingGson.LOWER_CASE_WITH_UNDERSCORES_PRETTY.toJson(this);
    }

}
