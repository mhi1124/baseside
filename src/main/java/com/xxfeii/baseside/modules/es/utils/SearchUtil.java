package com.xxfeii.baseside.modules.es.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxfeii.baseside.modules.es.entity.SearchUtilModel;

/**
 * 
 * @ClassName SearchUtil
 * @Description 搜索工具类
 * @author davi
 * @date 2016年9月3日
 * 
 */
public class SearchUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(SearchUtil.class);

	/**
	 * 精确到字段查询
	 * 
	 * @param client
	 * @param indexName
	 *            索引名称
	 * @param type
	 *            索引类型
	 * @param fild
	 *            字段名称
	 * @param value
	 *            查询的值
	 * @param hightShow
	 *            是否高亮显示
	 * @param pageNo
	 *            获取数据开始的条数
	 * @param pageSize
	 *            获取的数量
	 */
	@Deprecated
	public static SearchUtilModel search(Client client, String indexName,
			String[] types, String fild, String value, boolean hightShow,
			int pageNo, int pageSize) {
		SearchUtilModel sum = new SearchUtilModel();
		sum.setClient(client);
		String[] filds = {fild};
		sum.setFilds(filds);
		sum.setTypes(types);
		sum.setHightShow(true);
		sum.setIndexName(indexName);
		sum.setPageNo(pageNo);
		sum.setPageSize(pageSize);
		// index // name
		// 设搜索参数
		SearchRequestBuilder sbuilder = client.prepareSearch(indexName)
				.setTypes(types)
				// type
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery(fild, value)) // Query
				// .setPostFilter(QueryBuilders.rangeQuery("eventCount").from(1).to(18))
				// .addFields(fields)
				// // Filter
				.setFrom(pageNo).setSize(pageSize).setExplain(true);
		if (hightShow) {
			sbuilder.addHighlightedField(fild)// 高亮字段
					.setHighlighterPreTags("<span style=color:red>") // 对高亮字段添加样式
					.setHighlighterPostTags("</span>");
		}
		// 进行搜索
		SearchResponse response = sbuilder.execute().actionGet();
		// 搜索到的数量
		long nbHits = response.getHits().getTotalHits();
		logger.info("nbHits : " + nbHits);
		sum.setTotalCount(nbHits);
		List<String> searchResult = new ArrayList<String>();
		for (SearchHit hit : response.getHits()) {
			String json = hit.getSourceAsString();
			JSONObject o = JSON.parseObject(json);
			// 获取处理后高亮字段的值
			Map<String, HighlightField> result = hit.highlightFields();
			HighlightField titleField = result.get(fild);
			Text[] titleTexts = titleField.fragments();
			String fildValue = "";
			for (Text text : titleTexts) {
				fildValue += text;
			}
			// 替换成高亮
			o.put(fild, fildValue);
			searchResult.add(o.toJSONString());
			System.out.println(o.toJSONString());
		}
		sum.setSearchResults(searchResult);
		return sum;
	}

	/**
	 * 
	 * @param client
	 * @param indexName
	 * @param types
	 * @param fild
	 * @param value
	 * @param hightShow
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static SearchUtilModel search1(Client client, String indexName,
			String[] types, String fild, String value, boolean hightShow,
			int pageNo, int pageSize) {
		SearchUtilModel sum = new SearchUtilModel();
		sum.setClient(client);
		String[] filds = {fild};
		sum.setFilds(filds);
		sum.setTypes(types);
		sum.setHightShow(true);
		sum.setIndexName(indexName);
		sum.setPageNo(pageNo);
		sum.setPageSize(pageSize);
		sum.setHightFilds(filds);
		QueryBuilder qb = QueryBuilders.multiMatchQuery(value, filds);
		hightSearch(sum, qb);

		return sum;
	}

	/**
	 * 
	 * @param client
	 * @param indexName
	 * @param types
	 * @param filds
	 * @param value
	 * @param hightfilds
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static SearchUtilModel multiMatchHightSearch(Client client,
			String indexName, String[] types, String[] filds, String value,
			String[] hightfilds, int pageNo, int pageSize) {
		SearchUtilModel sum = new SearchUtilModel();
		sum.setClient(client);
		sum.setFilds(filds);
		sum.setTypes(types);
		sum.setIndexName(indexName);
		sum.setPageNo(pageNo);
		sum.setPageSize(pageSize);
		sum.setHightFilds(hightfilds);
		QueryBuilder qb = QueryBuilders.multiMatchQuery(value, filds);
		hightSearch(sum, qb);

		return sum;
	}

	/**
	 * 高亮搜索
	 * 
	 * @param searchUtilModel
	 * @param queryBuilder
	 */
	public static void hightSearch(SearchUtilModel searchUtilModel,
			QueryBuilder queryBuilder) {
		Client client = searchUtilModel.getClient();
		String indexName = searchUtilModel.getIndexName();
		int pageNo = searchUtilModel.getPageNo();
		int pageSize = searchUtilModel.getPageSize();
		String[] types = searchUtilModel.getTypes();
		String[] hightFilds = searchUtilModel.getHightFilds();
		// 设搜索参数
		SearchRequestBuilder sbuilder = client.prepareSearch(indexName)
				.setTypes(types)
				// type
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(queryBuilder) // Query
				.setFrom(pageNo).setSize(pageSize).setExplain(true);
		if (null != hightFilds && hightFilds.length > 0) {
			if (null != hightFilds && hightFilds.length > 0) {
				for (int i = 0, size = hightFilds.length; i < size; i++) {
					sbuilder.addHighlightedField(hightFilds[i])// 高亮字段
							.setHighlighterPreTags("<span style=color:red>") // 对高亮字段添加样式
							.setHighlighterPostTags("</span>");
				}
			}
		}
		logger.info("查询语句=====" + sbuilder);
		// 进行搜索
		SearchResponse response = sbuilder.execute().actionGet();
		// 搜索到的数量
		long nbHits = response.getHits().getTotalHits();
		logger.info("nbHits : " + nbHits);
		searchUtilModel.setTotalCount(nbHits);
		List<String> searchResult = new ArrayList<String>();
		for (SearchHit hit : response.getHits()) {
			String json = hit.getSourceAsString();
			JSONObject o = JSON.parseObject(json);
			// 获取处理后高亮字段的值
			Map<String, HighlightField> result = hit.highlightFields();
			if (null != hightFilds && hightFilds.length > 0) {
				if (null != hightFilds && hightFilds.length > 0) {
					for (int i = 0, size = hightFilds.length; i < size; i++) {
						HighlightField titleField = result.get(hightFilds[i]);
						if (null != titleField) {
							Text[] titleTexts = titleField.fragments();
							String fildValue = "";
							for (Text text : titleTexts) {
								fildValue += text;
							}
							// 替换成高亮
							o.put(hightFilds[i], fildValue);
						}
					}
				}
			}
			searchResult.add(o.toJSONString());
		}
		searchUtilModel.setSearchResults(searchResult);
	}

	/**
	 * multiMatchQuery
	 * 
	 * @param client
	 * @param indexName
	 * @param types
	 * @param filds
	 * @param text
	 * @param hightShow
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public SearchUtilModel multiMatchQuery(Client client,
			String indexName, String[] types, String[] filds, String text,
			int pageNo, int pageSize) {
		QueryBuilder qb = QueryBuilders.multiMatchQuery(text, filds);

		SearchUtilModel sum = new SearchUtilModel();
		sum.setClient(client);
		sum.setIndexName(indexName);
		sum.setPageNo(pageNo);
		sum.setPageSize(pageSize);
		sum.setFilds(filds);
		sum.setTypes(types);
		sum.setText(text);
		search(sum, qb);
		return sum;
	}

	/**
	 * 
	 * @param client
	 * @param indexName
	 * @param types
	 * @param searchJson
	 * @return
	 */
	public SearchUtilModel jsonSearch(Client client, String indexName,
			String[] types, String searchJson) {
		SearchUtilModel sum = new SearchUtilModel();
		sum.setClient(client);
		sum.setIndexName(indexName);
		sum.setTypes(types);
		search(sum, searchJson);
		return sum;
	}

	/**
	 * 
	 * @param SearchUtilModel
	 *            需要有client，indexName，types，pageNo，pageSize，text
	 * @param queryBuilder
	 *            查询条件
	 */
	public void search(SearchUtilModel searchUtilModel,
			QueryBuilder queryBuilder) {
		Client client = searchUtilModel.getClient();
		String indexName = searchUtilModel.getIndexName();
		int pageNo = searchUtilModel.getPageNo();
		int pageSize = searchUtilModel.getPageSize();
		String[] types = searchUtilModel.getTypes();
		System.out.println(queryBuilder);
		SearchRequestBuilder sbuilder = client.prepareSearch(indexName)
				.setTypes(types)
				// type
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(queryBuilder) // Query
				.setFrom(pageNo).setSize(pageSize).setExplain(true);
		// sbuilder.setTypes(types);
		System.out.println(sbuilder);
		search(sbuilder, searchUtilModel);
	}

	public void search(SearchUtilModel searchUtilModel, String query) {
		Client client = searchUtilModel.getClient();
		String indexName = searchUtilModel.getIndexName();
		String[] types = searchUtilModel.getTypes();
		SearchRequestBuilder sbuilder = client.prepareSearch(indexName)
				.setTypes(types);
		sbuilder.setQuery(query);
		System.out.println(sbuilder);
		search(sbuilder, searchUtilModel);
	}

	/**
	 * 搜索
	 * 
	 * @param sbuilder
	 * @param sum
	 */
	public void search(SearchRequestBuilder sbuilder, SearchUtilModel sum) {
		// 进行搜索
		SearchResponse response = sbuilder.execute().actionGet();
		// 搜索到的数量
		long nbHits = response.getHits().getTotalHits();
		sum.setTotalCount(nbHits);
		List<String> searchResult = new ArrayList<String>();
		for (SearchHit hit : response.getHits()) {
			String json = hit.getSourceAsString();
			JSONObject o = JSON.parseObject(json);
			searchResult.add(o.toJSONString());
		}
		sum.setSearchResults(searchResult);
	}
}
