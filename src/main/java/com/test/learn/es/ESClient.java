package com.test.learn.es;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.alibaba.fastjson.JSON;

public class ESClient {

	private static final String INDICES ="mydate";
	private static final String TYPE ="testdata";

	static ThreadLocalRandom random = ThreadLocalRandom.current();
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static BulkProcessor staticBulkProcessor = null;
	// @Test
	public static void search(String q) {

		// 创建查询索引
		SearchRequestBuilder searchRequestBuilder = client
				.prepareSearch("myindex");
		// 设置查询索引类型
		searchRequestBuilder.setTypes("post");
		// 设置查询类型
		// 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
		// 2.SearchType.SCAN = 扫描查询,无序
		// 3.SearchType.COUNT = 不设置的话,这个为默认值,还有的自己去试试吧
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		// 设置查询关键词
		// fieldQuery 这个必须是你的索引字段哦,不然查不到数据,这里我只设置两个字段 id ,title
		//		searchRequestBuilder.setQuery(QueryBuilders.matchQuery("title", q));

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.termQuery("id", q));

		searchRequestBuilder.setQuery(boolQueryBuilder);
		//QueryBuilder qb1 = termQuery("note", noteParam);

		//qb2构造了一个组合查询（BoolQuery），其对应lucene本身的BooleanQuery，可以通过must、should、mustNot方法对QueryBuilder进行组合，形成多条件查询
		/*QueryBuilder qb2 = boolQuery()
                            .must(termQuery("note", "test1"))
                            .must(termQuery("note", "test4"))
                            .mustNot(termQuery("note", "test2"))
                            .should(termQuery("note", "test3"));*/
		//qb3构造了一个过滤查询，就是在TermQuery的基础上添加一个过滤条件RangeFilter，这个范围过滤器将限制查询age字段大于等于23，小于等于54的结果
		/* QueryBuilder qb3 = filteredQuery(
                         termQuery("note.first", "shay"),
                         rangeFilter("create_date")
                        .from(new Date("2010-02-01"))
                        .to(new Date("2013-03-01"))
                        .includeLower(true)
                        .includeUpper(false)
          );*/

		// 设置查询数据的位置,分页用吧
		searchRequestBuilder.setFrom(0);
		// 设置查询结果集的最大条数
		searchRequestBuilder.setSize(10);
		// 设置是否按查询匹配度排序
		searchRequestBuilder.setExplain(true);

		// 最后就是返回搜索响应信息
		SearchResponse response = searchRequestBuilder.execute().actionGet();

		// 命中记录数
		Long hitCount = response.getHits().totalHits;

		System.out.println("命中总数："+hitCount);

		SearchHits searchHits = response.getHits();
		SearchHit[] hits = searchHits.getHits();

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < hits.length; i++) {
			Map<String, Object> map = hits[i].getSourceAsMap();
			list.add(map);
		}
		System.out.println("search success .."+list);

	}

	public static void main(String[] args) throws IOException {
		initESClient();
//		createIndex();
		createAtomicBulkIndex();
		//		search("2");
		//		buildMapping();
		closeESClient();
	}


	/**
	 * 批量
	 */
	public static void createBulkIndex() {
		//批量提交
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for(int i=100001;i<200000;i++){
			int id = i;
			String title = "中国到底是abcask就是了抗生素开导开导拉开的拉粉丝" + i;
			String desc = "然后插入OK。后来我看了源码，才恍然大悟。新版本（我不知道从什么版本开始，我以前最开始用的是0.9）值是根据value 的类型来判断。我贴一下。" + i;
			//业务对象
			String json = getBuilderJson(id, title,desc);
			IndexRequestBuilder indexRequest = client.prepareIndex(INDICES,TYPE)
					//指定不重复的ID
					.setSource(json,XContentType.JSON).setId(i+"");
			//添加到builder中
			bulkRequest.add(indexRequest);
		}


		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			System.out.println(bulkResponse.buildFailureMessage());
		}
	}


	/**
	 * 批量 自动提交
	 */
	public static void createAtomicBulkIndex() {

		//自动提交
		for(int i=200001;i<211000;i++){
			int id = i;
			String title = "中国到底是abcask就是了抗生素开导开导拉开的拉粉丝" + i;
			String desc = "然后插入OK。后来我看了源码，才恍然大悟。新版本（我不知道从什么版本开始，我以前最开始用的是0.9）值是根据value 的类型来判断。我贴一下。" + i;
			//业务对象
			String json = getBuilderJson(id, title,desc);
			IndexRequestBuilder indexRequest = client.prepareIndex(INDICES,TYPE)
					//指定不重复的ID
					.setSource(json,XContentType.JSON).setId(i+"");
			//添加到builder中
			getBuldProcessor().add(new IndexRequest(INDICES,TYPE).source(json,XContentType.JSON));
		}
		
		System.out.println("------新增结束");

	}

	/**
	 * 创建索引
	 */
	public static void createIndex() {
		for (int i = 5; i < 1000; i++) {
			Integer id =  i;
			String title = "中国到底是abc否是发到舒服点诗圣杜甫" + i;
			String desc = "因为我是单点的，所以健康度直接是yellow，黄色不影响使用，红色就有问题了，不过具体看问题是什么问题。" + i;
			client.prepareIndex(INDICES, TYPE)
			.setSource(getBuilderJson(id, title,desc),XContentType.JSON).execute().actionGet();
		}
		System.out.println("---新增数据结束"); 

		System.out.println("-----over-----");
	}


	/**
	 * 创建Mapping
	 * 
	 * @throws IOException
	 */
	public static void buildMapping() throws IOException {
		XContentBuilder contentBuilder = XContentFactory.jsonBuilder()	
				.startObject()
				.startObject("properties")
				.startObject("id").field("type", "integer").field("store", true).endObject()
				.startObject("title").field("type", "text").field("index",true).field("store",true).field("analyzer", "ik_smart").endObject()
				.startObject("description").field("type", "text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
				.startObject("edate").field("type", "date").field("format","yyyy-MM-dd HH:mm:ss").field("store", true).endObject()
				.startObject("status").field("type", "integer").field("store", true).endObject()

				.endObject()
				.endObject();
		PutMappingRequest mapping = Requests.putMappingRequest("mydate").type("testdata").source(contentBuilder);
		client.admin().indices().putMapping(mapping).actionGet();
		client.close();

		System.out.println("----创建mapping结束");

	}

	/**
	 * 创建索引 通常是json格式
	 * 
	 * @param obj
	 *            创建索引的实体
	 * 
	 * @return
	 */
	private static String getBuilderJson(Integer id, String title,String desc) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("title", title);
		map.put("description",desc);
		map.put("edate",dateFormat.format(new Date()));
		map.put("status", random.nextInt(2));
		return JSON.toJSONString(map);

	}

	/**
	 * 初始化客户端连接
	 */
	public static void initESClient() {
		// 配置你的es,现在这里只配置了集群的名,默认是elasticsearch,跟服务器的相同
		Settings settings = Settings.builder().put("cluster.name", "my-application")//设置集群名称
				.put("node.name","node-1")
				.put("client.transport.ignore_cluster_name", false)
				.put("client.transport.sniff",true)
				.build();//自动嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中
		// 这里可以同时连接集群的服务器,可以多个,并且连接服务是可访问的
		try {

			client =  new PreBuiltTransportClient(settings) 
					.addTransportAddress(new TransportAddress(InetAddress.getByName("0.0.0.0"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	//【设置自动提交文档】
	public static BulkProcessor getBuldProcessor() {
		if(staticBulkProcessor != null) {
			return staticBulkProcessor;
		}
		staticBulkProcessor = BulkProcessor.builder(client,new BuldProcessListener())		//文档数量达到1000时提交;
				.setBulkActions(1000)
				//总文档体积达到5MB时提交 
				.setBulkSize(new ByteSizeValue(5,ByteSizeUnit.MB))
				//每5S提交一次（无论文档数量、体积是否达到阈值）
				.setFlushInterval(TimeValue.timeValueSeconds(5))
				//加1后为可并行的提交请求数，即设为0代表只可1个请求并行，设为1为2个并行
				.setConcurrentRequests(2)
				.build();
		//				 staticBulkProcessor.awaitClose(10, TimeUnit.MINUTES);//关闭，如有未提交完成的文档则等待完成，最多等待10分钟

		return staticBulkProcessor;
	};

	// @After
	public static void closeESClient() {
		client.close();
	}

	private static Client client;

}
