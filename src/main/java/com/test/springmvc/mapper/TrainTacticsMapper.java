package com.test.springmvc.mapper;

import org.apache.ibatis.annotations.Param;

import com.test.springmvc.model.TrainTactics;

/**
 * 
 * 战术板Mapper
 * @author zengzw-1220
 * @date 2018年3月8日下午3:05:30
 */
public interface TrainTacticsMapper extends BaseMapper<TrainTactics, Integer>{
	
	/**
	 * 
	 * 根据战术板名称获取战术信息
	 * 
	 * @date 2018年3月8日下午3:40:51
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param name
	 * @return
	 */
	public TrainTactics selectByName(@Param("tacticName")String tacticName,@Param("orgUserId")String orgUserId);
	
	
   
}