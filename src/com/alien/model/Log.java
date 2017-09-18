package com.alien.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.alien.entity.BaseEntity;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
public class Log extends BaseEntity {
	private static final long serialVersionUID = 1309582605928485828L;
	private String userId;
	private String name;
	private Integer type;
	private String mac;
	private String ip;
	private Integer objectType;
	private String objectId;
	private String eventName;
	private String eventRecord;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(String userId, String name, Integer type, String mac, String ip, Integer objectType, String objectId,
			String eventName, String eventRecord) {
		this.userId = userId;
		this.name = name;
		this.type = type;
		this.mac = mac;
		this.ip = ip;
		this.objectType = objectType;
		this.objectId = objectId;
		this.eventName = eventName;
		this.eventRecord = eventRecord;
	}

	// Property accessors

	@Column(name = "USER_ID")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "MAC", length = 20)
	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Column(name = "IP", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "OBJECT_TYPE")
	public Integer getObjectType() {
		return this.objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	@Column(name = "OBJECT_ID", length = 100)
	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Column(name = "EVENT_NAME", length = 100)
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Column(name = "EVENT_RECORD", length = 500)
	public String getEventRecord() {
		return this.eventRecord;
	}

	public void setEventRecord(String eventRecord) {
		this.eventRecord = eventRecord;
	}

}