package org.bcos.autoconfigure;

import org.bcos.contract.tools.ToolConf;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "toolconf")
public class ToolConfig {
    private String systemProxyAddress;
    private String privKey;
    private String account;
    private String outPutpath;


    @Bean
    public ToolConf getToolConf() {
    ToolConf toolConf = new ToolConf();
    toolConf.setAccount(account);
    toolConf.setPrivKey(privKey);
    toolConf.setOutPutpath(outPutpath);
    toolConf.setSystemProxyAddress(systemProxyAddress);
    return toolConf;
    }

    public void setSystemProxyAddress(String systemProxyAddress) {
        this.systemProxyAddress = systemProxyAddress;
    }

    public void setPrivKey(String privKey) {
        this.privKey = privKey;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setOutPutpath(String outPutpath) {
        this.outPutpath = outPutpath;
    }
}
