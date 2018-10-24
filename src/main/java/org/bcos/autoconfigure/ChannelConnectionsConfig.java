package org.bcos.autoconfigure;

import org.bcos.channel.handler.ChannelConnections;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "channelConnections")
public class ChannelConnectionsConfig {

    private String caCertPath = "classpath:ca.crt";
    private String clientKeystorePath = "classpath:client.keystore";
    private String keystorePassWord = "123456";
    private String clientCertPassWord = "123456";
    private List<String> connectionsStr;

    @Bean
     public ChannelConnections getChannelConnection() {
        ChannelConnections channelConnections = new ChannelConnections();
        channelConnections.setCaCertPath(caCertPath);
        channelConnections.setClientCertPassWord(clientCertPassWord);
        channelConnections.setClientKeystorePath(clientKeystorePath);
        channelConnections.setKeystorePassWord(keystorePassWord);
        channelConnections.setConnectionsStr(connectionsStr);
        return channelConnections;
    }

    public void setCaCertPath(String caCertPath) {
        this.caCertPath = caCertPath;
    }

    public void setClientKeystorePath(String clientKeystorePath) {
        this.clientKeystorePath = clientKeystorePath;
    }

    public void setKeystorePassWord(String keystorePassWord) {
        this.keystorePassWord = keystorePassWord;
    }

    public void setClientCertPassWord(String clientCertPassWord) {
        this.clientCertPassWord = clientCertPassWord;
    }

    public void setConnectionsStr(List<String> connectionsStr) {
        this.connectionsStr = connectionsStr;
    }
}
