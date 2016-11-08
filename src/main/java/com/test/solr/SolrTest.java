/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.solr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.GroupParams;
import org.apache.solr.common.util.NamedList;

import com.alibaba.fastjson.JSON;

/**
 *
 * @author zengzw
 * @date 2016年10月26日
 */
public class SolrTest {
    //    private static final String URL = "http://172.16.1.140:8983/solr/collection2/";
    private static final String URL = "http://localhost:8086/solr/collection1/";


    private static HttpSolrServer server = null;


    private static void query(SolrQuery query) throws SolrServerException{
        query.setQuery("job_name:'车间'");

        QueryResponse response = server.query(query);
        SolrDocumentList list =  response.getResults();
        System.out.println("--------"+list.getNumFound());

        for(SolrDocument doc:list){
            System.out.println(">>>> "+doc.get("goods_price"));
        }
    }


    /**
     * 或 查询
     * 
     * @param query
     * @throws SolrServerException 
     */
    private static void queryOr(SolrQuery query) throws SolrServerException{
        query.setQuery("job_name:车间 or job_name:员工");
        //query.setQuery("leaf_category_name:其他交通工具   leaf_category_name:牛奶乳品");//空格也是 或者
        //query.setQuery("introduction:设计,简洁 ");//查询 包含设计、简洁

        //query.setQuery("leaf_category_name:其他交通工具 Or leaf_category_name:牛奶乳品");//或者
        //query.setQuery("leaf_category_name:其他交通工具   leaf_category_name:牛奶乳品");//空格也是 或者

        //过滤器查询。在查询的结果中再进行过滤。
        //query.addFilterQuery("goods_price:20");


        //查询goods_price 大于10 小于 20 ，没有等于
        //query.addFilterQuery("goods_price:{10 TO 20}");

        //排序
        query.addSort("salary_min", ORDER.asc);



        QueryResponse response = server.query(query);
        SolrDocumentList list =  response.getResults();
        System.out.println("--------"+list.getNumFound());

        for(SolrDocument doc:list){
            System.out.println(">>>> "+doc.get("goods_price"));
        }


    }


    /**
     * 且查询
     * 
     * @param query
     * @throws SolrServerException 
     */
    private static void queryAnd(SolrQuery query) throws SolrServerException{
        query.setQuery("leaf_category_name:其他交通工具 AND goods_brand_name:高碑店百货"); //并且
        query.setQuery("leaf_category_name:其他交通工具 +leaf_category_name:牛奶乳品");
        query.setQuery("introduction:设计,简洁 NOT  goods_id:334210"); //查询introduction包含设计、简洁  且 goods_id 不等于334210



        QueryResponse response = server.query(query);
        SolrDocumentList list =  response.getResults();
        System.out.println("--------"+list.getNumFound());

        for(SolrDocument doc:list){
            System.out.println(">>>> "+doc.get("goods_price"));
        }

    }


    /**
     * 区间查询
     * 
     * @param query
     * @throws SolrServerException 
     */
    private static void querySection(SolrQuery query) throws SolrServerException{
        query.setQuery("goods_price:[10 TO 20]"); //goods_price 10 >= goods_price <= 20

        //        query.setQuery("goods_price:[100 TO 200] + goods_price:[5 TO 10]"); 



        QueryResponse response = server.query(query);
        SolrDocumentList list =  response.getResults();
        System.out.println("--------"+list.getNumFound());

        for(SolrDocument doc:list){
            System.out.println(">>>> "+doc.get("goods_price"));
        }
    }



    /**
     *  
     * @param query
     * @throws SolrServerException
     */
    private static void queryFacet(SolrQuery query) throws SolrServerException{
        //分片
        query.setFacet(true);
        query.setFacetMinCount(1);//设置 限制 count的最小返回值，默认为0 
        query.setFacetMissing(false); //不统计null的值
        query.addFacetField("goods_brand_name");
        query.addFacetField("goods_code");
        query.setFacetPrefix("开县");//查询goods_brand_name  goods_code中的关键字前缀是“开县”的
        query.setFacetLimit(10); // 设置返回结果条数 ,-1表示返回所有,默认值为100

        //开始条数,偏移量,它与facet.limit配合使用可以达到分页的效果
        query.setParam(FacetParams.FACET_OFFSET, "30");
        //        query.addFacetQuery(""); //过滤facet条件

        /*
         * FacetComponet有两种排序选择，分别是count和index，
         * count是按每个词出现的次数，index是按词的字典顺序。如果查询参数不指定facet.sort，solr默认是按count排序。
         */
        query.setFacetSort("count"); //facet 排序.count 或者 index  排序


        /*
         4、facet.date用来分组时间字段，可以设置起止时间和时间的跨度
        facet.date=ptime
        facet.date.start=2010-1-1T0:0:0Z
        facet.date.end=NOW
        facet.date.gap=%2B1YEAR
        gap设置的时候可以用+-/等符号，但是需要转义才能用。
         */


        QueryResponse response = server.query(query);
        //================Facets Result============================
        List<FacetField> facets = response.getFacetFields();
        for(FacetField file:facets){
            System.out.println("--->"+file.getName()+":"+file.getValueCount());

            List<Count> listCounts = file.getValues();
            for(Count count:listCounts){
                System.out.println(count.getName()+":"+count.getCount());
            }
        }


    }



    /**
     *  高亮
     *  
     * @param query
     * @throws SolrServerException
     */
    private static void queryHighlight(SolrQuery query) throws SolrServerException{
        query.setRows(20);
        query.setHighlight(true);
        query.addHighlightField("job_name");
        query.setHighlightSimplePre("<b>");
        query.setHighlightSimplePost("</b>");
        query.setHighlightSnippets(3);
        query.set("hl.usePhraseHighlighte", true);// 如果一个查询中含有短语（引号框起来的）那么会保证一定要完全匹配短语的才会被高亮。

        //有时我们查询根据条件“java OR (empId:1000 AND empId:1001)”搜索时，结果如果高亮显示，可能出现1000,1001数字也会高亮，
        //但是我们只希望java关键字高亮，这个时候可以用下面的方法,只对lucene和solr关键字进行高亮显示（solr不作为搜索条件也可以）
        //        query.setParam("hl.q", "员工");


        QueryResponse response = server.query(query);

        //=======================Highlighting=====================
        //第一层Map: Key String == ID : Value: Map
        //第二层Map: Key String == name_ik : Value: List
        //获取到List: String 0,1,2....
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        System.out.println(">>>>  Highlighting:"+highlighting);


    }






    /**
     * 测试Group
     * @throws SolrServerException 
     */
    private static void queryGroup(SolrQuery query) throws SolrServerException{
        query.setQuery("*:*");

        query.setParam(GroupParams.GROUP, true);
        query.setParam(GroupParams.GROUP_FIELD,"province_id");
        //// 默认为 grouped所有文档分组显示 ,simple的话所有文档显示在一个集合中
        query.setParam(GroupParams.GROUP_FORMAT,"grouped");
        query.setParam(GroupParams.GROUP_MAIN,"false");//format为grouped的时候，必须为false
        query.setParam(GroupParams.GROUP_TOTAL_COUNT, true);//返回分组个数ngroups：


        QueryResponse response = server.query(query);
        // ========================= Group Result======================
        GroupResponse groupResponse = response.getGroupResponse();
        
        System.out.println(JSON.toJSONString(groupResponse));
        if(groupResponse != null){
            List<GroupCommand> listCommands = groupResponse.getValues();
            for(GroupCommand command : listCommands){
                System.out.println(command.getName() +": ");

                List<Group> listGroups = command.getValues();
                for(Group group:listGroups){
                    System.out.println("proviceId:"+group.getGroupValue()+":"+group.getResult().getNumFound());
                    System.out.println("document:"+group.getResult());
                }
            }
        }


    }



    private static void queryStatic(SolrQuery query) throws SolrServerException{
        /**
         * statistics
         */
        query.setGetFieldStatistics("recruit_number");
        //一次对多个字段独立分组统计
        //这相当于执行两个带有GROUP BY(facet)子句的SQL，这两个GROUP BY分别只对一个字段进行汇总统计
        query.addStatsFieldFacets("recruit_number", "salary_min");

        QueryResponse response = server.query(query);

        //========================= statistics===========================
        Map<String, FieldStatsInfo> statsInfoMap = response.getFieldStatsInfo();
        //        System.out.println("===>"+statsInfoMap);

        for( String  key :statsInfoMap.keySet()){
            FieldStatsInfo  fieldStatsInfo=statsInfoMap.get(key);
            System.out.println(fieldStatsInfo);

            Map<String, List<FieldStatsInfo>> maps=fieldStatsInfo.getFacets();//得到每个(facet)分组
            for ( String  facet: maps.keySet()){
                System.out.println("facet:"+facet);
                List<FieldStatsInfo> lists= maps.get(facet);

                System.out.println("--->"+lists);
            }

        }



    }

    private static void queryFacetPivot(SolrQuery query) throws SolrServerException{
        query.setQuery("*:*");
        //分片
        query.setFacet(true);
        query.setRows(1000);
        query.setStart(0);
        //根据省、市、区进行多层次的分组
        query.addFacetPivotField("province_id,city_id,zone_id");
        //        query.add("facet.pivot", "province_id,city_id");        //根据这两维度来分组查询  
        QueryResponse response = server.query(query);

        SolrDocumentList list =  response.getResults();
        System.out.println("--------"+list.getNumFound());

        for(SolrDocument doc:list){
            System.out.println(">>>> "+doc);
        }


        //================Facets Result============================
        NamedList<List<PivotField>> namedList = response.getFacetPivot();  
        System.out.println(namedList);
        if(namedList != null){  
            List<PivotField> pivotList = null;  
            for(int i=0;i<namedList.size();i++){  
                pivotList = namedList.getVal(i);  
                if(pivotList != null){  

                    for(PivotField pivot:pivotList){  
                        System.out.println("--"+pivot.getValue());
                        List<PivotField> fieldList = pivot.getPivot(); 

                        if(fieldList != null){  
                            for(PivotField field:fieldList){  
                                System.out.println(" --"+field.getValue());

                                List<PivotField> threedFieldList = field.getPivot();  
                                for(PivotField f:threedFieldList){  
                                    System.out.println("  ---"+f.getValue());
                                }  
                            }  
                        }  

                    }  
                }  
            }  
        }  
    }




    public static void main(String[] args) throws SolrServerException {
        server = new HttpSolrServer(URL);

        SolrQuery query = new SolrQuery();

        //        queryFacetPivot(query);

        queryGroup(query);
        if(true){
            return;
        }
        //        query.setQuery("job_name:'车间'");
        //        query.setQuery("leaf_category_name:其他交通工具 AND goods_brand_name:高碑店百货"); //并且
        //        query.setQuery("leaf_category_name:其他交通工具 Or leaf_category_name:牛奶乳品");//或者
        //        query.setQuery("leaf_category_name:其他交通工具   leaf_category_name:牛奶乳品");//空格也是 或者
        //        query.setQuery("leaf_category_name:其他交通工具 +leaf_category_name:牛奶乳品");

        //查询name包含solr apple
        //        query.setQuery("name:solr,apple");

        //                query.setQuery("introduction:设计,简洁 ");//查询 包含设计、简洁

        //        query.setQuery("introduction:设计,简洁 NOT  goods_id:334210"); //查询introduction包含设计、简洁  且 goods_id 不等于334210


        //        query.setQuery("goods_price:[10 TO 20]"); //goods_price 10 >= goods_price <= 20

        //        query.setQuery("goods_price:[100 TO 200] + goods_price:[5 TO 10]"); 

        //过滤器查询。在查询的结果中再进行过滤。
        //        query.addFilterQuery("goods_price:20");


        //查询goods_price 大于10 小于 20 ，没有等于
        //        query.addFilterQuery("goods_price:{10 TO 20}");

        //排序
        query.addSort("salary_min", ORDER.asc);


        //======================= Facet ====================

        //分片
        query.setFacet(true);
        query.addFacetPivotField("province_id","city_id");
        /*   query.setFacetMinCount(1);//设置 限制 count的最小返回值，默认为0 
        query.setFacetMissing(false); //不统计null的值
        query.addFacetField("goods_brand_name");
        query.addFacetField("goods_code");
        //        query.setFacetPrefix("开县");//查询goods_brand_name  goods_code中的关键字前缀是“开县”的
        //        query.setFacetLimit(10); // 设置返回结果条数 ,-1表示返回所有,默认值为100

        // //开始条数,偏移量,它与facet.limit配合使用可以达到分页的效果
        //        query.setParam(FacetParams.FACET_OFFSET, "30");
        //        query.addFacetQuery("") //过滤facet条件

         *//**
         * FacetComponet有两种排序选择，分别是count和index，
         * count是按每个词出现的次数，index是按词的字典顺序。如果查询参数不指定facet.sort，solr默认是按count排序。
         *//*
        query.setFacetSort("count"); //facet 排序.count 或者 index  排序
          */

        /*4、facet.date用来分组时间字段，可以设置起止时间和时间的跨度
    facet.date=ptime
    facet.date.start=2010-1-1T0:0:0Z
    facet.date.end=NOW
    facet.date.gap=%2B1YEAR
    gap设置的时候可以用+-/等符号，但是需要转义才能用。*/

        /**
         * 测试Group
         */
        query.setParam(GroupParams.GROUP, true);
        query.setParam(GroupParams.GROUP_FIELD,"salary_min");
        //// 默认为 grouped所有文档分组显示 ,simple的话所有文档显示在一个集合中
        query.setParam(GroupParams.GROUP_FORMAT,"grouped");
        query.setParam(GroupParams.GROUP_MAIN,"false");//format为grouped的时候，必须为false
        query.setParam(GroupParams.GROUP_TOTAL_COUNT, true);//返回分组个数ngroups：



        /**
         * Highlight
         */
        query.setRows(20);
        query.setHighlight(true);
        query.addHighlightField("job_name");
        query.setHighlightSimplePre("<b>");
        query.setHighlightSimplePost("</b>");
        query.setHighlightSnippets(3);
        query.set("hl.usePhraseHighlighte", true);// 如果一个查询中含有短语（引号框起来的）那么会保证一定要完全匹配短语的才会被高亮。

        //有时我们查询根据条件“java OR (empId:1000 AND empId:1001)”搜索时，结果如果高亮显示，可能出现1000,1001数字也会高亮，
        //但是我们只希望java关键字高亮，这个时候可以用下面的方法,只对lucene和solr关键字进行高亮显示（solr不作为搜索条件也可以）
        //        query.setParam("hl.q", "员工");



        /**
         * statistics
         */
        query.setGetFieldStatistics("recruit_number");
        query.addStatsFieldFacets("recruit_number", "salary_min");





        QueryResponse response = server.query(query);
        /*    SolrDocumentList list =  response.getResults();
        for(SolrDocument doc:list){
            System.out.println(">>>> "+doc.get("goods_price"));
        }*/


        //=======================Highlighting=====================
        //第一层Map: Key String == ID : Value: Map
        //第二层Map: Key String == name_ik : Value: List
        //获取到List: String 0,1,2....
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        System.out.println(">>>>  Highlighting:"+highlighting);




        //================Facets Result============================
        List<FacetField> facets = response.getFacetFields();
        for(FacetField file:facets){
            System.out.println("--->"+file.getName()+":"+file.getValueCount());

            List<Count> listCounts = file.getValues();
            for(Count count:listCounts){
                System.out.println(count.getName()+":"+count.getCount());
            }
        }



        // ========================= Group Result======================
        GroupResponse groupResponse = response.getGroupResponse();
        if(groupResponse != null){
            List<GroupCommand> listCommands = groupResponse.getValues();
            for(GroupCommand command : listCommands){
                //                System.out.println(command.getName() +": ");

                List<Group> listGroups = command.getValues();
                for(Group group:listGroups){
                    System.out.println(group.getGroupValue()+":"+group.getResult().getNumFound());
                }
            }
        }




        //========================= statistics===========================
        Map<String, FieldStatsInfo> statsInfoMap = response.getFieldStatsInfo();
        //        System.out.println("===>"+statsInfoMap);

        for( String  key :statsInfoMap.keySet()){
            FieldStatsInfo  fieldStatsInfo=statsInfoMap.get(key);
            System.out.println(fieldStatsInfo);

            Map<String, List<FieldStatsInfo>> maps=fieldStatsInfo.getFacets();//得到每个(facet)分组
            for ( String  facet: maps.keySet()){
                System.out.println("facet:"+facet);
                List<FieldStatsInfo> lists= maps.get(facet);

                System.out.println("--->"+lists);
            }

        }





    }
}
