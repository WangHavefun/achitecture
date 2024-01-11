package edu.ynu.se.xiecheng.achitectureclass.common.config;

import java.time.Duration;

public interface Properties {
    String cookieToken = "token";

//    int cookieTokenExpire = 3600 * 24 * 7; // 7天

    Duration cookieTokenExpire = Duration.ofDays(7);
}
