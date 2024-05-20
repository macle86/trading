package io.runon.trading;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 일별 신용 융자 정보
 * 거래일 기준으로 2~4일 이전정보를 활용해야 한다.
 * 정확한 날짜기준은 확인이 필요.
 * @author macle
 */
@Data
public class CreditLoanDaily {

    //거래일
    Integer tradeYmd;
    Integer paymentYmd;

    BigDecimal loanNewQuantity;
    BigDecimal loanRepaymentQuantity;
    BigDecimal loanBalanceQuantity;
    BigDecimal loanNewAmount;
    BigDecimal loanRepaymentAmount;
    BigDecimal loanBalanceAmount;
    BigDecimal loanBalanceRate;
    BigDecimal loanTradeRate;

    BigDecimal close;
    BigDecimal open;
    BigDecimal high;
    BigDecimal low;
    BigDecimal volume;

    @Override
    public String toString(){
        return TradingGson.LOWER_CASE_WITH_UNDERSCORES.toJson(this);
    }

    public static CreditLoanDaily make(String jsonText){
        return TradingGson.LOWER_CASE_WITH_UNDERSCORES.fromJson(jsonText, CreditLoanDaily.class);
    }

}
