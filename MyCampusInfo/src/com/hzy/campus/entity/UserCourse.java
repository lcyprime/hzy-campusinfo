package com.hzy.campus.entity;

/**
 * UserCourse entity. @author MyEclipse Persistence Tools
 */

public class UserCourse implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String course;
	private Short credit;
	private Short state;

	// Constructors

	/** default constructor */
	public UserCourse() {
	}

	/** full constructor */
	public UserCourse(Integer userid, String course, Short credit, Short state) {
		this.userid = userid;
		this.course = course;
		this.credit = credit;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Short getCredit() {
		return this.credit;
	}

	public void setCredit(Short credit) {
		this.credit = credit;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}