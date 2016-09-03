package com.xxfeii.baseside.modules.es.utils;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxfeii.baseside.common.utils.JsonUtil;
import com.xxfeii.baseside.common.utils.ProUtil;
import com.xxfeii.baseside.modules.es.entity.EsBaseModel;

/**
 * 
 * @ClassName ESUtil
 * @Description 索引工具类
 * @author davi
 * @date 2016年9月3日
 *
 */
public class ESUtil {
	private static Logger log = LoggerFactory.getLogger(ESUtil.class);

	/**
	 * 创建索引
	 * 
	 * @param indexName
	 * @return
	 */
	public boolean createIndex(Client client, String indexName) {
		if (isIndexExists(client, indexName)) {
			log.info("Index  " + indexName + " already exits!");
			return false;
		} else {
			CreateIndexResponse resp = client.admin().indices()
					.create(new CreateIndexRequest(indexName)).actionGet();
			if (resp.isAcknowledged()) {
				log.info("Index  " + indexName + " exits success!");
				return true;
			} else {
				log.info("Index  " + indexName + " exits fail!");
				return false;
			}
		}
	}

	/**
	 * 判断索引是否存在
	 * 
	 * @param indexName
	 *            索引库名称
	 * @return
	 */
	public static boolean isIndexExists(Client client, String indexName) {
		boolean flag = false;
		IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(
				indexName);
		IndicesExistsResponse inExistsResponse = client.admin().indices()
				.exists(inExistsRequest).actionGet();
		if (inExistsResponse.isExists()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断type是否存在
	 * 
	 * @param indexName
	 * @param typeName
	 * @return
	 */
	public static boolean existsType(Client client, String indexName,
			String typeName) {
		boolean flag = false;
		if (isIndexExists(client, indexName)) {
			TypesExistsResponse response = client.admin().indices()
					.prepareTypesExists(indexName).setTypes(typeName).execute()
					.actionGet();
			flag = response.isExists();
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 建mapping
	 * 
	 * @param indexName
	 * @param typeName
	 * @return
	 */
	public boolean indexTypeMapping(Client client, String indexName,
			String typeName) {
		boolean flag = false;
		if (!isIndexExists(client, indexName)) {
			createIndex(client, indexName);
		}
		String source = ProUtil.getConfig(typeName);
		if (StringUtils.isNotEmpty(source)) {
			PutMappingRequest request = Requests.putMappingRequest(indexName)
					.type(typeName).source(source);
			// Create type and mapping
			PutMappingResponse resp = client.admin().indices()
					.putMapping(request).actionGet();
			if (resp.isAcknowledged()) {
				log.info("Index: " + indexName + " ,typeName: " + typeName
						+ " appMapping ok, mapping:" + source);
				flag = true;
			} else {
				log.info("Index: " + indexName + " ,typeName: " + typeName
						+ " appMapping fail");
				flag = false;
			}
		} else {
			log.info("Index: " + indexName + " ,typeName: " + typeName
					+ " appMapping is not have!");
			flag = false;
		}
		return flag;
	}

	/**
	 * 清除所有索引
	 * 
	 * @param indexName
	 * @return
	 */
	public boolean deleteIndex(Client client, String indexName) {
		boolean flag = false;
		IndicesExistsResponse indicesExistsResponse = client.admin().indices()
				.exists(new IndicesExistsRequest(new String[]{indexName}))
				.actionGet();
		if (indicesExistsResponse.isExists()) {
			DeleteIndexResponse delete = client.admin().indices()
					.delete(new DeleteIndexRequest(indexName)).actionGet();
			if (!delete.isAcknowledged()) {
				log.info("Index: " + indexName + " wasn't deleted!");
				flag = false;
			} else {
				log.info("Index: " + indexName + " delete success!");
				flag = true;
			}
		} else {
			log.info("Index: " + indexName + " is not exists!");
			flag = false;
		}
		return flag;
	}

	/**
	 * 批量索引数据
	 * 
	 * @param indexName
	 * @param typeName
	 * @param dataList
	 * @return
	 */
	public boolean indexHotSpotDataList(Client client, String indexName,
			String typeName, List<? extends EsBaseModel> dataList) {
		boolean flag = false;
		if (dataList != null && dataList.size() > 0) {
			int size = dataList.size();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (int i = 0; i < size; ++i) {
				EsBaseModel data = dataList.get(i);
				String jsonSource = JsonUtil.modelToJson(data);
				if (jsonSource != null) {
					bulkRequest.add(new IndexRequest(indexName, typeName, data
							.getEs_id()).source(jsonSource));
				}
			}
			// 执行批量处理request
			BulkResponse bulkResponse = bulkRequest.get();
			if (bulkResponse.hasFailures()) {
				Iterator<BulkItemResponse> iter = bulkResponse.iterator();
				while (iter.hasNext()) {
					BulkItemResponse itemResponse = iter.next();
					if (itemResponse.isFailed()) {
						log.info(itemResponse.getFailureMessage());
					}
				}
				flag = false;
			}
			log.info("index success!");
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
}
