/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.runon.trading.view.util;

import com.seomse.commons.exception.IORuntimeException;

import java.io.*;
/**
 * @author ccsweets
 */
public class JarUtil {
    /**
     * Jar 파일에서 파일을 읽는다
     * @param filename file name
     * @return file contents
     */
    public static String readFromJarFile(String filename) {
        try {
            InputStream is = JarUtil.class.getClassLoader().getResourceAsStream(filename);
            if(is == null){
                throw new IORuntimeException();
            }

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            isr.close();
            is.close();
            return sb.toString();
        }catch(IOException e){
            throw new IORuntimeException(e);
        }
    }

    /**
     * Jar 파일 내부의 파일을 copy 한다
     * @param filename copy할 파일명
     * @param copyPath paste할 경로

     */
    public static void copyFromJarFile(String filename,String copyPath) {
        try {
            InputStream is = JarUtil.class.getClassLoader().getResourceAsStream(filename);
            if(is == null){
                throw new IORuntimeException();
            }

            byte[] buffer = new byte[is.available()];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            File targetFile = new File(copyPath + "/" + filename);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        }catch(IOException e){
            throw new IORuntimeException(e);
        }
    }
}
