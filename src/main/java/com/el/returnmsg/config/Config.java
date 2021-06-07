package com.el.returnmsg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Alan on 2020/10/12 14:25
 */
@EnableAutoConfiguration
@Configuration
public class Config {
    private static String returnMsg;

    public String getReturnMsg() {
        return returnMsg;
    }

    @Value("${url.returnMsg}")
    public void setReturnMsg(String returnMsg) {
        Config.returnMsg = returnMsg;
    }
}
