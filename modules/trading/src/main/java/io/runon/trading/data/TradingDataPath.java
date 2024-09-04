package io.runon.trading.data;

import io.runon.trading.TradingConfig;

import java.nio.file.FileSystems;

/**
 * @author macle
 */
public class TradingDataPath {

    public static String getAbsolutePath(String dirPath){

        dirPath = changeSeparator(dirPath);

        String dataPth = TradingConfig.getTradingDataPath();

        if(dirPath.startsWith(dataPth)){
            return dirPath;
        }else{
            String fileSeparator = FileSystems.getDefault().getSeparator();
            return  dataPth + fileSeparator + dirPath;
        }
    }

    public static String changeSeparator(String path){
        String fileSeparator = FileSystems.getDefault().getSeparator();

        if(fileSeparator.equals("\\")){
            return path.replace("/", "\\");
        }else{
            return path.replace("\\", "/");
        }
    }


    public static String getRelativePath(String path){
        path = changeSeparator(path);
        String dataPath = TradingConfig.getTradingDataPath();

        if(path.startsWith(dataPath)){
            path = path.substring(dataPath.length()+1);
        }

        return path;
    }


    public static String getDataPath(){
        return TradingConfig.getTradingDataPath();
    }

    public static void main(String[] args) {
        String path = getAbsolutePath("commodities\\futures\\candle\\GBR_brent_oil\\1h");
        System.out.println(path);


    }
}
