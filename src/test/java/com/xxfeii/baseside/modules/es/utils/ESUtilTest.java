package com.xxfeii.baseside.modules.es.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.elasticsearch.client.Client;
import org.junit.Test;

public class ESUtilTest {

	@Test
	public void createIndexTest(){
		String indexName = "test";
		ESUtil esUtil = new ESUtil();
		Client client = ESClientUtil.getClient();
		esUtil.createIndex(client, indexName);
		ESClientUtil.closeESClient();
	}
	@Test
	public void indexTypeMappingTest(){
		String indexName = "test";
		ESUtil esUtil = new ESUtil();
		Client client = ESClientUtil.getClient();
		esUtil.indexTypeMapping(client, indexName, "test");
		ESClientUtil.closeESClient();
	}
	@Test
	public void indexHotSpotDataListTest(){
		String indexName = "test";
		ESUtil esUtil = new ESUtil();
		Client client = ESClientUtil.getClient();
		String[] contents ={
				"SVN与Git最主要的区别...",
				"学习目标 掌握泛型的产生意义...",
				"基本操作：CRUD ...",
				"Hibernate框架基础...",
				"Shell是什么..."
		};
		String[] titles ={
				"git简介","Java中泛型的介绍与简单使用","SQL基本操作","Hibernate框架基础","Shell基本知识"	
			};
		List<TestModel> dataList = new ArrayList<>();
		for(int i=0;i<5;i++){
			TestModel testModel = new TestModel();
			testModel.setEs_id(UUID.randomUUID().toString());
			testModel.setTitle(titles[i]);
			testModel.setContent(contents[i]);
			testModel.setPosttime(new Date());
			dataList.add(testModel);
		}
		esUtil.indexHotSpotDataList(client, indexName, "test", dataList);
		ESClientUtil.closeESClient();
	}
}
