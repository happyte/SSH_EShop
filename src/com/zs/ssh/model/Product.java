package com.zs.ssh.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;   //商品id
	private String name;  //商品名字
	private BigDecimal price; //商品价格
	private String pic;      //商品图片路径
	private String remark;   //商品简单评论
	private String xremark;  //商品详细评论
	private Date date;       //商品日期
	private Boolean commend; //是否为推荐商品,推荐商品才有可能显示在商城首页
	private Boolean open;    //是否为有效商品,有效商品才有可能显示在商城首页
	private Category category;
	
	public Product() {
		super();
	}

	public Product(String name, BigDecimal price, String pic, String remark, String xremark, Date date, Boolean commend,
			Boolean open, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.remark = remark;
		this.xremark = xremark;
		this.date = date;
		this.commend = commend;
		this.open = open;
		this.category = category;
	}
	
	public Product(String name, BigDecimal price, String pic, String remark, String xremark, Boolean commend,
			Boolean open) {
		super();
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.remark = remark;
		this.xremark = xremark;
		this.commend = commend;
		this.open = open;
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
	
	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "price", precision = 8)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "pic", length = 200)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "xremark")
	public String getXremark() {
		return this.xremark;
	}

	public void setXremark(String xremark) {
		this.xremark = xremark;
	}

	@Column(name = "date", nullable = true, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "commend")
	public Boolean getCommend() {
		return this.commend;
	}

	public void setCommend(Boolean commend) {
		this.commend = commend;
	}

	@Column(name = "open")
	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", remark=" + remark
				+ ", xremark=" + xremark + ", date=" + date + ", commend=" + commend + ", open=" + open + ", category="
				+ category + "]";
	}
	
}
