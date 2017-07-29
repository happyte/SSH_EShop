package com.zs.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String login;
	private String name;
	private String pass;
	
	@Id
	@GeneratedValue
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "login", length = 20)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name = "name", length = 20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "pass", length = 20)
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Account(Integer id, String login, String name, String pass) {
		super();
		this.id = id;
		this.login = login;
		this.name = name;
		this.pass = pass;
	}
	
	public Account(String login, String name, String pass) {
		super();
		this.login = login;
		this.name = name;
		this.pass = pass;
	}
	
	public Account() {
		super();
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", login=" + login + ", name=" + name + ", pass=" + pass + "]";
	}
	
}
