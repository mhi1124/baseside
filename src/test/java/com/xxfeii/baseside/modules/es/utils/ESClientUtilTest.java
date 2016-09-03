package com.xxfeii.baseside.modules.es.utils;

import org.elasticsearch.client.Client;
import org.junit.Test;

public class ESClientUtilTest {

	@Test
	public void getClientTest(){
		Client client = ESClientUtil.getClient();
		System.out.println(client.toString());
		ESClientUtil.closeESClient();
	}
}
