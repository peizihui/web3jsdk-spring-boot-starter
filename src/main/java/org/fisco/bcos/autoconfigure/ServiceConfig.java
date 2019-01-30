package org.fisco.bcos.autoconfigure;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.channel.handler.ChannelConnections;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;


@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceConfig {

    private String orgID;
    private Integer connectSeconds = 30;
    private Integer connectSleepPerMillis = 1;


    @Bean
    public Service getService(ChannelConnections channelConnections) {
    Service channelService= new Service();
    channelService.setConnectSeconds(connectSeconds);
    channelService.setOrgID(orgID);
    channelService.setConnectSleepPerMillis(connectSleepPerMillis);
    ConcurrentHashMap<String, ChannelConnections> allChannelConnections = new ConcurrentHashMap<>();
    allChannelConnections.put(orgID, channelConnections);
   channelService.setAllChannelConnections(allChannelConnections);
    return channelService;
    }


    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public Integer getConnectSleepPerMillis() {
        return connectSleepPerMillis;
    }

    public void setConnectSleepPerMillis(Integer connectSleepPerMillis) {
        this.connectSleepPerMillis = connectSleepPerMillis;
    }

    public void setConnectSeconds(Integer connectSeconds) {
        this.connectSeconds = connectSeconds;
    }
}
