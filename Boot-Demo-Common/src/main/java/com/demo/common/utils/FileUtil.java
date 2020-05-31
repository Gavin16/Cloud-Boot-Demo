package com.demo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 对磁盘读写的工具类
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读取txt文件, 已字符串形式返回(保留换行符)
     */
    public static String readFile(String path){

        if(StringUtils.isEmpty(path)){
            return null;
        }

        File f = new File(path);

        try {
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader bufferedReader = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine())!= null){
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }

            return sb.toString();
        } catch (Exception e) {
            logger.error("FileUtil===readFile===文件读取异常:{}",e);
        }

        return null;
    }

}
