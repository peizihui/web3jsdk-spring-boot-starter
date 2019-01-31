package org.fisco.bcos.server;

import org.fisco.bcos.Application;
import org.fisco.bcos.channel.handler.ChannelConnections;
import org.fisco.bcos.channel.proxy.Server;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.net.ssl.SSLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProxyServerTest {
	@Autowired
	ThreadPoolTaskExecutor executor;
	@Autowired
    ChannelConnections channelConnections;

	@Test
	public  void proxyServerTest() throws SSLException {
	 Server server =	new Server();
	 server.setRemoteConnections(channelConnections);
	 server.setThreadPool(executor);
	 server.setBindPort(8830);
	 server.run();
	}
}
