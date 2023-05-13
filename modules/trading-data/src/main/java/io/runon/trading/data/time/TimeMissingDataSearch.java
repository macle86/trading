package io.runon.trading.data.time;

import com.seomse.commons.callback.StrCallback;
import com.seomse.commons.utils.time.Times;
import io.runon.trading.BeginEndTime;
import io.runon.trading.BeginEndTimeCallback;
import io.runon.trading.TradingTimes;
import io.runon.trading.data.file.TimeName;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macle
 */
public class TimeMissingDataSearch {

    private final String dirPath;
    private final TimeLine timeLine;
    private final TimeName.Type timeNameType;

    public TimeMissingDataSearch(String dirPath
            , TimeLine timeLine
            , TimeName.Type timeNameType){
        this.dirPath = dirPath;
        this.timeLine = timeLine;
        this.timeNameType = timeNameType;
    }

    private ZoneId zoneId = TradingTimes.UTC_ZONE_ID;

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    //당장 상
    private long missingTime = 7000L;
    public void setMissingTime(long missingTime) {
        this.missingTime = missingTime;
    }


    //다음버젼에서 처리 휴일 및 거래불가 시간 당장에는 암호화폐에서 사용하므로 휴일이 없음.
    //국가별 타임존 작업과 관련이 있음. off 타임존과 off 시간범위 설정기능 추가 (시 분)
//    private DayOfWeek [] offDays = null;
//    public void setOffDays(DayOfWeek[] offDays) {
//        this.offDays = offDays;
//    }
//
//    public void setOffDayWeekend() {
//        offDays = new DayOfWeek[]{
//                DayOfWeek.SATURDAY
//                , DayOfWeek.SUNDAY
//        };
//    }


    public void search(BeginEndTimeCallback callback){
        search(-1, System.currentTimeMillis(), callback);
    }

    public void search(long beginTime, BeginEndTimeCallback callback){
        search(beginTime, System.currentTimeMillis(), callback);
    }

    public BeginEndTime [] search(long beginTime, long endTime){

        final List<BeginEndTime> list = new ArrayList<>();

        BeginEndTimeCallback callback = list::add;

        search(beginTime, endTime, callback);

        if(list.size() == 0){
            return BeginEndTime.EMPTY_ARRAY;
        }

        BeginEndTime [] array = list.toArray(new BeginEndTime[0]);
        list.clear();

        return array;
    }

    public void search(long beginTime, long endTime, final BeginEndTimeCallback callback){

        StrCallback strCallback = new StrCallback() {
            long lastTime = -1;

            @Override
            public void callback(String str) {
                long time = timeLine.getTime(str);
                if(lastTime < 0){

                }
            }
        };


        TimeLines.load(dirPath, zoneId, beginTime, endTime, timeNameType, timeLine, strCallback);

    }

    public static void main(String[] args) {
        DayOfWeek [] offDay = new DayOfWeek[]{
                DayOfWeek.SATURDAY
                , DayOfWeek.SUNDAY
        };

        for(DayOfWeek d : offDay){
            System.out.println(d);
        }
    }
}
