package com.test.springmvc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 战术板实体
 * 
 * 
 * @author zengzw-1220
 * @date 2018年3月8日下午2:53:02
 */
public class TrainTactics implements Serializable{
	
	/**
	 * serialVersionUID: TODO
	 */
	private static final long serialVersionUID = -8550474366062230729L;

	/**
	 * 主键ID
	 */
    private Integer id;

    /**
     * 运动类型Id
     */
    private Integer sportTypeId;

    /**
     * 战术类型ID
     */
    private Integer tacticTypeId;

    /**
     * 战术名称
     */
    private String tacticName;
    
    
    /**
     * 删除标识（0：正常，1：删除）
     */
    private Short deleteFlag;

    /**
     * 组织Id
     */
    private String orgUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人Id
     */
    private String createId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    
    /**
     * 修改人ID
     */
    private String modifyId;

    /**
     * 战术板内容（JSON)
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(Integer sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    public Integer getTacticTypeId() {
        return tacticTypeId;
    }

    public void setTacticTypeId(Integer tacticTypeId) {
        this.tacticTypeId = tacticTypeId;
    }

    public String getTacticName() {
        return tacticName;
    }

    public void setTacticName(String tacticName) {
        this.tacticName = tacticName;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(String orgUserId) {
        this.orgUserId = orgUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}