/*
 * @Project Name: springmvcdemo
 * @File Name: TrainTasticService.java
 * @Package Name: com.test.springmvc.service
 * @Date: 2018年5月31日下午4:07:05
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springmvc.mapper.TrainTacticsMapper;
import com.test.springmvc.model.TrainTactics;

import scala.collection.generic.BitOperations.Int;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月31日下午4:07:05
 * @see
 */

@Service
public class TrainTacticsService {
	
	@Autowired
	private TrainTacticsMapper tacticsMapper;
	
	
	public TrainTactics getByid(Integer id){
		return tacticsMapper.selectById(id);
	}
	
}
