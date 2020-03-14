package util;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Random;

/**
 * @author 失去同步
 */
public class Util {

    private static final int MAX_URL_NUM = 6;
    private static String[] urls = {
            "http://www.baidu.com",
            "http://www.taobao.com",
            "http://www.360.cn",
            "http://time.tianqi.com",
            "http://tv.cctv.com"
    };
    private static Random random = new Random();
    private static URL url;
    private static URLConnection uc;
    private static int urlNum = 0;

    public static Date getInternetTime() {
        Long ld = null;
        //System.out.println("urlNum = " + urlNum);
        try {
            if (urlNum <= MAX_URL_NUM){
                url = new URL(urls[random.nextInt(urls.length)]);
                uc = url.openConnection();
                urlNum++;
                uc.connect();
                ld = uc.getDate();
                //System.out.println("ld = " + ld+"  url = "+url.getHost());
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (urlNum > MAX_URL_NUM / 2){
                exceptionDialog(e, null);
            }
        }
        urlNum--;
        return ld == null ? null : new Date(ld);
    }

    public static void exceptionDialog(Exception e, Component parent) {
        JOptionPane.showMessageDialog(parent, "错误: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    @Test
    public void testTime() {
        for (String s : urls) {
            long start, end;
            start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                try {
                    url = new URL(s);
                    uc = url.openConnection();
                    uc.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            end = System.currentTimeMillis();
            System.out.println(s + ":" + (end - start) + "ms");
        }
    }

}
