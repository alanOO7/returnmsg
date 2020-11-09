package com.el.returnmsg.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alan on 2020/10/12 13:59
 */
public class RestfulWithoutPW {

    /**
     *
     *
     * @param url
     * @param param
     * @return
     */
    public static String getRestfulWithoutPWResult(String url, String param){
        String outputstr = null;


        try {

            String username = "admin";
            String password = "admin";
            byte[] token = (username + ":" + password).getBytes("utf-8");
            String authorization = "Basic " + new String(Base64.encodeBase64(token), "utf-8");


            URL targetUrl = new URL(url);

            HttpURLConnection httpConnection =
                    (HttpURLConnection)targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Charset", "UTF-8");
            httpConnection.setRequestProperty("Authorization",authorization);


            httpConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(param.getBytes("utf-8"));
            System.out.println(outputStream);
            outputStream.flush();
            httpConnection.connect();
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " +
                        httpConnection.getResponseCode());
            }

            //解决返回值中文乱码问题
            InputStreamReader isr = new InputStreamReader((httpConnection.getInputStream()), "UTF-8");
            BufferedReader responseBuffer = new BufferedReader(isr);

           /*  BufferedReader responseBuffer =
                new BufferedReader(new InputStreamReader((httpConnection.getInputStream()))); */

            outputstr = responseBuffer.readLine();

            httpConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputstr;
    }
}
