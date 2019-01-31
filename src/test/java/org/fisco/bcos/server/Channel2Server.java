package org.fisco.bcos.server;

import org.fisco.bcos.Application;
import org.fisco.bcos.channel.client.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Channel2Server {

	@Autowired
    Service service;

	@Test
	public  void channel2ServerTest() throws Exception {

		
		String topic = "topic";
		List<String> topics = new ArrayList<String>();
		topics.add(topic);
		service.setTopics(topics);
		
		PushCallback cb = new PushCallback();
		
		service.setPushCallback(cb);

		System.out.println("3s...");
		Thread.sleep(1000);
		System.out.println("2s...");
		Thread.sleep(1000);
		System.out.println("1s...");
		Thread.sleep(1000);

		System.out.println("start test");
		System.out.println("===================================================================");

	}
}
