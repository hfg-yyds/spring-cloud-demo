package com.hfg.utils;

import lombok.SneakyThrows;

import java.io.*;

/**
 * @Author: Zero
 * @Date: 2022/4/1 12:31
 * @Description:
 */
public class ImagesUtils {
    public static void main(String[] args) {
        byte[] imgStr = getImgStr("D:\\text.txt");
        for (int i = 0; i < imgStr.length; i++) {

            System.out.println(imgStr[i]);
        }
    }

    @SneakyThrows
    public static String getTextStr(String textFilePath) {
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader(textFilePath));
        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }
        bf.close();
        String xml = buffer.toString();
        return xml;
    }
    /**
     * 将一个图片文件转化为字节数组
     * @param imgFile
     * @return
     */
    public static byte[] getImgStr(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return data;
//        return new String(Base64.encodeBase64(data));
    }
}
