package com.hzy.campus.entity;

/**
 * UserRelation entity. @author MyEclipse Persistence Tools
 */

public class UserRelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer myId;
	private Integer otherId;
	private String relation;
	private Short state;

	// Constructors

	/** default constructor */
	public UserRelation() {
	}

	/** full constructor */
	public UserRelation(Integer myId, Integer otherId, String relation,
			Short state) {
		this.myId = myId;
		this.otherId = otherId;
		this.relation = relation;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMyId() {
		return this.myId;
	}

	public void setMyId(Integer myId) {
		this.myId = myId;
	}

	public Integer getOtherId() {
		return this.otherId;
	}

	public void setOtherId(Integer otherId) {
		this.otherId = otherId;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}