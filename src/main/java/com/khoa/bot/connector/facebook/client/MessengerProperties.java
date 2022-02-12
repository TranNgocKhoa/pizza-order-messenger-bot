package com.khoa.bot.connector.facebook.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "messenger")
public class MessengerProperties {
    private String appAccessToken;
    private String appSecret;
    private String host;
    private String version;

    public String getAppAccessToken() {
        return appAccessToken;
    }

    public void setAppAccessToken(String appAccessToken) {
        this.appAccessToken = appAccessToken;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBaseUrl() {
        return this.getHost().replace("{version}", this.getVersion());
    }

    public String getUrl() {
        return this.getHost().replace("{version}", this.getVersion()).replace("{appAccessToken}", this.getAppAccessToken());
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
