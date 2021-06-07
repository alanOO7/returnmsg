package com.el.returnmsg.utils;


import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsUtil {

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }



    /**
     * post方式请求服务器(https协议)
     * @param url
     *            请求地址
     * @param content
     *            参数
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static String post(String url, String content)
            throws NoSuchAlgorithmException, KeyManagementException,
            IOException {
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                new java.security.SecureRandom());
        String username = "admin";
        String password = "admin";
        byte[] token = (username + ":" + password).getBytes("utf-8");
//        String authorization = "Basic " + new String(Base64.encodeBase64(token), "utf-8");

        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.addRequestProperty("connection", "Keep-Alive");
        conn.addRequestProperty("Content-Type", "application/json; charset=utf-8");
//        conn.setRequestProperty("Authorization", authorization);
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes("utf-8"));
        // 刷新、关闭
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            is.close();
            if(outStream!=null){
                return new String(outStream.toByteArray(),"utf-8");
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String str=HttpsUtil.post("https://eng.sycommercial.com:8090/prod-api/warehouse/CmSendFee/bulkUpdate", "{\"operateTime\":\"2021-04-26 17:01:10\",\"operationType\":0,\"pids\":[2483,2483,2483,2209,2209,2203,2203,2203,2147,2147,2147,2213,2213,2213,2167,2167,2167,2207,2181,2181,2181,2211,2211,2121,2143,2143,2143,2113,2113,2163,2163,2163,2151,2151,2151,2189,2117,2159,2159,2159,2129,2129,2129,2155,2233,2233,2233,2241,2241,2241,2125,2125,2125,2193,2193,2193,2201,2201,2201,2185,2185,2185,2177,2221,2221,2221,2237,2237,2237,2103,2197,2197,2197,2133,2133,2133,2139,2139,2139,2245,2245,2245,2229,2229,2229,2171,2171,2171,2173,2107,2107,2107,2109,2109,2109,2223,2223,2223,2479,2485,2485,2485]}");
            System.out.println(str);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
