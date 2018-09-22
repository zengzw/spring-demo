package com.test.learn.es;

import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;

public class BuldProcessListener implements Listener{

	@Override
	public void beforeBulk(long executionId, BulkRequest request) {
	System.out.println("-------------");
		
	}

	@Override
	public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
		System.out.println(response.getItems().length +" - " + response.getIngestTookInMillis() );
	}

	@Override
	public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
		   System.out.println( " 有文档提交失败！after failure=" + failure);
		
	}

}
