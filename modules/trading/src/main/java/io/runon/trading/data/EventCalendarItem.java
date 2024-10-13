package io.runon.trading.data;

import com.seomse.jdbc.annotation.Column;
import com.seomse.jdbc.annotation.Table;
import com.seomse.jdbc.annotation.PrimaryKey;
import com.seomse.jdbc.annotation.DateTime;
import io.runon.trading.TradingGson;

/**
 * @author macle
 */
@Table(name="event_calendar_item")
public class EventCalendarItem {

    @PrimaryKey(seq = 1)
    @Column(name = "event_id")
    String eventId;

    @PrimaryKey(seq = 2)
    @Column(name = "item_type")
    String itemType;

    @PrimaryKey(seq = 3)
    @Column(name = "item_id")
    String itemId;

    @DateTime
    @Column(name = "updated_at")
    Long updatedAt;

    @Override
    public String toString(){
        return TradingGson.LOWER_CASE_WITH_UNDERSCORES_PRETTY.toJson(this);
    }
}
