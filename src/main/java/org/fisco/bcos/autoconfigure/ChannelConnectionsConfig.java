package org.fisco.bcos.autoconfigure;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;
import org.fisco.bcos.channel.handler.ChannelConnections;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "channelConnections")
public class ChannelConnectionsConfig {

    List<ChannelConnections> groupAndConnectstr;

    @Bean
     public ChannelConnections getChannelConnection() {
        ChannelConnections channelConnections = new ChannelConnections();
        channelConnections.setConnectionsStr(connectionsStr);
        channelConnections.setGroupId(groupId);
        return channelConnections;
    }


    public void setConnectionsStr(List<String> connectionsStr) {
        this.connectionsStr = connectionsStr;
    }
}
