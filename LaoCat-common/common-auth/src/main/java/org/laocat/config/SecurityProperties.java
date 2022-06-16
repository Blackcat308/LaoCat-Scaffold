package org.laocat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description jwt配置类
 */
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class SecurityProperties {
    /**
     * 秘钥
     */
    private String secret;
    /**
     * 有效时长 （秒）
     */
    private Long effectiveTime;
    /**
     * token 唯一标记
     */
    private String uniqueTag;
    /**
     * token 自动刷新剩余时间
     */
    private Long autoRefreshRemainingTime;
    /**
     * 允许多人同账号同时在线
     */
    private boolean allowMultiplePeople;
    /**
     * GET请求是否需要进行Authentication请求头校验，true：默认校验；false：不拦截GET请求
     */
    private boolean dischargedGetMethod;
    /**
     * 忽略授权的路径
     */
    private List<String> ignoreUrl;

    public SecurityProperties() {
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getUniqueTag() {
        return uniqueTag;
    }

    public void setUniqueTag(String uniqueTag) {
        this.uniqueTag = uniqueTag;
    }

    public Long getAutoRefreshRemainingTime() {
        return autoRefreshRemainingTime;
    }

    public void setAutoRefreshRemainingTime(Long autoRefreshRemainingTime) {
        this.autoRefreshRemainingTime = autoRefreshRemainingTime;
    }

    public boolean isAllowMultiplePeople() {
        return allowMultiplePeople;
    }

    public void setAllowMultiplePeople(boolean allowMultiplePeople) {
        this.allowMultiplePeople = allowMultiplePeople;
    }

    public boolean isDischargedGetMethod() {
        return dischargedGetMethod;
    }

    public void setDischargedGetMethod(boolean dischargedGetMethod) {
        this.dischargedGetMethod = dischargedGetMethod;
    }

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    @Override
    public String toString() {
        return "SecurityProperties{" +
                "secret='" + secret + '\'' +
                ", effectiveTime=" + effectiveTime +
                ", uniqueTag='" + uniqueTag + '\'' +
                ", autoRefreshRemainingTime=" + autoRefreshRemainingTime +
                ", allowMultiplePeople=" + allowMultiplePeople +
                ", dischargedGetMethod=" + dischargedGetMethod +
                ", ignoreUrl=" + ignoreUrl +
                '}';
    }
}
