package com.sty.hello.utils;

import java.io.*;

public class JsonUtil {

    /**
     * 从JSON文件中读取数据
     * @param fileName
     * @return
     */
    public static String readJson(String fileName){
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            File jsonFile = new File(fileName);
            br = new BufferedReader(new FileReader(jsonFile));

            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 向文件中写入JSON数据
     * @param path
     * @param fileName
     * @param json
     */
    public static void writeJson(String path, String fileName, Object json) {
        BufferedWriter bw = null;
        File file = new File(path, fileName);
        //如果文件不存在，创建一个
        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入数据
        try {
            bw = new BufferedWriter((new FileWriter(file)));
            bw.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("写入成功");

    }
}
