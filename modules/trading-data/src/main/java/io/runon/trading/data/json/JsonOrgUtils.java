package io.runon.trading.data.json;

import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * org.json 그룹을 사용할때 데이터 처리용 유틸성 클래스
 * @author macle
 */
public class JsonOrgUtils {

    public static BigDecimal getBigDecimal(JSONObject object, String key){
        if(object.isNull(key)){
            return null;
        }
        Object data = object.get(key);
        if(data.getClass() == java.lang.String.class){
            String str = (String)data;
            str = str.replace(",","").replace("-","").replace("_","").replace(" ", "").trim();
            if("".equals(str)){
               return null;
            }

            return new BigDecimal(str);


        }else{
            return new BigDecimal(data.toString());
        }

    }

}
