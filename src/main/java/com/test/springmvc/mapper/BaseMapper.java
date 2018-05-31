
package com.test.springmvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * @author zengzw-1220
 * @date 2017年10月31日下午3:39:17
 */
public interface BaseMapper<T, PK> {

	/**
	 * 查询最大ID
	 * @date 2017年10月31日下午3:50:50
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @return
	 */
	int selectMaxId();

	/**
	 * 根据ID获取对象
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param id
	 * @return
	 */
	T selectById(PK id);

	/**
	 * 根据ID获取对象
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param record
	 * @return
	 */
	T selectByPrimaryKey(T record);

	/**
	 * 新增
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param record
	 * @return
	 */
	int insert(T record);

	/**
	 * 根据选择字段新增
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param record
	 * @return
	 */
	int insertSelective(T record);

	/**
	 * 根据Id 修改对象
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);

	/**
	 * 根据主键对象，修改部分字段信息
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 修改部分字段新
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param record
	 * @return
	 */
	int updateByIdSelective(T record);

	/**
	 * 根据Id 删除
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param id
	 * @return
	 */
	int deleteById(PK id);

	/**
	 * 根据主键 对象 删除
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(PK id);

	/**
	 * 分页获取所有数据
	 * @date 2017年10月31日下午3:51:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 * @param record
	 * @param rowBounds
	 * @return
	 */
	List<T> selectAll(T record, RowBounds rowBounds);

}
