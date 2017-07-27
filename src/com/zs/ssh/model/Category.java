package com.zs.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category implements java.io.Serializable {

	private Integer id;
	private String type;
	private Boolean hot;

	public Category() {
	}

	public Category(String type, Boolean hot) {
		this.type = type;
		this.hot = hot;
	}

	public Category(Integer id, String type, Boolean hot) {
		super();
		this.id = id;
		this.type = type;
		this.hot = hot;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return this.hot;
	}

	@Column(name = "hot")
	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", hot=" + hot + "]";
	}

}
